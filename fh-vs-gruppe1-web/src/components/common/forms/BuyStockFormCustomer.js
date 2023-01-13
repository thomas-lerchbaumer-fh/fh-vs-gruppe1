import React, {useContext, useState} from 'react'
import {Button, MenuItem, Select, TableCell, tableCellClasses, TextField} from "@mui/material";
import {styled} from "@mui/material/styles";
import EmployeeContext from "../../../context/employee/employeeContext";
import AlertContext from "../../../context/alert/alertContext";
import CustomerContext from "../../../context/customer/customerContext";



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


const BuyStockFormCustomer = props =>{
    const {stock, allCustomers} = props;

    const customerContext = useContext(CustomerContext);
    const {customerBuyStocks} = customerContext;

    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const [buyAmount, setBuyAmount] = useState(0);
    const [stockTmp, setStockTmp] = useState({ symbol: '', lastTradePrice: 0 });

    const handleBuyAmountChange = (event, stock) => {
        setStockTmp(stock);
        const buyAmount = Number(event.target.value);
        setBuyAmount(buyAmount);
        const totalCost = calculateTotalCost(buyAmount, stock.lastTradePrice);
        stock.totalCost = totalCost;
    };

    const onSubmit = (e) => {
        e.preventDefault();
        setStockTmp(stock);
        if(buyAmount <= 0){
            setAlert('You have to buy at least 1 share', 'info')
        }else{
        customerBuyStocks({"symbol":stock.symbol, "amount":buyAmount});
        }
    }

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
                    inputProps={{ min: "0" }}
                />
            </StyledTableCell>
            <StyledTableCell align="right">{stock.totalCost}</StyledTableCell>
            <StyledTableCell align="right">
                <Button variant="contained"
                        disabled={stock.floatShares <= 0}
                        type="submit" color="primary" onClick={onSubmit}>
                    Buy
                </Button>
            </StyledTableCell>
        </>
    )


}

export default  BuyStockFormCustomer;