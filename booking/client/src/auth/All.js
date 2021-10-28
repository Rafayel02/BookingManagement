import React, {useContext} from "react";
import PartnerLogin from "./PartnerLogin";
import UserLogin from "./UserLogin";
import PartnerRegister from "./PartnerRegister";
import UserRegister from "./UserRegister";
import Main from "../Main/Main";
import PartnerContexProvider from '../contexts/PartnerContext'
import {Route, Switch} from "react-router-dom";
import NavigationItems from "../Navigation/NavigationItem";
import Partner from "../Partner/Partner";
import PartnerProfile from "../profiles/PartnerProfile";
import Footer from "../Footer/Footer"
import {AuthContext} from "../contexts/AuthContext";
import jwt from 'jwt-decode'
import UserProfile from "../profiles/UserProfile";
import PartnerProfileFromOutSide from "../profiles/PartnerProfileFromOutSide";

const All = () => {
    const {token} = useContext(AuthContext)

    return <div>
        <NavigationItems/>
        <Switch>

            <Route path="/filter">
                <PartnerContexProvider>
                    <Partner/>
                </PartnerContexProvider>
            </Route>

            <Route path="/login/partner">
                <PartnerContexProvider>
                    {!token ? <PartnerLogin/> : <h1>Not found</h1>}
                </PartnerContexProvider>
            </Route>

            <Route path="/login">
                {!token ? <UserLogin/> : <h1>Not found</h1>}
            </Route>

            <Route path="/register/partner">
                {!token ? <PartnerRegister/> : <h1>Not found</h1>}
            </Route>

            <Route path="/register">
                {!token ? <UserRegister/> : <h1>Not found</h1>}
            </Route>

            <Route path="/profile">
                {
                    getPayload(token) ?
                        getPayload(token).role === "ROLE_USER" ?
                            <UserProfile/>
                            : getPayload(token).role === "ROLE_PARTNER" ?
                                <PartnerProfile/>
                                : <h1>Not Found</h1>
                        : <h1>Not Found</h1>
                }
            </Route>

            <Route path="/partner" component={PartnerProfileFromOutSide}/>

            <Route path="/">
                <PartnerContexProvider>
                    <Main/>
                </PartnerContexProvider>
                <Footer/>
            </Route>

        </Switch>

    </div>
}

function getPayload(token) {
    if (token) {
        console.log(jwt(token));
        return jwt(token)
    }
    return null
}

export default All;