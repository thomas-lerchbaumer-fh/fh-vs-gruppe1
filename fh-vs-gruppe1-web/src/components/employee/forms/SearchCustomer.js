import React, {useContext, useState} from 'react';
import Grid from "@mui/material/Unstable_Grid2";
import {Button, TextField} from "@mui/material";
import AlertContext from "../../../context/alert/alertContext";
import EmployeeContext from "../../../context/employee/employeeContext";
import Typography from "@mui/material/Typography";













const SearchCustomer = (props) => {
    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {searchCustomer, loadingEmp} = employeeContext;

    const [search,setSearch] = useState('');

    const onChange = (e) => setSearch(e.target.search);

    const onSubmit = (e) => {
        e.preventDefault();
        if (search.length < 2) {
            setAlert("Please set ", 'error');
        } else {
            searchCustomer({

            })
        }
    }

    return(
        <>
            <Typography variant={"body2"}> Below you can search for customers</Typography>
            <Grid container spacing={1} justifyContent="center" aligItems={"center"} alignContent="center" className={"searchC"}>
                <Grid item xs={4} md={4} xl={4}>
                    <TextField
                        required
                        fullWidth
                        label="Search for Customer (Kdnr, Email, Name)"
                        name="search"
                        onChange={onChange}
                        value={search}
                    />
                </Grid>
                    <Grid item xs={3} md={3} xl={3}>
                        <Button variant="contained" onClick={onSubmit}>Search</Button>
                    </Grid>
            </Grid>
        </>

    )


}



export default SearchCustomer;