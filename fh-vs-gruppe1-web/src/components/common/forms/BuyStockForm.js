import React, {useContext, useState} from 'react'
import {Button, MenuItem, Select, TableCell, tableCellClasses, TextField} from "@mui/material";
import {styled} from "@mui/material/styles";
import EmployeeContext from "../../../context/employee/employeeContext";
import AlertContext from "../../../context/alert/alertContext";



const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));



const calculateTotalCost = (buyAmount, pricePerShare) => {
    return (buyAmount * pricePerShare).toFixed(2);
};


const BuyStockForm = props =>{
    const {stock, allCustomers} = props;

    const employeeContext = useContext(EmployeeContext);
    const {empBuyStocks} = employeeContext;

    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const [buyAmount, setBuyAmount] = useState(0);
    const [stockTmp, setStockTmp] = useState({ symbol: '', lastTradePrice: 0 });

    const handleBuyAmountChange = (event, stock) => {
        console.log(stock);
        setStockTmp(stock);
        const buyAmount = Number(event.target.value);
        setBuyAmount(buyAmount);
        const totalCost = calculateTotalCost(buyAmount, stock.lastTradePrice);
        stock.totalCost = totalCost;
    };

    const onSubmit = (e) => {
        e.preventDefault();
        setStockTmp(stock);
        if(buyAmount > stock.floatShares){
            setAlert('You can\'t buy more stocks than available', 'info')
        }else{
            empBuyStocks({"symbol":stock.symbol, "amount":buyAmount, "userEmail":customer});
            setAlert('Data submitted', 'info')
            window.location.reload()
        }
    }

    const [customer, setCustomer] = useState('');


    return(
        <>
            <StyledTableCell align="right">
                <TextField
                    id="buy_amount"
                    name="buy_amount"
                    label="Buy Amount"
                    value={stock.buyAmount}
                    onChange={(event) => handleBuyAmountChange(event, stock)}
                    type="number"
                    inputProps={{ min: 1, max: stock.floatShares}}
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
                <Button variant="contained" type="submit" color="primary" onClick={onSubmit}>
                    Buy
                </Button>
            </StyledTableCell>
        </>
    )


}

export default  BuyStockForm;