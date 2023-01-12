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

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

const calculateTotalCost = (buyAmount, pricePerShare) => {
    return (buyAmount * pricePerShare).toFixed(2);
};

const DepotGridEmployee = (props) => {
    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {empBuyStocks, allCustomers, getAllCustomers, loadingAllCustomers, loadingEmp} = employeeContext;

    useEffect(() => {getAllCustomers()
    }, []);

    const [buyAmount, setBuyAmount] = useState(0);
    const [stock, setStock] = useState({ symbol: '', lastTradePrice: 0 });

    const handleBuyAmountChange = (event, stock) => {
        setStock(stock);
        const buyAmount = Number(event.target.value);
        setBuyAmount(buyAmount);
        const totalCost = calculateTotalCost(buyAmount, stock.lastTradePrice);
        stock.totalCost = totalCost;
    };

    const [customer, setCustomer] = useState('');

    const onSubmit = (e) => {
        e.preventDefault();
        setStock(stock);
        empBuyStocks({"symbol":stock.symbol, "amount":buyAmount, "userEmail":customer});
        setAlert('Data submitted', 'info')
    }

    return (
        (loadingAllCustomers)?<p>Loading</p>:
        <form onSubmit={onSubmit}>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
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
                                <StyledTableCell align="right">{stock.lastTradePrice}</StyledTableCell>
                                <StyledTableCell align="right">
                                    <TextField
                                        id="buy_amount"
                                        name="buy_amount"
                                        label="Buy Amount"
                                        value={stock.buyAmount}
                                        onChange={(event) => handleBuyAmountChange(event, stock)}
                                        type="number"
                                        inputProps={{ min: "0" }}
                                    />
                                </StyledTableCell>
                                <StyledTableCell align="right">{stock.totalCost}</StyledTableCell>
                                <StyledTableCell align="right">
                                    <Select value={customer} onChange={(event) => setCustomer(event.target.value)}>
                                        {allCustomers.map((name) => (
                                            <MenuItem
                                                key={name.email}
                                                value={name.email}
                                            >
                                                {name.email}</MenuItem>))}
                                    </Select>
                                </StyledTableCell>
                                <StyledTableCell align="right">
                                    <Button variant="contained" type="submit" color="primary">
                                        Buy
                                    </Button>
                                </StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </form>
    );
};

export default DepotGridEmployee;
