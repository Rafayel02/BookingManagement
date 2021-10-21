import React from "react";
import {Link, Route} from 'react-router-dom'
import { useParams } from "react-router-dom";
import Mockdata from './Mockdata'


function GetPartner(){

    let data = Mockdata;
    let pathArray = window.location.pathname.split("/");
    let id = pathArray[2];
console.log("I was called");

    // const { cafeId }= useParams()
    // console.log('cafeId', cafeId)
//axios find by Id

    return(
        <div>
            <img src = {data[id-1].logo} />
            <div>
                
            </div>
        </div>
    )
 
}

export default GetPartner;