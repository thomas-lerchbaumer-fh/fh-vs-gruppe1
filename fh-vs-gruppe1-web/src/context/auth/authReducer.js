import {
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    LOGOUT,
    CLEAR_ERRORS
} from '../types';

// eslint-disable-next-line import/no-anonymous-default-export
export default (state, action) => {
    switch (action.type) {
        case USER_LOADED:
            console.log(action.payload)
            return {
                ...state,
                isAuthenticated: true,
                loading: false,
                user: action.payload.name,
                role:action.payload.role.toLowerCase()
            };
        case LOGIN_SUCCESS:
            localStorage.setItem('token', action.payload.accessToken);
            return {
                ...state,
                ...action.payload,
                isAuthenticated: true,
            };
        case AUTH_ERROR:
        case LOGIN_FAIL:
        case LOGOUT:
            console.log('you ex?');
            //localStorage.removeItem('token');
            return {
                ...state,
                token: null,
                isAuthenticated: false,
                loading: false,
                user: null,
                error: action.payload
            };
        case CLEAR_ERRORS:
            return {
                ...state,
                error: null
            };
        default:
            return state;
    }
};