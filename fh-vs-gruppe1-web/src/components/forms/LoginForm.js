import React, {useState, useContext, useEffect} from 'react';

import AlertContext from '../../context/alert/alertContext';
import AuthContext from '../../context/auth/authContext';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Unstable_Grid2';
import {Link} from 'react-router-dom'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {Button, TextField} from "@mui/material";
import {styled} from "@mui/material/styles";
import { useNavigate } from "react-router-dom";


const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));

const LoginForm = (props) => {
    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const nav = useNavigate()

    const authContext = useContext(AuthContext);
    const {login, error, clearErrors, isAuthenticated } = authContext;

    const [user, setUser] = useState({
        username: '',
        password: '',
    })
    const onChange = (e) => setUser({...user, [e.target.name]: e.target.value})

    const onSubmit = (e) => {
        e.preventDefault();
        if (username === '' || password === '') {
            setAlert("Please fill in all fields", 'error');
        } else {
            login({
                username,
                password
            })
        }
    }

    useEffect(()=>{
        if(isAuthenticated){
            nav("/");
        }
        if(error ==='Invalid Credentials'){
            setAlert(error, 'danger');
            clearErrors();
        }

        //es-lint-disable-next-line
    },[error,isAuthenticated, props.history]);

    const {username, password} = user;

    return (
        <>
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': {m: 1, width: '25ch'},
                }}
                display="flex"
                noValidate
                autoComplete="off"
                justifyContent="center"
                flexDirection="column"
            >
                <Grid container spacing={2} justifyContent="center" alignContent="center">
                    <Grid xs={12} md={12} xl={12}>
                        <TextField
                            required
                            fullWidth
                            id="email-required"
                            label="Email"
                            name="username"
                            onChange={onChange}
                            value={username}
                        />
                    </Grid>
                    <Grid xs={12} md={12} xl={12}>
                        <TextField
                            required
                            fullWidth
                            type="password"
                            id="password-required"
                            label="Password"
                            name="password"
                            onChange={onChange}
                            value={password}
                        />
                    </Grid>
                    <Grid xs={12} md={12} xl={12}>
                        <Button variant="contained" onClick={onSubmit}>Login</Button>
                    </Grid>
                </Grid>
            </Box>
        </>
    )
}


export default LoginForm