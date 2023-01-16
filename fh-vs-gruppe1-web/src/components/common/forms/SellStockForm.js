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


const SellStockForm = props =>{
    const {stock, depot} = props;

    const employeeContext = useContext(EmployeeContext);
    const {empSellStocks} = employeeContext;

    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const [sellAmount, setSellAmount] = useState(0);
    const [stockTmp, setStockTmp] = useState({ symbol: '', lastTradePrice: 0 });

    const handleSellAmountChange = (event, stock) => {
        setStockTmp(stock);
        const sellAmount = Number(event.target.value);
        setSellAmount(sellAmount);
        const totalCost = calculateTotalCost(sellAmount, stock.currentPrice);
        stock.totalCost = totalCost;
    };

    const onSubmit = (e) => {
        e.preventDefault();
        setStockTmp(stock);
        if(sellAmount > stock.amount){
            setAlert('You can\'t sell more stocks than you own', 'info')
        }else{
            empSellStocks({"id":stock.id, "symbol":stock.symbol, "amount":sellAmount, "userEmail":depot[1]});
            setAlert('Data submitted', 'info')
            window.location.reload()
        }
    }

    return(
        <>
            <StyledTableCell align="right">
                <TextField
                    id="sell_amount"
                    name="sell_amount"
                    label="Sell Amount"
                    fullWidth={true}
                    value={stock.sellAmount}
                    onChange={(event) => handleSellAmountChange(event, stock)}
                    type="number"
                    inputProps={{ min: 1, max: stock.amount}}
                />
            </StyledTableCell>
            <StyledTableCell align="right">{stock.totalCost}</StyledTableCell>
            <StyledTableCell align="right">
                <Button variant="contained" type="submit" color="primary" onClick={onSubmit}>
                    Sell
                </Button>
            </StyledTableCell>
        </>
    )


}

export default SellStockForm;