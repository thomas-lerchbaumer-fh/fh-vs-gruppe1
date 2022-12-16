import React from 'react'
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";


const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(4),
    textAlign: 'center',
    elevation: 2,
    color: theme.palette.text.secondary,
    height:"100%",
    alignItems:"center"
}));


const GridItem = (props) =>{

    return(
        <Item elevation={7}>
            {props.children}
        </Item>
    )


}


export default GridItem;