
import React from 'react';
import {Link, Route} from 'react-router-dom'
import { useParams } from "react-router-dom";


import Mockdata from './Mockdata'
import classes from './Partner.module.css'

function Partner(){
   const { cafeId }= useParams()
   console.log('cafeId', cafeId)

console.log(Mockdata[0])

return(
    

    <div className = {classes.cafeItems}>
        {
            Mockdata.map( cafe => (
                <Link className={classes.poqr}  to={`/${cafe.id}`} >
                <div className={classes.cardWrapper} id={cafe.id} onClick = {() => {
                //    history.push("partners/" + cafe.id);

                }}> 
                
                    <img src={cafe.logo}  className={classes.cardImage}/>
                  
                    <p> {cafe.name}  <br />
                        Review: <b>{cafe.review}</b> <br />
                        Address: {cafe.address} <br />
                        Contact: {cafe.phone}
                     </p>

                </div>
                </Link >

           ) )
        }

   <h1> hhhhhhhh</h1>
  </div>
);



}

export default Partner;