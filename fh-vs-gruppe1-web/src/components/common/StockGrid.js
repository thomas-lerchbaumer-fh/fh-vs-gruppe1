import React, {useContext, useEffect, useState} from 'react'
import {styled} from "@mui/material/styles";
import {
    Button, MenuItem, Select,
    Table,
    TableBody,
    TableCell,
    tableCellClasses,
    TableContainer,
    TableHead,
    TableRow,
    TextField
} from "@mui/material";
import Paper from "@mui/material/Paper";
import AlertContext from "../../context/alert/alertContext";
import EmployeeContext from "../../context/employee/employeeContext";
import StockContext from "../../context/stock/stockContext";
import BuyStockForm from "./forms/BuyStockForm";

const StyledTableCell = styled(TableCell)(({theme}) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({theme}) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));


const DepotGridEmployee = (props) => {
    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {empBuyStocks, allCustomers, getAllCustomers, loadingAllCustomers, loadingEmp} = employeeContext;

    useEffect(() => {
        getAllCustomers()
    }, []);

    return (
        (loadingAllCustomers) ? <p>Loading</p> :
            <TableContainer component={Paper}>
                <Table sx={{minWidth: 700}} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell>Company</StyledTableCell>
                            <StyledTableCell align="right">Symbol</StyledTableCell>
                            <StyledTableCell align="right">Available stocks</StyledTableCell>
                            <StyledTableCell align="right">Price per share</StyledTableCell>
                            <StyledTableCell align="center">Buy Amount</StyledTableCell>
                            <StyledTableCell align="right">Total Cost</StyledTableCell>
                            <StyledTableCell align="right">Customer</StyledTableCell>
                            <StyledTableCell align="right">Action</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {props.stocks.map((stock) => (
                            <StyledTableRow key={stock.companyName}>
                                <StyledTableCell component="th" scope="row">
                                    {stock.companyName}
                                </StyledTableCell>
                                <StyledTableCell align="right">{stock.symbol}</StyledTableCell>
                                <StyledTableCell align="right">{stock.floatShares}</StyledTableCell>
                                <StyledTableCell align="right">{stock.lastTradePrice.toFixed(2)}</StyledTableCell>
                                <BuyStockForm stock={stock} allCustomers={allCustomers}></BuyStockForm>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
    );
};

export default DepotGridEmployee;
