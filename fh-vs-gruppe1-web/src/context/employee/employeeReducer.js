import {

    CLEAR_ERRORS, CUSTOMER_SEARCH_LOADED, ERR_NO_CUSTOMER_FOUND, SET_LOADING
} from '../types';

// eslint-disable-next-line import/no-anonymous-default-export
export default (state, action) => {
    switch (action.type) {
        case CUSTOMER_SEARCH_LOADED:

            return {
                ...state,
                loadingEmp: false,
                customers: action.payload,
            };
        case ERR_NO_CUSTOMER_FOUND:{

        }
        case SET_LOADING:
        return {
            ...state,
            loadingEmp:action.payload
        }
        case CLEAR_ERRORS:
            return {
                ...state,
                error: null
            };
        default:
            return state;
    }
};