import React, {useCallback, useReducer} from 'react';
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
    LOGIN_FAIL
} from '../types';

const EmployeeState = props =>{

    const initialState = {
        loadingEmp: true,
        customers:[],
        error: null,
    };

    const [state, dispatch] = useReducer(employeeReducer, initialState);

    const searchCustomer = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }
        setAuthToken(localStorage.token);


        try {
            const res = await axios.get('/api/employee/searchCustomer');
            dispatch({
                type: USER_LOADED,
                payload: res.data
            });
        } catch (err) {
            dispatch({type: AUTH_ERROR});
        }
    }, [dispatch]);


    return(
        <EmployeeContext.Provider
            value={{
                loadingEmp: state.loadingEmp,
                customers: state.customers,
                searchCustomer
            }}
            >
            {props.children}
        </EmployeeContext.Provider>
    )
}

export default EmployeeState;