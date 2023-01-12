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

function createData(symbol, amount, unitPrice, date, currentPrice) {
    return { symbol, amount, unitPrice, date, currentPrice };
}


const DepotGridEmployee = props =>{
    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const employeeContext = useContext(EmployeeContext);
    const {empSellStocks, loadingEmp} = employeeContext;


    const rows = [
        // createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
        // createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
        // createData('Eclair', 262, 16.0, 24, 6.0),
        // createData('Cupcake', 305, 3.7, 67, 4.3),
        // createData('Gingerbread', 356, 16.0, 49, 3.9),
    ];
    console.log(props, 'test');
    props.depot[0].transactions.forEach(item => {
        rows.push(createData(item.symbol,item.amount,item.unitPrice,item.orderDate,item.currentPrice))
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
                        <StyledTableCell align="right">Symbol</StyledTableCell>
                        <StyledTableCell align="right">Order date</StyledTableCell>
                        <StyledTableCell align="right">amount</StyledTableCell>
                        <StyledTableCell align="right">Buying price per share</StyledTableCell>
                        <StyledTableCell align="right">Selling price per share</StyledTableCell>
                        <StyledTableCell align="right">Action</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <StyledTableRow key={row.name}>
                            <StyledTableCell align="right">{row.symbol}</StyledTableCell>
                            <StyledTableCell align="right">{row.date}</StyledTableCell>
                            <StyledTableCell align="right">{row.amount}</StyledTableCell>
                            <StyledTableCell align="right">{row.unitPrice}</StyledTableCell>
                            <StyledTableCell align="right">{row.currentPrice}</StyledTableCell>
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
                    <TableCell colSpan={3}><Typography sx={{fontWeight: 'bold'}}>{props.depot[0].currentTotalDepotValue} €</Typography></TableCell>
                </TableRow>
            </Table>
        </TableContainer>
        </form>

    )


}


export default  DepotGridEmployee;