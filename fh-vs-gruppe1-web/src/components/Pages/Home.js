import React, {useContext, useEffect} from 'react';
import Box from "@mui/material/Box";
import AuthContext from "../../context/auth/authContext";
import {useNavigate} from "react-router-dom";




const Home = (props) => {
    const authContext = useContext(AuthContext);
    const {login, error, clearErrors, isAuthenticated,loadUser,loading } = authContext;
    const navigate = useNavigate();

    useEffect(()=>{
        if(!isAuthenticated){
            navigate("/login")
        }
    },[isAuthenticated]);


    return(
        <>
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
              Hello
            </Box>

        </>
    )

}

export default Home