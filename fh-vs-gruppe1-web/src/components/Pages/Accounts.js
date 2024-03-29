import React, {useContext, useEffect} from 'react';
import Box from "@mui/material/Box";
import AuthContext from "../../context/auth/authContext";
import {useNavigate} from "react-router-dom";
import {CircularProgress} from "@mui/material";
import LoginForm from "../forms/LoginForm";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Unstable_Grid2";
import GridItem from "../layouts/GridItem";
import AccountAdministration from "../employee/AccountAdministration";


const Accounts = (props) => {
    const authContext = useContext(AuthContext);
    const {login, error, clearErrors, user, isAuthenticated, loadUser, loading, role} = authContext;
    const navigate = useNavigate();

    useEffect(() => {

    }, [isAuthenticated, loading]);


    return (

            <>
                <Box component="main" sx={{flexGrow: 1, p: 3}}>
                    {!isAuthenticated &&
                        <>
                            <Typography variant={"body1"}>To use this service please login</Typography>
                            <LoginForm></LoginForm>
                        </>
                    }
                    {isAuthenticated &&
                    <Grid container spacing={4} width={"100%"}>
                        <Typography>Welcome back {user}. Your role: {role}</Typography>
                        {role === "employee" &&
                            <Grid item xs={12}>
                                <AccountAdministration></AccountAdministration>
                            </Grid>
                        }

                    </Grid>
                    }
                </Box>

            </>
    )

}

export default Accounts