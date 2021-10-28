import React, { Component, useEffect, useState, useContext } from 'react';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react';
import PlacesAutocomplete, {
  geocodeByAddress,
  getLatLng,
} from 'react-places-autocomplete';
import { PartnerContext } from '../contexts/PartnerContext';

export function MapContainer (props) {

  const {location , setLocation, setAddress} = useContext(PartnerContext);
  const [address, setaddress] = useState('')
const [showingInfoWindow, setshowingInfoWindow] = useState(false)
const [activeMarker, setactiveMarker] = useState({})
const [selectedPlace, setselectedPlace] = useState({})
const [mapCenter, setmapCenter] = useState({
  lat: 40.1777,
  lng:44.5127
})
 const  handleChange = address => {
   setaddress(address)


  };
 
  const handleSelect = address => {
    setaddress(address)
    geocodeByAddress(address)
      .then(results => getLatLng(results[0]))
      .then(latLng => {
        console.log('Success', latLng);
        setmapCenter(latLng)
        setLocation(latLng);
        // this.setState({ mapCenter: latLng });
      })
      .catch(error => console.error('Error', error));
      // sessionStorage.setItem("location", [mapCenter.lat , mapCenter.lng ])
      // localStorage.setItem("location", [mapCenter.lat , mapCenter.lng ])
      // setLocation([mapCenter.lat , mapCenter.lng])
      setAddress(address);


  };

   
 const handleSelectMarker = address => {
   
    console.log("i kinda have a hope of working")
  };

//  getImmediate = () => {
//     return 
// }
 const saveNow = async() => {
  let coordinates = await new Promise((resolve, reject) => 
  navigator.geolocation.getCurrentPosition(
    position => {
      const lat = JSON.stringify(position.coords.latitude);
      const long = JSON.stringify(position.coords.longitude);
      setmapCenter({lat: lat, lng: long})
      // this.setState({  mapCenter: {lat: lat, lng: long}})  

      resolve({ lat, long});
    },
    error => console.log(error.message),
    {
      enableHighAccuracy: true,
      timeout: 20000,
      maximumAge: 1000
    }
  )
);
console.log(mapCenter, "this map center")

// sessionStorage.setItem("location", [mapCenter.lat , mapCenter.lng ])
// localStorage.setItem("location", [mapCenter.lat , mapCenter.lng ])


}

useEffect( async () => {
  await saveNow()
  // setLocation([mapCenter.lat , mapCenter.lng])

}, [])

return (
  <div id='googleMaps' style = {{width:'10%', height:'10%'}}>
    <PlacesAutocomplete
      value={address}
      onChange={handleChange}
      onSelect={handleSelect}
    >
      {({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
        <div>
          <input
              style={{width:'448px', height:'30px'}}
            {...getInputProps({
              placeholder: 'Find your location',
              className: 'location-search-input',
            })}
          />
          <div className="autocomplete-dropdown-container" >
            {loading && <div>Loading...</div>}
            {suggestions.map(suggestion => {
              const className = suggestion.active
                ? 'suggestion-item--active'
                : 'suggestion-item';
              // inline style for demonstration purpose
              const style = suggestion.active
                ? { backgroundColor: '#fafafa', cursor: 'pointer' }
                : { backgroundColor: '#ffffff', cursor: 'pointer' };
              return (
                <div
                  {...getSuggestionItemProps(suggestion, {
                    className,
                    style,
                  })}
                >
                  <span>{suggestion.description}</span>
                </div>
              );
            })}
          </div>
        </div>
      )}
    </PlacesAutocomplete>
<div style ={{height:'230px'}} >
    <Map
        className='mapBox'
      google={props.google}
      initialCenter={{
        lat: mapCenter.lat,
        lng: mapCenter.lng
      }}
      center={{
        lat: mapCenter.lat,
        lng: mapCenter.lng
      }}
      //style = {{width:'31%', height:'30%'}}
    >
      <Marker 
       draggable={true}
       onChange={ handleSelectMarker }

        position={{
          lat: mapCenter.lat,
          lng: mapCenter.lng
        }} />
    </Map>
    </div>
  </div>
)
}

export default GoogleApiWrapper({
  apiKey: ('AIzaSyArFTpU0FOqVbNuDxScA0p0O2CGSd4Josc')
})(MapContainer)