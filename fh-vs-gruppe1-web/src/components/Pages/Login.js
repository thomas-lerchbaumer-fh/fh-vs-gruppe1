import React, {useState, useContext, useEffect} from 'react';

import AlertContext from '../../context/alert/alertContext';
import AuthContext from '../../context/auth/authContext';

import Box from '@mui/material/Box';

import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Unstable_Grid2';

import {Button, TextField} from "@mui/material";
import {styled} from "@mui/material/styles";
import { useNavigate } from "react-router-dom";
import LoginForm from "../forms/LoginForm";


const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));

const Login = (props) => {
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
            <LoginForm></LoginForm>
            </>
    )
}


export default Login