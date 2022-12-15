import React from 'react'
import SearchCustomer from "./forms/SearchCustomer";
import Grid from "@mui/material/Unstable_Grid2";
import Box from "@mui/material/Box";
import GridItem from "../layouts/GridItem";





const EmployeeHome = (props) =>{



    return(
        <>
            <Box width={"100%"}>
                <GridItem>
                <SearchCustomer></SearchCustomer>
                </GridItem>
            </Box>
        </>
    )

}


export default EmployeeHome;