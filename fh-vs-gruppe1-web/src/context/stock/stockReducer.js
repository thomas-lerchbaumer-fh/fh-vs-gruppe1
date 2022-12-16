import {

    CLEAR_ERRORS, CUSTOMER_SEARCH_LOADED, ERR_NO_CUSTOMER_FOUND, SET_LOADING, STOCK_SEARCH
} from '../types';

// eslint-disable-next-line import/no-anonymous-default-export
export default (state, action) => {
    switch (action.type) {
        case STOCK_SEARCH:

            return {
                ...state,
                loadingStock: false,
                stocks: action.payload,
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