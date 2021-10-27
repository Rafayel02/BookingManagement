import React, { useEffect, useMemo, useState } from "react";
import {Link, Route, useLocation} from 'react-router-dom'
import { useParams } from "react-router-dom";
import Mockdata from './Mockdata'
import axios from "axios";

import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";


const Item = styled(Paper)(({ theme }) => ({
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));



function GetPartner(){

    const [value, setValues] = useState("");
    const [con, setCon] = useState("");
    const [reviews, setReviews] = useState("");
    const [rating, setRating] = useState("");

    const {pathname} = useLocation()

    const id = useMemo(() => pathname?.split("/")?.[2] || '', [pathname])

    
    
    // let pathArray = window.location.pathname.split("/");
    // let id1 = pathArray[2];
    
    // console.log(id, '****', id1)


const FetchPartner = async (e) =>{
     const response = await axios.get(`http://localhost:5689/filter/findu?id=${id}`);
    // console.log(response.data, "response data");
    setValues(response.data)
  }
// FetchPartner();
//   console.log(value);

  useEffect(()=>{
    //   console.log('aaaaaa')
    FetchPartner();
    // console.log(con, "idk isk")
  },[])


  const handleChange2 = (e) =>{
    setReviews(e.target.value)

  }
  const handleChange1 = (e) => {
    setRating(e.target.value)

  } 
  console.log(rating)
  console.log(reviews)

  const handleFormSubmit = async (e) => {
 
    e.preventDefault();
    return axios.post("http://localhost:5689/review", {
        "userUuid":"15045330-abfd-4b0c-849c-5cbae15a37e9",
        "partnerUuid": id ,
        "comment" : reviews,
        "rating": rating 
    });

  }

    return(
        <div  style = {{height:'1000px', position: 'static'}} >
            <div style = {{height:'550px',paddingLeft:'10px', paddingRight:'30px', paddingTop:'10px' ,display:'flex'}}>
            <div>
            <img src = {value.imageUrl} style = {{width:'100%', height:'400px'}} />
            </div>
             <div style = {{paddingRight: "20px"}}>
                 <h1> <b style = {{ paddingLeft:"80px"}}> {value.name} </b> </h1> 
                 <p>
                     <b> Contacts: </b> <br />
                       Address: {value.address}  <br /> 
                       Email: {value.email}  
                 </p>

             </div>

            </div>

      <div>
               
        <Box sx={{ flexGrow: 1 }}>
          <Grid container spacing={2}>
                <Grid item xs={8}>
                  View Reviews
                </Grid>
                <Grid item xs={4}>
                  <Item>


               
               <TextField
                 name="reviews"
                 label="Review"
                 value={reviews}
                 onChange={handleChange2}
                 required
                 multiline

               />
               {/* <Box component="fieldset" mb={3} borderColor="transparent"> */}
                 <Typography component="legend">Add Rating</Typography>
                
                  <Rating
                    name="simple-controlled"
                    value={rating}
                    onChange={handleChange1}
                   
                />
              
               <Button
                 type="submit"
                 fullWidth
                 variant="contained"
                 color= "error"
                 onClick = {handleFormSubmit}
               >
                 Add a Rating
               </Button>
              
                  </Item>
                </Grid>
           </Grid>
        </Box>
            </div>
        </div>
    )
 
}

export default GetPartner;