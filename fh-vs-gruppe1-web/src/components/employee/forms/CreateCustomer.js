import React, {useContext, useState} from 'react';
import Grid from "@mui/material/Unstable_Grid2";
import {Button, TextField} from "@mui/material";
import AlertContext from "../../../context/alert/alertContext";
import EmployeeContext from "../../../context/employee/employeeContext";
import Typography from "@mui/material/Typography";


const CreateCustomer = (props) => {
    const [customer, setCustomer] = useState({});

    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {createCustomer, loadingEmp} = employeeContext;

    const handleInputChange = e => {
        const target = e.target;
        const value = target.value;
        const name = target.name;

        setCustomer({
            ...customer,
            [name]: value
        });
    }

    const onSubmit = (e) => {
        e.preventDefault();
        createCustomer(customer);
    }

    return (
        <>
            <Typography variant={"body2"}> Below you can create a new customer</Typography>
            <Grid container justifyContent="center" direction="column" alignItems={"center"} alignContent="center" className={"createC"}
                  minWidth={"60vw"} width={"100%"}>
                <Grid item xs={4}>
                    <TextField
                        required
                        fullWidth
                        type="text"
                        label="Enter first name"
                        name="firstname"
                        onChange={handleInputChange}
                        value={customer.firstname}
                    />
                </Grid>
                <Grid item xs={4}>
                    <TextField
                        required
                        fullWidth
                        type="text"
                        label="Enter last name"
                        name="lastname"
                        onChange={handleInputChange}
                        value={customer.lastname}
                    />
                </Grid>
                <Grid item xs={4}>
                    <TextField
                        required
                        fullWidth
                        type="email"
                        label="Enter E-Mail"
                        name="email"
                        onChange={handleInputChange}
                        value={customer.email}
                    />
                </Grid>
                <Grid item xs={2}>
                    <Button variant="contained" onClick={onSubmit}>Create</Button>
                </Grid>
            </Grid>
        </>

    )
}

export default CreateCustomer;