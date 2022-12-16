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

    const [search, setSearch] = useState('');

    const onChange = (e) => {
        setSearch(e.target.value);
    }

    const onSubmit = (e) => {
        e.preventDefault();
        (search.length <= 2) ? setAlert('Please add at least 2 characters', 'info') : searchCustomer({search})
    }

    return (
        <>
            <Typography variant={"body2"}> Below you can search for customers</Typography>
            <Grid container justifyContent="center" alignItems={"center"} alignContent="center" className={"searchC"}
                  minWidth={"60vw"} width={"100%"}>
                <Grid item xs={4}>
                    <TextField
                        required
                        fullWidth
                        label="Search for Customer (Kdnr, Email, Name)"
                        name="search"
                        onChange={onChange}
                        value={search}
                    />
                </Grid>
                <Grid item xs={2}>
                    <Button variant="contained" onClick={onSubmit}>Search</Button>
                </Grid>
            </Grid>
        </>

    )


}


export default SearchCustomer;