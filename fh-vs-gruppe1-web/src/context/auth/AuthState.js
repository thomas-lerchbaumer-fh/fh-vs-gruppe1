import React, {useReducer} from 'react';
import axios from 'axios';
import AuthContext from './authContext';
import authReducer from './authReducer';
import setAuthToken from '../../utils/setAuthToken';
import {
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGOUT,
    CLEAR_ERRORS,
    LOGIN_FAIL
} from '../types';

const AuthState = props => {
    const initialState = {
        token: localStorage.getItem('token'),
        isAuthenticated: null,
        loading: true,
        user: null,
        error: null
    };

    const [state, dispatch] = useReducer(authReducer, initialState);

    // Load User
    const loadUser = async (token) => {
        if(token){
            setAuthToken(token);
        }else{
            setAuthToken(localStorage.token);
        }

        try {
            const res = await axios.get('/api/loadUser');

            dispatch({
                type: USER_LOADED,
                payload: res.data
            });
        } catch (err) {
            dispatch({type: AUTH_ERROR});
        }
    };

    // Login User
    const login = async formData => {
        const headers = {
                'Content-Type': 'application/json'
            }


        try {
            const res = await axios.post('/api/login', formData, {
                headers: headers
            })
             console.log(res);
            dispatch({
                type: LOGIN_SUCCESS,
                payload: res.data
            })
            //loadUser(res.data.token);
        } catch (err) {
            dispatch({
                type: LOGIN_FAIL,
                payload: err.response.data.msg
            });
        }


    };

    // Logout
    const logout = () => dispatch({type: LOGOUT});

    // Clear Errors
    const clearErrors = () => dispatch({type: CLEAR_ERRORS});

    return (
        <AuthContext.Provider
            value={{
                token: state.token,
                isAuthenticated: state.isAuthenticated,
                loading: state.loading,
                user: state.user,
                error: state.error,
                loadUser,
                login,
                logout,
                clearErrors
            }}
        >
            {props.children}
        </AuthContext.Provider>
    );
};

export default AuthState;