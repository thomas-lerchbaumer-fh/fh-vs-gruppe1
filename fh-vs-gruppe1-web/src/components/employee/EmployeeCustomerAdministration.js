import React, {useContext, useEffect} from 'react'
import Grid from "@mui/material/Unstable_Grid2";
import Box from "@mui/material/Box";
import GridItem from "../layouts/GridItem";
import StockContext from "../../context/stock/stockContext";
import CreateCustomer from "./forms/CreateCustomer";

const EmployeeCustomerAdministration = (props) => {
    useEffect(() => {}, );
    const stockContext = useContext(StockContext);
    const {stocks, loadingStock} = stockContext;

    useEffect(() => {

    }, [loadingStock,stocks]);
    return (
        <>
            <Grid item xs={12}> <GridItem>
                <Grid container>
                    <Grid item xs={12}>
                        <CreateCustomer></CreateCustomer>
                    </Grid>
                </Grid>
            </GridItem>
            </Grid>
        </>
    )
}

export default EmployeeCustomerAdministration;