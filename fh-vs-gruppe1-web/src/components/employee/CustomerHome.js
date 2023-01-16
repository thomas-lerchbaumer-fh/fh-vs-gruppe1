import React, {useContext, useEffect} from 'react'
import SearchCustomer from "./forms/SearchCustomer";
import Grid from "@mui/material/Unstable_Grid2";
import Box from "@mui/material/Box";
import GridItem from "../layouts/GridItem";
import SearchStockForm from "../common/forms/SearchStockForm";
import StockGrid from "../common/StockGrid";
import StockContext from "../../context/stock/stockContext";
import CustomerContext from "../../context/customer/customerContext";
import {CircularProgress} from "@mui/material";
import DepotGridEmployee from "../layouts/DepotGridEmployee";
import Typography from "@mui/material/Typography";
import DepotGridCustomer from "../layouts/DepotGridCustomer";
import StockGridCustomer from "../common/StockGridCustomer";


const CustomerHome = (props) => {
    const stockContext = useContext(StockContext);
    const {stocks, loadingStock} = stockContext;

    const customerContext = useContext(CustomerContext);
    const {customer, loading,getCustomer} = customerContext;


    useEffect(()=>{
        if(loading){
            getCustomer();
        }

    },[customer])

    useEffect(() => {

    }, [loadingStock,stocks]);

    return (
        (loading)? <CircularProgress /> :
        <>
            <Grid item xs={12}>
                <GridItem>
                    <Grid container>
                        <Grid item xs={12}>
                        <Typography variant={"h6"}> Hello {customer.firstName} {customer.surname} , below you can find your depot.</Typography>
                        </Grid>
                        <Grid item xs={12}>
                            <DepotGridCustomer depot={[customer.depot, customer.email]}></DepotGridCustomer>
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
                            <StockGridCustomer stocks={stocks} ></StockGridCustomer>
                        </Grid>
                        }
                    </Grid>
                </GridItem>
            </Grid>
        </>
    )

}

export default CustomerHome;