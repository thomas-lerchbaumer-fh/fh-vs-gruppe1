import React, {useContext, useEffect} from 'react'
import Grid from "@mui/material/Unstable_Grid2";
import Box from "@mui/material/Box";
import GridItem from "../layouts/GridItem";
import StockContext from "../../context/stock/stockContext";
import CreateAccount from "./forms/CreateAccount";

const AccountAdministration = (props) => {
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
                        <CreateAccount></CreateAccount>
                    </Grid>
                </Grid>
            </GridItem>
            </Grid>
        </>
    )
}

export default AccountAdministration;