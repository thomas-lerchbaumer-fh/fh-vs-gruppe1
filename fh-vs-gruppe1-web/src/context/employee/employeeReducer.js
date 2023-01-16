import {
    ALL_CUSTOMERS,
    BUY_STOCKS,
    CLEAR_ERRORS,
    CUSTOMER_SEARCH_LOADED,
    ERR_NO_CUSTOMER_FOUND,
    GET_BANK_VOLUME,
    SET_LOADING,
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
        case BUY_STOCKS:{

            return{
                ...state,
            }
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
            }
        case ALL_CUSTOMERS:
            return{
                ...state,
                allCustomers: action.payload,
                loadingAllCustomers: false
            }
            ;
        case GET_BANK_VOLUME:
            return{
                ...state,
                bankVolume: action.payload.totalOrderVolume
            }
        default:
            return state;
    }
};