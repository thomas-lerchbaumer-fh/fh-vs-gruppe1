import React, {useCallback, useContext, useReducer} from 'react';
import axios from 'axios';
import EmployeeContext from "./employeeContext";
import employeeReducer from './employeeReducer';
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
    GET_BANK_VOLUME
} from '../types';
import AlertContext from "../alert/alertContext";

const EmployeeState = props => {

    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const initialState = {
        loadingEmp: true,
        customers: [],
        allCustomers: [],
        loadingAllCustomers: true,
        loadingBankVol: true,
        bankVolume: 0,
        error: null,
    };

    const [state, dispatch] = useReducer(employeeReducer, initialState);

    const searchCustomer = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload: true
        })
        setAuthToken(localStorage.token);

        try {
            const res = await axios.post('/api/employee/searchUser', formData, {
                headers: headers
            })
            // console.log(res);
            dispatch({
                type: CUSTOMER_SEARCH_LOADED,
                payload: res.data
            });
        } catch (err) {
            setAlert(err.response.data.message, "error");
            console.log(err)
            dispatch({type: AUTH_ERROR});
        }
    }, [dispatch]);

    const createAccount = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload: true
        })

        setAuthToken(localStorage.token);

        console.log(formData);
        if (formData['privilege'] === 'customer'){
            try {
                const res = await axios.post('/api/employee/createCustomer', formData, {
                    headers: headers
                })

                dispatch({
                    type: ACCOUNT_CREATION,
                    payload: res.data
                });
            } catch (err) {
                dispatch({type: AUTH_ERROR});
            }
        }
        else{
            try {
                const res = await axios.post('/api/employee/createEmployee', formData, {
                    headers: headers
                })

                dispatch({
                    type: ACCOUNT_CREATION,
                    payload: res.data
                });
            } catch (err) {
                dispatch({type: AUTH_ERROR});
            }
        }
    }, [dispatch]);


    const getBankVolume = useCallback(async () => {
        setAuthToken(localStorage.token);

        try {
            const res = await axios.get('/api/employee/getBankVolume');
            console.log(res);
            dispatch({
                type: GET_BANK_VOLUME,
                payload: res.data
            })

        } catch (e) {

        }


    }, [])

    const empBuyStocks = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload: true
        })
        setAuthToken(localStorage.token);

        try {
            const res = await axios.post('/api/employee/buyStock', formData, {
                headers: headers
            })


            setAlert("Trade successfull", "success");
        } catch (err) {
            console.log(err);
            setAlert(err.response.data.message, 'error')
        }
    }, [dispatch]);

    const getAllCustomers = useCallback(async () => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload: true
        })

        setAuthToken(localStorage.token);

        try {
            const res = await axios.get('/api/employee/getAllCustomers', {
                headers: headers
            })
            console.log(res)
            dispatch({
                type: ALL_CUSTOMERS,
                payload: res.data
            });
        } catch (err) {
            dispatch({type: AUTH_ERROR});
        }
    }, [dispatch]);

    const empSellStocks = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload: true
        })

        setAuthToken(localStorage.token);

        console.log(formData);

        try {
            const res = await axios.post('/api/employee/sellStock', formData, {
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
        <EmployeeContext.Provider
            value={{
                loadingEmp: state.loadingEmp,
                customers: state.customers,
                searchCustomer,
                createAccount,
                empBuyStocks,
                getAllCustomers,
                loadingAllCustomers: state.loadingAllCustomers,
                allCustomers: state.allCustomers,
                empSellStocks,
                bankVolume: state.bankVolume,
                getBankVolume
            }}
        >
            {props.children}
        </EmployeeContext.Provider>
    )
}

export default EmployeeState;