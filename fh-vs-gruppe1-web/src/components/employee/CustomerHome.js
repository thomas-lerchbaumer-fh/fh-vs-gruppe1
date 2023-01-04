import React, {useContext, useEffect} from 'react'
import SearchCustomer from "./forms/SearchCustomer";
import Grid from "@mui/material/Unstable_Grid2";
import Box from "@mui/material/Box";
import GridItem from "../layouts/GridItem";
import SearchStockForm from "../common/forms/SearchStockForm";
import StockGrid from "../common/StockGrid";
import StockContext from "../../context/stock/stockContext";


const CustomerHome = (props) => {
    const stockContext = useContext(StockContext);
    const {stocks, loadingStock} = stockContext;

    useEffect(() => {

    }, [loadingStock,stocks]);

    return (
        <>
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
        </>
    )

}

export default CustomerHome;