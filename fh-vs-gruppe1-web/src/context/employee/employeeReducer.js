import {

    CLEAR_ERRORS, CUSTOMER_SEARCH_LOADED
} from '../types';

// eslint-disable-next-line import/no-anonymous-default-export
export default (state, action) => {
    switch (action.type) {
        case CUSTOMER_SEARCH_LOADED:
            console.log(action.payload)
            return {
                ...state,
                isAuthenticated: true,
                loading: false,
                user: action.payload.name,
                role:action.payload.role.toLowerCase()
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