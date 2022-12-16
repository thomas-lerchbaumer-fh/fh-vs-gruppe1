import React, {useContext} from 'react'
import {styled} from "@mui/material/styles";
import {Table, TableBody, TableCell, tableCellClasses, TableContainer, TableHead, TableRow} from "@mui/material";
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

function createData(companyName, floatShares, lastTradePrice, stockExchange,symbol) {
    return { companyName,symbol, floatShares, lastTradePrice, stockExchange, };
}


const DepotGridEmployee = props =>{
    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;


    // const stockContext = useContext(StockContext);
    // const {stocks, loadingStocks} = stockContext;
        return(
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead>
                    <TableRow>
                        <StyledTableCell>Company</StyledTableCell>
                        <StyledTableCell align="right">Symbol</StyledTableCell>
                        <StyledTableCell align="right">Available stocks</StyledTableCell>
                        <StyledTableCell align="right">Price per share</StyledTableCell>
                        <StyledTableCell align="right">Buy Amount</StyledTableCell>
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

                        </StyledTableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )


}


export default  DepotGridEmployee;