import React, { useContext, useEffect, useMemo, useState } from "react";
import { useLocation } from 'react-router-dom'
import axios from "axios";

import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { AuthContext } from "../contexts/AuthContext";
import jwt_decode from "jwt-decode";

const Item = styled(Paper)(({ theme }) => ({
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));


function GetPartner() {

    const { token } = useContext(AuthContext);
    let decoded;
    let userId;
    let role;
    if (token) {
        decoded = jwt_decode(token);
        userId = decoded.id;
        role = decoded.role;
    }
    console.log(decoded);
    const [all, setAll] = useState([])
    const [value, setValues] = useState("");
    const [con, setCon] = useState("");
    const [reviews, setReviews] = useState("");
    const [rating, setRating] = useState("");
    const [len, setLength] = useState(0);

    const [errorMessage, setErrorMessage] = useState("");


    const { pathname } = useLocation()

    const partnerId = useMemo(() => pathname?.split("/")?.[2] || '', [pathname])

    const FetchPartner = async (e) => {
        const response = await axios.get(`http://localhost:5689/info/partner?id=${partnerId}`);
        setValues(response.data)
    }


    const GetReviews = async (e) => {
        const response = await axios.get(`http://localhost:5689/history/partner?id=${partnerId}`);
        setAll(response.data)
        console.log(response.data)

    }

    // console.log(all[0].id, 'all')    
    useEffect(() => {
        FetchPartner();
    }, [])

    useEffect(() => {
        GetReviews();
    }, [con])

    useEffect(() => {
        checkUser();
    }, [con])
    const handleChange2 = (e) => {
        setReviews(e.target.value)

    }
    const handleChange1 = (e) => {
        setRating(e.target.value)

    }
    console.log(rating)
    console.log(reviews)

    const checkUser =() =>{
        let a = [];
       if(all && userId){
        console.log(all, 'all')
         a = all.filter( a =>  a.id ==  userId)
         console.log(a , "a");
        
       }
       setLength(a.length);
    }

    const handleFormSubmit = async (e) => {

        e.preventDefault();
        GetReviews();
        // window.location.reload();
     try{

        setCon(reviews);
        return axios.post("http://localhost:5689/review", {
            "userId": userId,
            "partnerId": partnerId,
            "comment": reviews,
            "rating": rating
        });
    }catch(e) {
        console.log(e, "error")
        setErrorMessage(e.response?.data?.message || "Something went wrong");
    }
    }

    return (

        <div style={{display:'flex', width:'100%'}}>
             <div>
                 <img src={value.imageUrl} style={{ width: '100%', height: '400px' }} />
             </div>

             <div style ={{width:"74%"}}>
                 <div style= {{width: '100%'}}>
                     <h2 style={{color:'red', textAlign:'center'}}> {value.name} </h2> <br />
                     <p> Address: {value.address} <br />
                         Email: {value.email}
                     
                      </p>

                 </div>

                 <div style = {{display:'flex', justifyContent: 'space-between', width:'100%'}}> 
                        <div>
                            <h1>{errorMessage ? errorMessage : ""} </h1>
                             <h4> Reviews </h4>
                            {/* {!!all ? <p> {all.map(val => {
                                <p> {val.id} </p>
                            })} </p> : <p> Doesn't exist </p>} */}
                            {all.length ? all.map(val =>(
                                    <div id={val.id} style={{ display: 'flex',  borderStyle:'ridge' , padding:'2px'}}>
                                        <div style={{ width: '70%', height: "80%" , display:'flex'}}>
                                             {val.comment} 
                                        </div>
                                        <div style={{ width: '20%', height: "20%" , color:'red'}}>
                                           {val.rating} 
                                        </div>
                                    </div>

                                )): (< div ><p> No reviews yet </p></div>)}

                        </div>
                        <div>
                            <h4> Add Review </h4>
                            {(userId && role === 'ROLE_USER' && len==0) ? (
                      
                            <Item>

                                <TextField
                                    name="reviews"
                                    label="Review"
                                    value={reviews}
                                    onChange={handleChange2}
                                    required
                                    multiline

                                />
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
                                    color="error"
                                    onClick={handleFormSubmit}
                                >
                                    Add a Rating
                                </Button>

                            </Item>
                          ) : 
                          <div>
                          <p>Only signed in users can leave a review</p>
                          </div>
                          }
                    </div>
                 </div>
             </div>





        </div>
    // <div style={{ height: '10px', position: 'static' }}>
    //      <div style={{
    //             height: '550px',
    //             paddingLeft: '10px',
    //             paddingRight: '30px',
    //             paddingTop: '10px',
    //             display: 'flex'
    //         }}>
    //                 <div>
    //                     <img src={value.imageUrl} style={{ width: '100%', height: '400px' }} />
    //                 </div>
    //                 <div style={{ paddingRight: "20px" }}>
    //                       <div>
    //                             <h1><b style={{ paddingLeft: "80px" }}> {value.name} </b></h1>
    //                             <p>
    //                                 <b> Contacts: </b> <br />
    //                                 Address: {value.address} <br />
    //                                 Email: {value.email}
    //                             </p>
    //                       </div>

    //                       <div style= {{display:'flex'}}>
    //                             <div>
    //                         {!!all ? <p> {all.map(val => {
    //                             <p> {val.id} </p>
    //                              })} </p> : <p> Doesn't exist </p>}
    //                                      {all.length ? all.map(val =>(
    //                                 <div id={val.id} style={{ width: '40%', height: '10%', display: 'flex', backgroundColor:'grey' }}>
    //                                     <div style={{ width: '70%', height: "80%" , display:'flex'}}>
    //                                         <p> {val.comment}  </p>
    //                                     </div>
    //                                     <div style={{ width: '20%', height: "20%" }}>
    //                                         <p> {val.rating} </p>
    //                                     </div>
    //                                 </div>

    //                             )): (< div ><p> No reviews yet </p></div>)}
    //                             </div>

    //                       </div>
                        
    //                 </div>
    //         </div>




    //         <div>




    //             <Box sx={{ flexGrow: 1 }}>
    //                 <Grid container spacing={2}>
    //                     <Grid item xs={8}>
    //                         here
    //                         {!!all ? <p> {all.map(val => {
    //                             <p> {val.id} </p>
    //                         })} </p> : <p> Doesn't exist </p>}
    //                         {all.length ? all.map(val =>(
    //                                 <div id={val.id} style={{ width: '40%', height: '10%', display: 'flex', backgroundColor:'#909090' }}>
    //                                     <div style={{ width: '70%', height: "80%" , display:'flex'}}>
    //                                         <p> {val.comment}  </p>
    //                                     </div>
    //                                     <div style={{ width: '20%', height: "20%" }}>
    //                                         <p> {val.rating} </p>
    //                                     </div>
    //                                 </div>

    //                             )): (< div ><p> No reviews yet </p></div>)}
    //                 </Grid>
    //                 {(userId && role === 'ROLE_USER') ? (
    //                     <Grid item xs={4}>
    //                         <Item>

    //                             <TextField
    //                                 name="reviews"
    //                                 label="Review"
    //                                 value={reviews}
    //                                 onChange={handleChange2}
    //                                 required
    //                                 multiline

    //                             />
    //                             <Typography component="legend">Add Rating</Typography>

    //                             <Rating
    //                                 name="simple-controlled"
    //                                 value={rating}
    //                                 onChange={handleChange1}
    //                             />

    //                             <Button
    //                                 type="submit"
    //                                 fullWidth
    //                                 variant="contained"
    //                                 color="error"
    //                                 onClick={handleFormSubmit}
    //                             >
    //                                 Add a Rating
    //                             </Button>

    //                         </Item>
    //                     </Grid>) : <h1>Only signed in users can leave a review</h1>}
    //             </Grid>
    //         </Box>
    //        </div>
    //  </div >
    )

}

export default GetPartner;