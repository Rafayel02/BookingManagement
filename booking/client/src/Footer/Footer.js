import React from 'react';
import {Link} from 'react-router-dom';
import classes from './Footer.module.css';

const headerItems = ({token}) => (
    <div className={classes.footerItems}>
        <div style={{alignItems: "center", margin: 'auto'}}>
            <p> Office 14, 5th floor, Nord Business Center, 1 Northern Ave, Yerevan 0001 <br/>
                +374 60 619828 <br/>
                adobe@armenia.am</p>
            {
                token ? '' :
                    <div>
                        <Link to={`/register/partner`}>
                            Register as Partner
                        </Link>
                        <br/>
                        <Link to={`/login/partner`}>
                            Login as Partner
                        </Link>
                    </div>
            }
        </div>


        <div>
            <Link to={`/register/partner`}>
                Register as Partner
            </Link>
        </div>
    </div>

)

export default headerItems;
