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
import CustomerContext from "../../context/customer/customerContext";
import Grid from "@mui/material/Unstable_Grid2";
import SellStockForm from "../common/forms/SellStockForm";

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


const DepotGridCustomer = props =>{
    const alertContext = useContext(AlertContext);
    const { setAlert } = alertContext;

    const customerContext = useContext(CustomerContext);
    const {customerSellStock, loading} = customerContext;


    const rows = [];
    console.log(props, 'test');
    props.depot[0].transactions.forEach(item => {
        console.log(item);
        rows.push(createData(item.symbol,item.amount,item.unitPrice,item.orderDate,item.currentPrice,item.companyName))
    })


    const [sellAmount, setSellAmount] = useState(0);
    const [stock, setSellStock] = useState({ symbol: '', lastTradePrice: 0 });

    const [groupedStocks, setGroupedStocks] = useState([]);

    const groupStocks = () =>{
        //const res = [rows.reduce((a,c) => (a[c.symbol]=(a[c.symbol]||[]).concat(c),a) ,{})];
        const res = [];
        const keys =[];
        for(let i = 0; i < rows.length; i++  ){
            const accessor = rows[i].symbol;
            if(res[accessor]){
                res[accessor].amount += rows[i].amount
            }else{
                res[accessor] = rows[i]
                keys.push(rows[i].symbol);
            }
        }
        const group = [];
        keys.forEach(key => {
            group.push(res[key]);
        })

        setGroupedStocks(group);
    }


    const handleSellStock = (event, stock) => {
        setSellStock(stock);
        const sellAmount = Number(event.target.value);
        setSellAmount(sellAmount);
    };

    const onSubmit = (row, e) => {
        e.preventDefault();
        customerSellStock({"symbol":row.symbol, "amount":row.amount});
        setAlert('Data submitted', 'info')
    }

    return(
        <>
        <form onSubmit={onSubmit}>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell align="center">Company</StyledTableCell>
                            <StyledTableCell align="center">Symbol</StyledTableCell>
                            <StyledTableCell align="center">Order date</StyledTableCell>
                            <StyledTableCell align="right">Amount</StyledTableCell>
                            <StyledTableCell align="right">Buying price per share</StyledTableCell>
                            <StyledTableCell align="right">Selling price per share</StyledTableCell>
                            <StyledTableCell align="center">Sell Amount</StyledTableCell>
                            <StyledTableCell align="center">Total Selling Price</StyledTableCell>
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
                                <StyledTableCell align="right">{row.unitPrice.toFixed(2).toLocaleString()}€</StyledTableCell>
                                <StyledTableCell align="right">{row.currentPrice.toFixed(2).toLocaleString()}€</StyledTableCell>
                                <SellStockForm stock={row} depot={props.depot}></SellStockForm>
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
    <Grid xs={12}>
        {rows.length > 0 &&

            <Button variant={"contained"} onClick={groupStocks} sx={{marginBottom:"2%"}}> Show Grouped Stocks </Button>

        }
        {groupedStocks.length > 0 &&

            <TableContainer component={Paper} >
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell align="center">Company</StyledTableCell>
                            <StyledTableCell align="center">Symbol</StyledTableCell>
                            <StyledTableCell align="right">amount</StyledTableCell>
                            <StyledTableCell align="right">Selling price per share</StyledTableCell>
                            <StyledTableCell align="right">Total value</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {groupedStocks.map((row) => (
                            <StyledTableRow key={row.name}>
                                <StyledTableCell align="center">{row.companyName} </StyledTableCell>
                                <StyledTableCell align="center">{row.symbol}</StyledTableCell>
                                <StyledTableCell align="right">{row.amount.toLocaleString()}</StyledTableCell>
                                <StyledTableCell align="right">{row.currentPrice.toFixed(2).toLocaleString()}€</StyledTableCell>
                                <StyledTableCell align="right">
                                    {(row.amount * row.currentPrice).toFixed(2).toLocaleString() }€
                                </StyledTableCell>

                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>



        }
    </Grid>
    </>

    )


}


export default  DepotGridCustomer;