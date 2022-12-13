import { Navigate} from 'react-router-dom';
import {CircularProgress} from "@mui/material";


const PrivateRoute = ({ auth: { isAuthenticated, loading }, children }) => {

    if(loading) return <CircularProgress />
    return (isAuthenticated && !loading) ? children : <Navigate to="/login" />;
};

export default PrivateRoute;