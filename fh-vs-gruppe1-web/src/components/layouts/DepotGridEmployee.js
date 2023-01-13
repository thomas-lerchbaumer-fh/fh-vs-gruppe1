import React, {useContext, useState} from 'react'
import {styled} from "@mui/material/styles";
import {
    Button,
    Table,
    TableBody,
    TableCell,
    tableCellClasses,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import AlertContext from "../../context/alert/alertContext";
import employeeContext from "../../context/employee/employeeContext";
import EmployeeContext from "../../context/employee/employeeContext";

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

function createData(symbol, amount, unitPrice, date, currentPrice, companyName) {
    const tmpDate = new Date(date)
    const formattedDate = `${("0" + tmpDate.getDay()).slice(-2)}.${("0" + tmpDate.getMonth() +1).slice(-2)}.${tmpDate.getFullYear()} ${tmpDate.getHours()}:${tmpDate.getMinutes()}`
    console.log(formattedDate);
    return { symbol, amount, unitPrice, formattedDate, currentPrice, companyName };
}


const DepotGridEmployee = props =>{
    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {empSellStocks, loadingEmp} = employeeContext;


    const rows = [];
    console.log(props, 'test');
    props.depot[0].transactions.forEach(item => {
        console.log(item);
        rows.push(createData(item.symbol,item.amount,item.unitPrice,item.orderDate,item.currentPrice,item.companyName))
    })


    const [sellAmount, setSellAmount] = useState(0);
    const [stock, setSellStock] = useState({ symbol: '', lastTradePrice: 0 });

    const handleSellStock = (event, stock) => {
        setSellStock(stock);
        const sellAmount = Number(event.target.value);
        setSellAmount(sellAmount);
    };


    const [customer, setCustomer] = useState('');

    const onSubmit = (row, e) => {
        e.preventDefault();
        empSellStocks({"symbol":row.symbol, "amount":row.amount, "userEmail":props.depot[1]});
        setAlert('Data submitted', 'info')
    }

    return(
        <form onSubmit={onSubmit}>
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead>
                    <TableRow>
                        <StyledTableCell align="center">Company</StyledTableCell>
                        <StyledTableCell align="center">Symbol</StyledTableCell>
                        <StyledTableCell align="center">Order date</StyledTableCell>
                        <StyledTableCell align="right">amount</StyledTableCell>
                        <StyledTableCell align="right">Buying price per share</StyledTableCell>
                        <StyledTableCell align="right">Selling price per share</StyledTableCell>
                        <StyledTableCell align="center">Action</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <StyledTableRow key={row.name}>
                            <StyledTableCell align="center">{row.companyName}</StyledTableCell>
                            <StyledTableCell align="center">{row.symbol}</StyledTableCell>
                            <StyledTableCell align="center">{row.formattedDate}</StyledTableCell>
                            <StyledTableCell align="right">{row.amount}</StyledTableCell>
                            <StyledTableCell align="right">{row.unitPrice}€</StyledTableCell>
                            <StyledTableCell align="right">{row.currentPrice}€</StyledTableCell>
                            <StyledTableCell align="right">
                                <Button variant="contained" type="submit" color="primary" onClick={(e) => onSubmit(row, e)}>
                                    Sell
                                </Button>
                            </StyledTableCell>

                        </StyledTableRow>
                    ))}
                </TableBody>
                <TableRow>
                    <TableCell rowSpan={3}><Typography sx={{fontWeight: 'bold'}}>Total Depot Value</Typography></TableCell>
                    <TableCell colSpan={3}><Typography sx={{fontWeight: 'bold'}}>
                        {(Math.round(props.depot[0].currentTotalDepotValue * 100) / 100).toFixed(2)}€</Typography></TableCell>
                </TableRow>
            </Table>
        </TableContainer>
        </form>

    )


}


export default  DepotGridEmployee;