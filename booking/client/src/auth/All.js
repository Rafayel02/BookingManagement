import React from "react";
import PartnerLogin from "./PartnerLogin";
import UserLogin from "./UserLogin";
import PartnerRegister from "./PartnerRegister";
import UserRegister from "./UserRegister";

import {
    Switch,
    Route,
} from "react-router-dom";

const All = () => {
    return <div>
        <Switch>
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
                a
            </Route>
        </Switch>
    </div>
}

export default All;