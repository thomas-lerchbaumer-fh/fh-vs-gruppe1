import React, {useContext} from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import AlertInfo from "../layout/AlertInfo";
import PrivateRoute from "./PrivateRoute";
import Home from "../Pages/Home";
import AuthContext from "../../context/auth/authContext";
import Login from "../Pages/Login";
import Accounts from "../Pages/Accounts";

const AvailableRoutes = (props) => {
    const authContext = useContext(AuthContext);
    const {isAuthenticated, loading,user} = authContext;

    return (
        <>
            <AlertInfo></AlertInfo>
            <Routes>

                {/*<Route*/}
                {/*    path="/"*/}
                {/*    element={*/}
                {/*        <PrivateRoute auth={{isAuthenticated: isAuthenticated, loading: loading,user}}>*/}
                {/*            <Home/>*/}
                {/*        </PrivateRoute>*/}
                {/*    }*/}
                {/*/>*/}
                <Route exact path='/' element={<Home/>}></Route>
                <Route exact path='/accounts' element={<Accounts/>}></Route>
                <Route exact path='/login' element={<Login/>}></Route>

            </Routes>
        </>

    )

}


export default AvailableRoutes