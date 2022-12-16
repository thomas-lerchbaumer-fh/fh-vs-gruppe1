import React, {useContext, useState} from 'react';
import AlertContext from "../../../context/alert/alertContext";
import EmployeeContext from "../../../context/employee/employeeContext";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Unstable_Grid2";
import {Button, TextField} from "@mui/material";
import StockContext from "../../../context/stock/stockContext";






const SearchStockForm = props =>{
    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const stockContext = useContext(StockContext);
    const {searchStock, loadingStock} = stockContext;

    const [search,setSearch] = useState('');

    const onChange = (e) =>{
        setSearch(e.target.value);}

    const onSubmit = (e) => {
        e.preventDefault();
        (search.length<= 2) ?  setAlert('Please add at least 2 characters','info') :   searchStock({search})
    }

    return(
        <>
            <Typography variant={"body2"}> Below you can search stocks</Typography>
            <Grid container  justifyContent="center" alignItems={"center"} alignContent="center" className={"searchC"} minWidth={"60vw"} width={"100%"}>
                <Grid item xs={4}>
                    <TextField
                        required
                        fullWidth
                        label="Search for Stocks (Symbol, Name)"
                        name="search"
                        onChange={onChange}
                        value={search}
                    />
                </Grid>
                <Grid item xs={2} >
                    <Button variant="contained" onClick={onSubmit}>Search</Button>
                </Grid>
            </Grid>
        </>

    )




}


export default SearchStockForm;