import React, { useContext } from 'react';
import AlertContext from "../../context/alert/alertContext";
import {Alert, Snackbar} from "@mui/material";


const AlertInfo = () =>{
    const alertContext = useContext(AlertContext);

    const [open, setOpen] = React.useState(true);

    const handleClick = () => {
        setOpen(true);
    };

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpen(false);
    };

    return (
        alertContext.alerts.length > 0 &&
        alertContext.alerts.map(alert => (
            <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
            <Alert key={alert.id} severity={`${alert.type}`}>
                {alert.msg}
            </Alert>
            </Snackbar>
        ))
    );
}

export default AlertInfo