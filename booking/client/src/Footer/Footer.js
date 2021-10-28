import React from 'react';
import { Link } from 'react-router-dom';
import PartnerRegister from '../auth/PartnerRegister';
import classes from './Footer.module.css';

const headerItems = () => (
<div className={classes.footerItems}>
  {/* <div>
       <Link>
       <p>
       <PartnerRegister />
       </Link>
  </div>
  <div > */}
  <div style= {{alignItems:"center", paddingLeft:'100px'}}>
    <p> Office 14, 5th floor, Nord Business Center, 1 Northern Ave, Yerevan 0001 <br />
        +374 60 619828 <br />
        adobe@armenia.am</p>
    </div>

    <div >
      <Link to = { `/register/partner`} >
          Register as Partner
      </Link>
      </div>
  </div>
  // </div>
)

export default headerItems;
