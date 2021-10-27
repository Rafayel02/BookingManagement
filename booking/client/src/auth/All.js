import React, {useEffect, useState} from "react";
import PartnerLogin from "./PartnerLogin";
import UserLogin from "./UserLogin";
import PartnerRegister from "./PartnerRegister";
import UserRegister from "./UserRegister";
import Main from "../Main/Main";
import PartnerContexProvider from  '../contexts/PartnerContext'

import {
    Switch,
    Route,
    Redirect, useLocation
} from "react-router-dom";
import NavigationItems from "../Navigation/NavigationItem";
import Partner from "../Partner/Partner";
import GetPartner from "../Partner/GetPartner";
import Footer from "../Footer/Footer"

const All = () => {
    return <div>
        <NavigationItems />
        <Switch>
            <Route path="/filter">
            <PartnerContexProvider>
                <Partner />
            </PartnerContexProvider>
            </Route>


            <Route path="/login/partner">
            <PartnerContexProvider>
                <PartnerLogin/>
            </PartnerContexProvider>

            </Route>
            <Route path="/login">
                <UserLogin/>
            </Route>
            <Route path="/register/partner">
                <PartnerRegister/>
            </Route>
            <Route path="/register">
                <UserRegister/>
            </Route>
            <Route path = "/partner"  component = {GetPartner} />

            <Route path="/">
                <PartnerContexProvider>
                <Main/>
                </PartnerContexProvider>
            </Route>

            <Redirect to='/' />

        </Switch>
        <Footer />

    </div>
}

export default All;