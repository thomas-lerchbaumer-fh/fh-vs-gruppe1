import React, { useContext } from 'react';
import AlertContext from "../../context/alert/alertContext";
import {Alert} from "@mui/material";


const AlertInfo = () =>{
    const alertContext = useContext(AlertContext);
    return (
        alertContext.alerts.length > 0 &&
        alertContext.alerts.map(alert => (
            <Alert key={alert.id} severity={`${alert.type}`}>
                {alert.msg}
            </Alert>
        ))
    );
}

export default AlertInfo