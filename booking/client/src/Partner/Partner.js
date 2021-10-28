import React, {useEffect} from 'react';
import {Link} from 'react-router-dom'
import classes from './Partner.module.css'

import {styled} from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import FormControl from '@mui/material/FormControl';
import FormHelperText from '@mui/material/FormHelperText';


import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import ListItemText from '@mui/material/ListItemText';
import Select from '@mui/material/Select';
import Checkbox from '@mui/material/Checkbox';

import axios from 'axios';


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


const Item = styled(Paper)(({theme}) => ({
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));


const category = [
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

function Partner(props) {

    const [change, setChange] = React.useState("1");

    let pathArray = window.location.pathname.split("/");
    let cat = [];
    let rad = [];
    let active = [];
    if (pathArray[2]) {
        cat = pathArray[2].split(',');
    }
    if (pathArray[3]) {
        rad = pathArray[3];
    }
    if (pathArray[4]) {
        active = pathArray[4].replaceAll('%20', " ").split(",")
    }
    const filt = props.val;

    const [radius, setRadius] = React.useState(rad);

    const [filtered, setfiltered] = React.useState(cat);
    const [activity, setActivity] = React.useState(active);
    const [values, setValues] = React.useState({
        category: []

    });

    const [cafes, setCafes] = React.useState();


    const handleChange = (event) => {
        const {
            target: {value},
        } = event;

        setfiltered(value);

        setValues({...values, cuisine: value});
    };

    const handleChange1 = (event) => {
        const {
            target: {value},
        } = event;

        setRadius(value);

        setValues({...values, cuisine: value});
    };


    const handleChange2 = (event) => {
        const {
            target: {value},
        } = event;

        setActivity(value);

        setValues({...values, cuisine: value});
    };

    console.log(filtered)
    console.log(radius);


    const handleSubmit = async (e) => {
        e.preventDefault();
        const {category} = values;
        console.log(values.cuisine);

        console.log(" Filter in Main : ", filtered);

        let a = [1, 2];
        // getFilteredData(filtered);

        const response = await axios.post("http://localhost:5689/filter",
            {
                "category": filtered,


            });
        console.log(response.data);
        setCafes(response)


    }

    const handleSubmit1 = async (e) => {
        // e.preventDefault();
        const {category} = values;
        console.log(values.cuisine);

        console.log(" Filter in Main : ", filtered);
        console.log(radius, "radius")
        let a = [1, 2];
        // getFilteredData(filtered);
        console.log(sessionStorage.getItem('location'), 'activity');
        sessionStorage.getItem('location')

        let locationArray = [
            sessionStorage.getItem('location') ? sessionStorage.getItem('location').split(',')[0] : 40.1777,
            sessionStorage.getItem('location') ? sessionStorage.getItem('location').split(',')[0] : 44.5127
        ]

        if (typeof radius!== 'object') {
            console.log("*******"+radius)
            locationArray=[...locationArray,radius]
        }

        console.log({
            "category": filtered,
            "activity": activity,
            "locationInfo": locationArray
        })

        const response = await axios.post("http://localhost:5689/filter",
            {
                "category": filtered,
                "activity": activity,
                "locationInfo": locationArray

            });
        console.log(response.data, 'response data');
        setCafes(response.data);


    }

// console.log(cafes.partnersList[0], "cafes");
    if (cafes)
        console.log(cafes.partnersList, "cafes blah blah")
    else {
        console.log(cafes, "dead");
    }


    let counter = 0;
    useEffect(() => {
        counter++;
        console.log("counter : ", counter)
        handleSubmit1();

    }, [change]);


    return (
        <Box sx={{flexGrow: 1}}>
            <Grid container spacing={2}>
                <Grid item xs={2}>
                    <Item style={{position: 'fixed', width: '15.5%'}}>
                        <div>
                            <FormControl sx={{m: 1, width: '80%', padding: 0, marginButtom: '200px'}}>
                                <InputLabel id="demo-multiple-checkbox-label">Cuisine</InputLabel>
                                <Select
                                    labelId="demo-multiple-checkbox-label"
                                    id="demo-multiple-checkbox"
                                    multiple
                                    value={filtered}
                                    onChange={handleChange}
                                    input={<OutlinedInput label="Tag"/>}
                                    renderValue={(selected) => selected.join(', ')}
                                    MenuProps={MenuProps}
                                >
                                    {category.map((name) => (
                                        <MenuItem key={name} value={name}>
                                            <Checkbox checked={filtered.indexOf(name) > -1}/>
                                            <ListItemText primary={name}/>
                                        </MenuItem>
                                    ))}
                                </Select>

                            </FormControl>
                            <FormControl sx={{m: 1, width: '80%', padding: 0, marginButtom: '200px'}}>
                                <InputLabel id="demo-multiple-checkbox-label">Activity</InputLabel>
                                <Select
                                    labelId="demo-multiple-checkbox-label"
                                    id="demo-multiple-checkbox"
                                    multiple
                                    value={activity}
                                    onChange={handleChange2}
                                    input={<OutlinedInput label="Tag"/>}
                                    renderValue={(selected) => selected.join(', ')}
                                    MenuProps={MenuProps}
                                >
                                    {act.map((name) => (
                                        <MenuItem key={name} value={name}>
                                            <Checkbox checked={activity.indexOf(name) > -1}/>
                                            <ListItemText primary={name}/>
                                        </MenuItem>
                                    ))}
                                </Select>

                            </FormControl>


                            <FormControl sx={{m: 1, width: '80%', padding: 0, marginButtom: '200px'}}>
                                <InputLabel id="demo-simple-select-readonly-label">Radius</InputLabel>
                                <Select
                                    labelId="demo-simple-select-readonly-label"
                                    id="demo-simple-select-readonly"
                                    value={radius}
                                    label="Age"
                                    onChange={handleChange1}
                                    inputProps={{readOnly: false}}
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


                            <button onClick={handleSubmit1}>FILTER</button>


                        </div>
                    </Item>
                </Grid>
                <Grid item xs={8}>
                    <Item>
                        <div className={classes.cafeItems}>
                            {cafes ? cafes.partnersList.map(cafe => (
                                <Link className={classes.poqr} to={`/partner/${cafe.id}`}>
                                    <div className={classes.cardWrapper} id={cafe.id} onClick={() => {
                                    }}>
                                        {
                                            cafe.imageUrl === 'default' ?
                                                <img src="https://www.kindpng.com/picc/m/201-2011704_restaurant-jd-sports-logo-png-transparent-png.png"
                                                     className={classes.cardImage}/> :
                                                <img src={cafe.imageUrl} className={classes.cardImage}/>
                                        }
                                        <p> {cafe.name} <br/>
                                            Rating: <b>{!!cafe.review ? cafe.review : "No rating yet"}</b> <br/>
                                            Address: {cafe.address} <br/>
                                        </p>

                                    </div>
                                </Link>

                            )) : <h1>No restaurants or cafes by specified filter</h1>
                            }
                            {/* <h1>  {cafes.partnersList[0]} </h1> */}
                        </div>
                    </Item>
                </Grid>
            </Grid>
        </Box>
    );


}

export default Partner;