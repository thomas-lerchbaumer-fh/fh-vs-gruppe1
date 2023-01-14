import React, {useContext, useEffect} from 'react'
import SearchCustomer from "./forms/SearchCustomer";
import Grid from "@mui/material/Unstable_Grid2";
import Box from "@mui/material/Box";
import GridItem from "../layouts/GridItem";
import DisplayCustomers from "./layout/DisplayCustomers";
import SearchStockForm from "../common/forms/SearchStockForm";
import StockGrid from "../common/StockGrid";
import StockContext from "../../context/stock/stockContext";
import {Button} from "@mui/material";
import EmployeeContext from "../../context/employee/employeeContext";
import Typography from "@mui/material/Typography";


const EmployeeHome = (props) => {
    const stockContext = useContext(StockContext);
    const {stocks, loadingStock} = stockContext;

    const employeeContext = useContext(EmployeeContext);
    const {bankVolume,getBankVolume} = employeeContext;

    const getVolume = () =>{
        console.log('exe=');
        getBankVolume()
    }

    useEffect(() => {

    }, [loadingStock,stocks,bankVolume]);

    return (
        <>
            <Grid item xs={12}> <GridItem>
                <Grid container>
                    <Grid item xs={12}>
                        <SearchCustomer></SearchCustomer>
                    </Grid>
                    <Grid item xs={12}>
                        <DisplayCustomers></DisplayCustomers>
                    </Grid>
                </Grid>
            </GridItem>

            </Grid>
            <Grid item xs={12}>
                <GridItem>
                    <Grid container>
                        <Grid item xs={12}>
                            <SearchStockForm></SearchStockForm>
                        </Grid>
                        {!loadingStock && stocks &&
                        <Grid item xs={12}>
                            <StockGrid stocks={stocks} ></StockGrid>
                        </Grid>
                        }
                    </Grid>
                </GridItem>
            </Grid>

            <Grid item xs={12}>
                <GridItem>
                    <Grid container>
                        <Grid item xs={12}>
                           <Button variant="contained" onClick={getVolume}> Get available bank volume</Button>
                        </Grid>
                        {bankVolume > 0 &&
                            <Grid item xs={12}>
                               <Typography variant={"h5"}>Total available bank volume: <b>{bankVolume.toLocaleString()}€</b></Typography>
                            </Grid>
                        }
                    </Grid>
                </GridItem>
            </Grid>
        </>
    )

}


export default EmployeeHome;