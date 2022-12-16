import React, {useContext, useEffect, useState} from 'react';
import EmployeeContext from "../../../context/employee/employeeContext";
import List from "@mui/material/List";
import {Collapse, ListItem} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import ListItemText from "@mui/material/ListItemText";
import TripOriginIcon from '@mui/icons-material/TripOrigin';
import Divider from "@mui/material/Divider";
import AlertContext from "../../../context/alert/alertContext";
import Grid from "@mui/material/Unstable_Grid2";
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import DepotGridEmployee from "../../layouts/DepotGridEmployee";


const DisplayCustomers = (props) => {
    const alertContext = useContext(AlertContext);
    const {setAlert} = alertContext;


    const [expandedId, setExpandedId] = React.useState(-1);
    const handleExpandClick = i => {
        setExpandedId(expandedId === i ? -1 : i);
    };

    const employeeContext = useContext(EmployeeContext);
    const {searchCustomer, loadingEmp, customers} = employeeContext;

    useEffect(() => {
        if(!loadingEmp){
            if(customers.length === 0){
                setAlert('No user found','error')
            }
        }

        //es-lint-disable-next-line
    }, [customers,loadingEmp]);


    return (
        <>
            {customers &&
                <>
                    <Divider></Divider>
                <List >
                    {customers.map((customer, i) => (
                        <Box>
                        <ListItem
                            key={i}
                            disableGutters
                            aria-expanded={expandedId === i}
                            secondaryAction={
                                <IconButton aria-label="comment" onClick={() => handleExpandClick(i)} p={1}>
                                    <Typography variant={"subtitle1"}>View Depot</Typography>
                                    <AccountBalanceWalletIcon/>
                                </IconButton>
                            }
                        >
                            <ListItemText primary={`${customer.email}`}/>
                            <ListItemText primary={`${customer.firstName} ${customer.surname}`}/>

                        </ListItem>

                        <Collapse
                        in={expandedId === i}
                        timeout='auto'
                        unmountOnExit
                        >
                       {/*<DepotGridEmployee depot={customer.depot}></DepotGridEmployee>*/}
                        </Collapse>
                        </Box>

                    ))}

                </List>

               </>

            }
        </>
    )

}


export default DisplayCustomers;