import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../contexts/AuthContext";
import axios from "axios";
import jwt from "jwt-decode";

function UserProfile() {
    const {token} = useContext(AuthContext)
    const [userInfo, setUserInfo] = useState("");
    const [userReviewHistory, setUserReviewHistory] = useState("");

    const getUserInfo = async () => {
        const userInfoResponse
            = await axios.get(`http://localhost:5689/info?id=${getPayload(token).id}`);
        setUserInfo(userInfoResponse.data)
    };

    useEffect(() => {
        getUserInfo();
    }, [])

    const getReviewHistory = async () => {
        const userReviewHistory
            = await axios.get(`http://localhost:5689/history?id=${getPayload(token).id}`);
        setUserReviewHistory(userReviewHistory.data)
    }

    useEffect(() => {
        getReviewHistory();
    }, [])

    return (
        <div style={{display: "flex", justifyContent: 'space-between'}}>
            <div style={{textAlign: 'left', width: '50%'}}>
                <h1 style={{textAlign: 'center'}}>Profile</h1>
                <div style={{display: "flex", justifyContent: "space-around", alignItems: "center"}}>
                    <img style={{height: '120px', borderRadius: '40px'}}
                         src={"https://www.un.org/sites/un2.un.org/files/user.png"}/>
                    <div style={{height: "100%"}}>
                        <h4>First Name: {userInfo.firstName}</h4>
                        <h4>Last Name: {userInfo.lastName}</h4>
                        <h4>Email: {userInfo.email}</h4>
                    </div>
                </div>
            </div>
            <div style={{textAlign: 'right', width: '50%'}}>
                <h1 style={{textAlign: 'center'}}>History</h1>
                {userReviewHistory.length ? userReviewHistory.map(val => (
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
    );

    function getPayload(token) {
        if (token) {
            console.log(jwt(token));
            return jwt(token)
        }
        return null
    }

}

export default UserProfile;
