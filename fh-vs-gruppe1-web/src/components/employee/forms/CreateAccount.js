import React, {useContext, useState} from 'react';
import Grid from "@mui/material/Unstable_Grid2";
import {Button, Checkbox, FormControlLabel, Radio, RadioGroup, TextField} from "@mui/material";
import AlertContext from "../../../context/alert/alertContext";
import EmployeeContext from "../../../context/employee/employeeContext";
import Typography from "@mui/material/Typography";


const CreateAccount = (props) => {
    const [customer, setCustomer] = useState({});

    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {createAccount, loadingEmp} = employeeContext;

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
        createAccount(customer);
        setAlert('Data submitted', 'info')
        setCustomer({firstname:'',lastname:'',email:'',streetname:'',housenumber:'',postcode:'',city:''});
    }

    return (
        <>
            <Typography variant={"body2"}> Below you can create create a new Customer or Employee Account</Typography>
            <form onSubmit={onSubmit}>
                <Grid container justifyContent="center" direction="row" alignItems={"center"} alignContent="center"
                      className={"createC"} minWidth={"60vw"} width={"100%"}>
                    <Grid item xs={6}>
                        <TextField
                            required
                            fullWidth
                            type="text"
                            label="First Name"
                            name="firstname"
                            onChange={handleInputChange}
                            value={customer.firstname}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            required
                            fullWidth
                            type="text"
                            label="Last Name"
                            name="lastname"
                            onChange={handleInputChange}
                            value={customer.lastname}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            type="email"
                            label="E-Mail"
                            name="email"
                            onChange={handleInputChange}
                            value={customer.email}
                        />
                    </Grid>
                    <Grid item xs={10}>
                        <TextField
                            required
                            fullWidth
                            type="text"
                            label="Streetname"
                            name="streetname"
                            onChange={handleInputChange}
                            value={customer.streetname}
                        />
                    </Grid>
                    <Grid item xs={2}>
                        <TextField
                            required
                            fullWidth
                            type="number"
                            label="House Number"
                            name="housenumber"
                            onChange={handleInputChange}
                            value={customer.housenumber}
                        />
                    </Grid>
                    <Grid item xs={4}>
                        <TextField
                            required
                            fullWidth
                            type="number"
                            label="Postal Code"
                            name="postcode"
                            onChange={handleInputChange}
                            value={customer.postcode}
                        />
                    </Grid>
                    <Grid item xs={8}>
                        <TextField
                            required
                            fullWidth
                            type="text"
                            label="City"
                            name="city"
                            onChange={handleInputChange}
                            value={customer.city}
                        />
                    </Grid>
                    <Grid item xs={4}>
                        <RadioGroup defaultValue="customer" name="privilege" onChange={handleInputChange}>
                            <FormControlLabel value="customer" control={<Radio />} label="Customer" />
                            <FormControlLabel value="employee" control={<Radio />} label="Employee" />
                        </RadioGroup>
                    </Grid>
                    <Grid item xs={2}>
                        <Button variant="contained" type="submit">Create</Button>
                    </Grid>
                </Grid>
            </form>
        </>

    )
}

export default CreateAccount;