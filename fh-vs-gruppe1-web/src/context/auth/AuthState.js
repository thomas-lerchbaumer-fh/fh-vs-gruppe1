import React, {useCallback, useReducer} from 'react';
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
        role: null,
        error: null
    };

    const [state, dispatch] = useReducer(authReducer, initialState);


    const loadUser = useCallback(async (token) => {
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
    }, [dispatch]);



    const login = useCallback(async (formData) => {
        const headers = {
            'Content-Type': 'application/json'
        }

        try {
            const res = await axios.post('/api/login', formData, {
                headers: headers
            })

            dispatch({
                type: LOGIN_SUCCESS,
                payload: res.data
            })
            await loadUser(res.data.accessToken);
        } catch (err) {
            dispatch({
                type: LOGIN_FAIL,
                payload: err.response.data.msg
            });
        }
    }, [dispatch]);



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
                role: state.role,
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