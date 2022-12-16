import React, {useCallback, useReducer} from 'react';
import axios from 'axios';
import StockContext from "./stockContext";
import stockReducer from './stockReducer';
import setAuthToken from '../../utils/setAuthToken';
import {
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGOUT,
    CLEAR_ERRORS,
    LOGIN_FAIL, CUSTOMER_SEARCH_LOADED, SET_LOADING, STOCK_SEARCH
} from '../types';

const StockState = props =>{

    const initialState = {
        loadingStock: true,
        stocks:[],
        error: null,
    };

    const [state, dispatch] = useReducer(stockReducer, initialState);

    const searchStock = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        dispatch({
            type: SET_LOADING,
            payload:true
        })

        setAuthToken(localStorage.token);

        try {
            const res = await axios.post('/api/searchStock',formData,{
                headers: headers
            })

            dispatch({
                type: STOCK_SEARCH,
                payload: res.data
            });
        } catch (err) {
            dispatch({type: AUTH_ERROR});
        }
    }, [dispatch]);


    return(
        <StockContext.Provider
            value={{
                loadingStock: state.loadingStock,
                stocks: state.stocks,
                searchStock
            }}
        >
            {props.children}
        </StockContext.Provider>
    )
}

export default StockState;