import React, {useContext, useEffect, useMemo, useState} from "react";
import {useLocation} from 'react-router-dom'
import axios from "axios";

import {styled} from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import {AuthContext} from "../contexts/AuthContext";
import jwt_decode from "jwt-decode";
import jwt from "jwt-decode";
import {TextField, Typography} from "@material-ui/core";
import {Rating} from "@material-ui/lab";
import Button from "@material-ui/core/Button";

const Item = styled(Paper)(({theme}) => ({
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));


function PartnerProfileFromOutSide() {

    const {token} = useContext(AuthContext);
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

    const {pathname} = useLocation()

    let partnerId = useMemo(() => pathname?.split("/")?.[2] || '', [pathname])

    const FetchPartner = async (e) => {
        console.log("*************" + partnerId)
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

    const checkUser = () => {
        let a = [];
        if (all && userId) {
            console.log(all, 'all')
            a = all.filter(a => a.id == userId)
            console.log(a, "a");

        }
        setLength(a.length);
    }

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        GetReviews();
        try {

            setCon(reviews);
            return axios.post("http://localhost:5689/review", {
                "userId": userId,
                "partnerId": partnerId,
                "comment": reviews,
                "rating": rating
            });
        } catch (e) {
            console.log(e, "error")
            setErrorMessage(e.response?.data?.message || "Something went wrong");
        }
    }

    return (
        <div style={{display: "flex", justifyContent: 'space-between', flexDirection: 'column'}}>
            <div style={{display: "flex", justifyContent: 'space-between'}}>
                <div style={{textAlign: 'left', width: '50%'}}>
                    <h1 style={{textAlign: 'center'}}>Restaurant Info</h1>
                    <div style={{display: "flex", justifyContent: "space-around", alignItems: "center"}}>
                        {value.imageUrl === 'default' ?
                            <img
                                src="https://www.kindpng.com/picc/m/201-2011704_restaurant-jd-sports-logo-png-transparent-png.png"
                                style={{height: '200px', borderRadius: '40px'}}/> :
                            <img src={value.imageUrl} style={{height: '200px', borderRadius: '40px'}}/>
                        }
                        <div style={{height: "100%"}}>
                            <h4>Name: {value.name}</h4>
                            <h4>Address: {value.address}</h4>
                            <h4>Email: {value.email}</h4>
                        </div>
                    </div>
                    {
                        token ?
                            getPayload(token).role === "ROLE_PARTNER" ?
                                <div/> :
                                <div style={{
                                    display: "flex",
                                    justifyContent: "center",
                                    flexDirection: "column",
                                    alignItems: "center",
                                    marginTop: '50px'
                                }}>
                                    <br/>
                                    <h2> Add Review </h2>
                                    {(userId && role === 'ROLE_USER' && len === 0) ? (
                                            <Item>
                                                <TextField style={{marginTop: '-30px'}}
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
                                                <br/>
                                                <Button style={{width: '200px'}}
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
                            :
                            <div/>
                    }
                </div>
                <div style={{textAlign: 'right', width: '50%'}}>
                    <h1 style={{textAlign: 'center'}}> Reviews </h1>
                    {all.length ? all.map(val => (
                        <div id={val.id} style={{
                            margin: ' 0 auto',
                            width: '80%',
                            display: 'flex',
                            borderStyle: 'ridge',
                            padding: '2px'
                        }}>
                            <div style={{width: '70%', height: "80%", display: 'flex'}}>
                                {val.comment}
                            </div>
                            <div style={{width: '20%', height: "20%", color: 'red'}}>
                                {val.rating}
                            </div>
                        </div>

                    )) : (<h4 style={{marginRight: '330px'}}>No reviews yet </h4>)}
                </div>
            </div>
        </div>
    )

    function getPayload(token) {
        if (token) {
            console.log(jwt(token));
            return jwt(token)
        }
        return null
    }

}

export default PartnerProfileFromOutSide;