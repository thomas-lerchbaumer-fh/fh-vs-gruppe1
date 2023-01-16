import React, {useCallback, useContext, useReducer} from 'react';
import axios from 'axios';
import setAuthToken from '../../utils/setAuthToken';
import {
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGOUT,
    CLEAR_ERRORS,
    LOGIN_FAIL,
    CUSTOMER_SEARCH_LOADED,
    SET_LOADING,
    BUY_STOCKS,
    ACCOUNT_CREATION,
    ALL_CUSTOMERS,
    SELL_STOCKS,
    SET_ALERT,
    GET_CUSTOMER_DEPOT, UPDATE_CUSTOMER_DEPOT
} from '../types';
import AlertContext from "../alert/alertContext";
import CustomerContext from "./customerContext";
import customerReducer from "./customerReducer";

const CustomerState = props => {

    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const initialState = {
        error: null,
        loading:true,
        customer : []
    };

    const [state, dispatch] = useReducer(customerReducer, initialState);

    const getCustomer = useCallback( async () =>{


        setAuthToken(localStorage.token);

        try{
            const res = await axios.get('api/customer/getCustomer')
            dispatch({
                type: GET_CUSTOMER_DEPOT,
                payload: res.data
            })

        }catch (e){

        }
    })

    const customerBuyStocks = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }

        setAuthToken(localStorage.token);

        try {
            const res = await axios.post('/api/customer/buyStock', formData, {
                headers: headers
            })
            console.log(res, 'buy cutstomer');
             dispatch({
                type: UPDATE_CUSTOMER_DEPOT,
                payload: res.data
            });
            setAlert("Trade successful","success")
        } catch (err) {
            console.log(err);
            setAlert(err.response.data.message, 'error')
        }
    }, []);


    const customerSellStocks = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload: true
        })

        setAuthToken(localStorage.token);

        try {
            const res = await axios.post('/api/customer/sellStock', formData, {
                headers: headers
            })

            dispatch({
                type: SELL_STOCKS,
                payload: res.data
            });

        } catch (err) {
            dispatch({type: AUTH_ERROR});
        }
    }, [dispatch]);

    return (
        <CustomerContext.Provider
            value={{
                customerBuyStocks,
                customerSellStocks,
                getCustomer,
                customer: state.customer,
                loading: state.loading
            }}
        >
            {props.children}
        </CustomerContext.Provider>
    )
}

export default CustomerState;