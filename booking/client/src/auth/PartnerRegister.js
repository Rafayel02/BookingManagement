import React, {useContext, useState} from "react";
import {useHistory} from "react-router-dom";
import Card from "@material-ui/core/Card";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import CardContent from "@material-ui/core/CardContent";
import TextField from "@material-ui/core/TextField";
import FormHelperText from "@material-ui/core/FormHelperText";

import {makeStyles} from "@material-ui/core/styles";
import axios from "axios";
import GoogleMaps from "../Maps/GoogleMaps";
import {PartnerContext} from "../contexts/PartnerContext";
import FormControl from '@mui/material/FormControl';


import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import ListItemText from '@mui/material/ListItemText';
import Select from '@mui/material/Select';
import Checkbox from '@mui/material/Checkbox';

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


const useStyles = makeStyles(() => ({
    formField: {
        marginBottom: "2rem",
    },
    cardGrid: {
        width: "500px",
        height: '100%',
        margin: "auto",
        marginTop: 10
    },
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


function PartnerRegister() {

    let history = useHistory();

    const {location, address} = useContext(PartnerContext);
    console.log(location, 'location')
    console.log(address, 'address');

    const classes = useStyles();
    /// const { updateToken } = useContext(AuthContext);

    const [filtered, setfiltered] = React.useState([]);
    const [activity, setActivity] = React.useState([]);

    const [values, setValues] = useState({
        name: "",
        email: "",
        password: "",
        longitude: "",
        latitude: "",
        imageUrl: null,
        address: "",
        partnerCategories: [],
        partnerActivities: []
    });
    const [errorMessage, setErrorMessage] = useState("");

    const handleInputValue = (e) => {
        const {name, value} = e.target;
        setValues({
            ...values,
            [name]: value,
        });

        console.log(name)
    };

    const handleChange1 = (event) => {

        const {
            target: {value},
        } = event;

        setfiltered(value);
    }

    const handleChange2 = (event) => {

        const {
            target: {value},
        } = event;

        setActivity(value);
    }


    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {
            // setValues({...values, "latitude": localStorage.getItem('location').split(",")[0]});
            // setValues({...values, "longitude": localStorage.getItem('location').split(",")[1]});
            const response = await createPartner({
                ...values,
                "latitude": location.lat,
                "longitude": location.lng,
                "address": address,
                "partnerCategories": filtered,
                "partnerActivities": activity

            });
            localStorage.setItem("token", response.data.token);
            console.log(response.data);
            window.location.href = '/'
        } catch (e) {
            console.log(e);
            setErrorMessage(e.response?.data?.message || "Something went wrong");
        }
    };

    const createPartner = async (values) => {
        return axios.post("http://localhost:5689/register/partner", values);
    };

    return (
        <Card className={classes.cardGrid}>
            <CardContent>
                <form onSubmit={handleFormSubmit}>
                    <FormHelperText error={true}>{errorMessage || " "}</FormHelperText>
                    <h1>Sign Up Partner</h1>
                    <TextField
                        name="name"
                        label="Name"
                        value={values.name}
                        onChange={handleInputValue}
                        className={classes.formField}
                        required
                        fullWidth
                    />
                    <TextField
                        name="email"
                        label="Email"
                        value={values.email}
                        onChange={handleInputValue}
                        className={classes.formField}
                        required
                        fullWidth
                    />

                    <TextField
                        name="password"
                        value={values.password}
                        onChange={handleInputValue}
                        type="password"
                        label="Password"
                        className={classes.formField}
                        required
                        fullWidth
                    />

                    <TextField
                        name="imageUrl"
                        label="Image url"
                        value={values.imageUrl}
                        onChange={handleInputValue}
                        className={classes.formField}
                        fullWidth
                    />

                    <FormControl sx={{m: 1, height: '100%', width: '100%', marginLeft: '-2px'}}>
                        <InputLabel id="demo-multiple-checkbox-label">Cuisine</InputLabel>
                        <Select
                            labelId="demo-multiple-checkbox-label"
                            id="demo-multiple-checkbox"
                            multiple
                            value={filtered}
                            onChange={handleChange1}
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
                    <FormControl sx={{m: 1, height: '100%', width: '100%', marginLeft: '-2px'}}>
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
                    <div style={{height: '25px'}}>

                    </div>
                    <GoogleMaps/>

                    <Box>
                        <Button color="primary" variant="contained" type="submit">
                            Sign Up
                        </Button>
                    </Box>
                </form>
            </CardContent>
        </Card>
    );
}

export default PartnerRegister;