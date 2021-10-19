import React from 'react';
import NavigationItem from './NavigationItem/NavigationItem';
import classes from './NavigationItem.module.css';
// import logo from '../../../assets/logo.png';
import {  NavLink } from 'react-router-dom';

const NavigationItems = () => (
  <div className={classes.dist}>
    <ul className={classes.NavigationItems}>
    <NavigationItem><NavLink to="/">Home</NavLink></ NavigationItem>
    <NavigationItem><NavLink to="/filter"> Restaurants & Cafes </NavLink></ NavigationItem>

    <img src={"https://i.pinimg.com/564x/be/5c/9a/be5c9abb9f508c3f61381f724c8ca75a.jpg"}  />

    <NavigationItem><NavLink to="/register"> Sign Up </NavLink> </ NavigationItem>
    <NavigationItem><NavLink to="/login"> Login </NavLink> </ NavigationItem>
    <NavigationItem><NavLink to="/contact"> Contact </NavLink> </ NavigationItem>
    </ul>

  </div>
)

export default NavigationItems;