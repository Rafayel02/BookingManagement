import React, { Component, useEffect, useState } from "react";
// import NavigationItems from '../../components/Navigation/NavigationItems/NavigationItems';
// import MainItems from './MainItems/MainItems';
// import Cafes from '../../components/Cafes/Cafes';
// import Footer from '../../components/Footer/Footer.js';
// import Text from '../../components/Text/Text.js';
import { Route, NavLink, Switch, Redirect } from "react-router-dom";
import classes from "./Main.module.css";
import NavigationItems from "../Navigation/NavigationItem";
import Search from "../Search/Search";
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from "react-leaflet";

function LocationMarker() {
  const [position, setPosition] = useState(null)
  const map = useMapEvents({
    click() {
      map.locate()
    },
    locationfound(e) {
      setPosition(e.latlng)
      map.flyTo(e.latlng, map.getZoom())
    },
  })

  return position === null ? null : (
    <Marker position={position}>
      <Popup>You are here</Popup>
    </Marker>
  )
}


const Main = () => {
  const [markerPosition, setMarkerPosition] = useState([40.17846144920184, 44.51170787724434])

  useEffect(() => {
    setTimeout(() => {
      setMarkerPosition([40.186133636747705, 44.52222213632417])
    }, 5000)
  }, [])


  return (
    <div>
      <Search />
      {/*<MapContainer*/}
      {/*onClick={console.log}*/}
      {/*  center={[40.17846144920184, 44.51170787724434]}*/}
      {/*  zoom={13}*/}
      {/*  scrollWheelZoom={false}*/}
      {/*  style = {  {  height:'400px',    width:'800px'}}*/}
      {/*>*/}
      {/*  <TileLayer*/}
      {/*    attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'*/}
      {/*    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"*/}
      {/*  />*/}
      {/*  <LocationMarker />*/}
      {/*</MapContainer>*/}
      {/*<Search />*/}
      {/*<Switch>*/}
      {/*  <Route path="/" exact component={Login} />*/}
      {/*  /!* <Route path="/filter" component={Cafes} /> *!/*/}

      {/*  <Route path="/login" component={Login} />*/}
      {/*  <Route path="/signup" component={Register} />*/}
      {/*</Switch>*/}
      {/* <Footer /> */}
    </div>
  )
}

export default Main;
