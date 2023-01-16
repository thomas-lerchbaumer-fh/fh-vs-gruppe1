import {
    ALL_CUSTOMERS,
    CLEAR_ERRORS,
    CUSTOMER_SEARCH_LOADED,
    ERR_NO_CUSTOMER_FOUND,
    GET_CUSTOMER_DEPOT,
    SET_LOADING,
    UPDATE_CUSTOMER_DEPOT,
} from '../types';

// eslint-disable-next-line import/no-anonymous-default-export
export default (state, action) => {
    switch (action.type) {
        case GET_CUSTOMER_DEPOT:
            return {
                ...state,
                loading: false,
                customer: action.payload,
            };
        case UPDATE_CUSTOMER_DEPOT:
            const tmpCustomer = state.customer;
            state.customer.depot.transactions.push(action.payload);
            //tmpCustomer.depot.transactions.push(action.payload);
            console.log(tmpCustomer, "tmp customer after");
            return{
                ...state,
            }
        case SET_LOADING:
            return {
                ...state,
                loading:action.payload
            }
        case CLEAR_ERRORS:
            return {
                ...state,
                error: null
            }
        default:
            return state;
    }
};