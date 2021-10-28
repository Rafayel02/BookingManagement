import * as React from 'react';
import { useHistory } from "react-router-dom";
import { useContext } from "react";
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import ListItemText from '@mui/material/ListItemText';
import Select from '@mui/material/Select';
import Checkbox from '@mui/material/Checkbox';
import FormHelperText from '@mui/material/FormHelperText';

import axios from 'axios';
import {
  Switch,
  Route,
  Link,
  Redirect
} from "react-router-dom";

import {PartnerContext  } from '../contexts/PartnerContext'
import { AuthContext } from '../contexts/AuthContext';
import Partner from '../Partner/Partner';
import MapComponent from '../Maps/MapComponent';
import GoogleMaps from '../Maps/GoogleMaps';

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const names = [
  'italian',
  'mexican',
  'chinese',
  'japanese',
  'russian',
  'armenian'

];
const act = [
  'with friends',
  'with family',
  'for couples',
  'birthday',
  'work & study'
]
export default function MultipleSelectCheckmarks() {

  const { getFilteredData, filt } = useContext(PartnerContext);

  const { token } = useContext(AuthContext)
  const [location, setLocation] = React.useState([40.1777,44.5127]);

  const [filtered, setfiltered] = React.useState([]);
  const [radius, setRadius] = React.useState([]);

  const [activity, setActivity] = React.useState([]);


  const [values, setValues] = React.useState({
    category:[]

  });

  const handleChange = (event) => {
    const {
      target: { value },
    } = event;

    setfiltered(value);

    setValues({...values, cuisine:value});
  };

  const handleChange1 = (event) => {
    const {
      target: { value },
    } = event;

    setRadius(value);

    setValues({...values, cuisine:value});
  };
  const handleChange2 = (event) => {
    const {
      target: { value },
    } = event;

    setActivity(value);

    setValues({...values, cuisine:value});
  };


  let history = useHistory();
  const handleSubmit = async (e) =>{
    e.preventDefault();
    const {category} = values;
    console.log(values.cuisine);

    console.log(" Filter in Main : ", filtered);

    let a = [1,2];
    // getFilteredData(filtered);
    history.push( `filter/${filtered}/${radius}/${activity}/${location}`)
    const response = await axios.post("http://localhost:5689/filter", {"category": filtered});
    console.log(response.data);


  }

  const saveNow = async() => {
    let coordinates = await new Promise((resolve, reject) =>
        navigator.geolocation.getCurrentPosition(
            position => {
              const lat = JSON.stringify(position.coords.latitude);
              const long = JSON.stringify(position.coords.longitude);
              setLocation([lat, long])
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
  }

  React.useEffect(() => {
    saveNow();
  },[])






  return (
      <div>
        <div style = {{width: '50%', height:'50%', display: 'flex'}}>
          <FormControl sx={{ m: 1, width: '50%' }}>
            <InputLabel id="demo-multiple-checkbox-label">Tag</InputLabel>
            <Select
                labelId="demo-multiple-checkbox-label"
                id="demo-multiple-checkbox"
                multiple
                value = {filtered}
                onChange={handleChange}
                input={<OutlinedInput label="Tag" />}
                renderValue={(selected) => selected.join(', ')}
                MenuProps={MenuProps}
            >
              {names.map((name) => (
                  <MenuItem key={name} value={name}>
                    <Checkbox checked={filtered.indexOf(name) > -1} />
                    <ListItemText primary={name} />
                  </MenuItem>
              ))}
            </Select>


          </FormControl>
          <FormControl sx={{ m: 1, width:'50%', padding:0, marginButtom : '200px' }}>
            <InputLabel id="demo-multiple-checkbox-label">Activity</InputLabel>
            <Select
                labelId="demo-multiple-checkbox-label"
                id="demo-multiple-checkbox"
                multiple
                value = {activity}
                onChange={handleChange2}
                input={<OutlinedInput label="Tag" />}
                renderValue={(selected) => selected.join(', ')}
                MenuProps={MenuProps}
            >
              {act.map((name) => (
                  <MenuItem key={name} value={name}>
                    <Checkbox checked={activity.indexOf(name) > -1} />
                    <ListItemText primary={name} />
                  </MenuItem>
              ))}
            </Select>

          </FormControl>



          <FormControl sx={{ m: 1, minWidth: 120 }}>
            <InputLabel id="demo-simple-select-readonly-label">Radius</InputLabel>
            <Select
                labelId="demo-simple-select-readonly-label"
                id="demo-simple-select-readonly"
                value={radius}
                label="Age"
                onChange={handleChange1}
                inputProps={sessionStorage.getItem("location") ? {readOnly: false} : {readOnly: false}}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              <MenuItem value={1}>1km Radius</MenuItem>
              <MenuItem value={2}>2km Radius</MenuItem>
              <MenuItem value={5}>5km Radius</MenuItem>
              <MenuItem value={100}>100km Radius</MenuItem>
            </Select>
            <FormHelperText>{sessionStorage.getItem("location") ? "" : "Add Location"}</FormHelperText>
          </FormControl>
          <div style = {{padding: '20px'}}>
            {/* <Link to = { `partner/${filtered}`}> */}
            <button  onClick={handleSubmit}>FILTER</button>
            {/* </Link> */}
          </div>



        </div>
        <div className = {{width: '20px', height: '50px'}}>
          {/* <MapComponent /> */}
          {/* <GoogleMaps /> */}
          {location[0]}
          tox em bac toxel
          {location[1]}
        </div>
      </div>
  );
}
