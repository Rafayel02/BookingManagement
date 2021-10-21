
import React from 'react';
import {Link, Route} from 'react-router-dom'
import { useParams } from "react-router-dom";
import Mockdata from './Mockdata'
import classes from './Partner.module.css'

import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';

import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import ListItemText from '@mui/material/ListItemText';
import Select from '@mui/material/Select';
import Checkbox from '@mui/material/Checkbox';
import Button from '@mui/material/Button';


const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 210,
    },
  },
};



const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));


const category = [
    'italian',
    'mexican',
    'armenian',
    'syrian',
    'Meh',
    'Meh2 ',
    'Meh3',
    'Bradley Wilkerson',
    'Virginia Andrews',
    'Kelly Snyder',
  ];
  const ratingArr = [
      '5 star',
      '4> rating',
     
  ]

function Partner(){

    const [personName, setPersonName] = React.useState([]);
    const [cuisine, setCuisine] = React.useState([]);
    const [rating, setRating] = React.useState([]);

    // const [values , setValue] = useState({
    //     cuisine:""
    // })


  

    const handleChange = (event) => {
      const {
        target: { value },
      } = event;
      setCuisine(
         typeof value === 'string' ? value.split(',') : value,
      );
    };
    
    const handleChange1 = (event) => {
        const {
          target: { value },
        } = event;
        setRating(
           typeof value === 'string' ? value.split(',') : value,
        );
      };

  

  
      
console.log(rating)

return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={2}>
        <Grid item xs={2} >
          <Item style = {{position: 'fixed', width:'15.5%'}}>
              <div>
                <FormControl sx={{ m: 1, width:'80%', padding:0, marginButtom : '200px' }}>
                        <InputLabel id="demo-multiple-checkbox-label">Cuisine</InputLabel>
                        <Select
                        labelId="demo-multiple-checkbox-label"
                        id="demo-multiple-checkbox"
                        multiple
                        value={cuisine}
                        onChange={handleChange}
                        input={<OutlinedInput label= "Cuisine" />}
                        renderValue={(selected) => selected.join(', ')}
                        MenuProps={MenuProps}
                        >
                        {category.map((v) => (
                            <MenuItem key={v} value={v}>
                            <Checkbox checked={cuisine.indexOf(v) > -1} />
                            <ListItemText primary={v} />
                            </MenuItem>
                        ))}
                            </Select>
                            </FormControl>
             
               
                <FormControl sx={{ m: 1, width:'80%', padding:0, marginButtom : '200px' }}>

                    <InputLabel id="demo-multiple-checkbox-label">Rating</InputLabel>
                    <Select
                    labelId="demo-multiple-checkbox-label"
                    id="demo-multiple-checkbox"
                    multiple
                    value={rating}
                    onChange={handleChange1}
                    input={<OutlinedInput label= "Rating" />}
                    renderValue={(selected) => selected.join(', ')}
                    MenuProps={MenuProps}
                    >
                    {ratingArr.map((name) => (
                    <MenuItem key={name} value={name}>
                    <Checkbox checked={rating.indexOf(name) > -1} />
                    <ListItemText primary={name} />
                    </MenuItem>
                    ))}
                    </Select>
                    </FormControl>
                    <FormControl sx={{ m: 1, width:'80%', padding:0, marginButtom : '200px' }}>
                    <RadioGroup
                    aria-label="Location"

                    name="radio-buttons-group"
                    >
                    <FormControlLabel value="Bird's-eye View" control={<Radio />} label="Bird's eye view" />
                    <FormControlLabel value="1km radius" control={<Radio />} label="1km radius" />
                    <FormControlLabel value="3km radius" control={<Radio />} label="3km radius" />
                    <FormControlLabel value="5km radius" control={<Radio />} label="5km radius" />
                    <FormControlLabel value="none" control={<Radio />} label="none" />


                    </RadioGroup>
</FormControl>

             </div>
          </Item>
        </Grid>
        <Grid item xs={8}>
          <Item> 
              <div className = {classes.cafeItems}>

                 {
            Mockdata.map( cafe => (
                <Link className={classes.poqr}  to={`partner/${cafe.id}`} >
                <div className={classes.cardWrapper} id={cafe.id} onClick = {() => {
                //    history.push("partners/" + cafe.id);

                }}> 
                
                    <img src={cafe.logo}  className={classes.cardImage}/>
                  
                    <p> {cafe.name}  <br />
                        Review: <b>{cafe.review}</b> <br />
                        Address: {cafe.address} <br />
                        Contact: {cafe.phone}
                     </p>

                </div>
                </Link >

           ) )
        }
        </div>
        </Item>
        </Grid>
        </Grid>
        </Box>
);






}

export default Partner;