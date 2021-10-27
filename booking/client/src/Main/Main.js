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

export default function MultipleSelectCheckmarks() {

  const { getFilteredData, filt } = useContext(PartnerContext);

  const { token } = useContext(AuthContext)

  const [filtered, setfiltered] = React.useState([]);
  const [radius, setRadius] = React.useState([]);

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


  let history = useHistory();
  const handleSubmit = async (e) =>{
    e.preventDefault();
    const {category} = values;
    console.log(values.cuisine);

    console.log(" Filter in Main : ", filtered);

    let a = [1,2];
    // getFilteredData(filtered);
    history.push( `filter/${filtered}/${radius}`)
   const response = await axios.post("http://localhost:5689/filter", {"category": filtered});
    console.log(response.data);


  }

 console.log(radius);

  return (
    <div>
      <div style = {{width: '100%', height:'100%'}}>
        <FormControl sx={{ m: 1, width: 300 }}>
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
          <MenuItem value={10}>10km Radius</MenuItem>
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
          {/*<GoogleMaps />*/}
        </div>
      </div>
  );
}
