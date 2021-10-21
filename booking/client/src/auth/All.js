import React from "react";
import PartnerLogin from "./PartnerLogin";
import UserLogin from "./UserLogin";
import PartnerRegister from "./PartnerRegister";
import UserRegister from "./UserRegister";
import Main from "../Main/Main";

import {
    Switch,
    Route,
    Redirect
} from "react-router-dom";
import NavigationItems from "../Navigation/NavigationItem";
import Partner from "../Partner/Partner";

const All = () => {
    return <div>
        <NavigationItems />
        <Switch>
        <Route path="cafes/:cafeId" exact component={Partner} />
        <Route path="/filter"  component={Partner} />


            <Route path="/login/partner">
                <PartnerLogin/>
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
            <Route path="/">
                <Main/>
            </Route>
            <Redirect to='/' />

        </Switch>

    </div>
}

export default All;