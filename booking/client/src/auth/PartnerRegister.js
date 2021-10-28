import React, {useState} from "react";
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


function PartnerRegister() {

    let history = useHistory();

    const classes = useStyles();
    /// const { updateToken } = useContext(AuthContext);

    const [values, setValues] = useState({
        name: "",
        email: "",
        password: "",
        longitude: "",
        latitude: "",
        imageUrl: "",
        address: ""
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

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log(localStorage.getItem('location').split(",")[0], localStorage.getItem('location').split(",")[1], "boxk")
            setValues({...values, "latitude": localStorage.getItem('location').split(",")[0]});
            setValues({...values, "longitude": localStorage.getItem('location').split(",")[1]});
            const response = await createPartner({
                ...values,
                "latitude": localStorage.getItem('location').split(",")[0],
                "longitude": localStorage.getItem('location').split(",")[1]
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

                    <TextField
                        name="address"
                        label="Address"
                        value={values.address}
                        onChange={handleInputValue}
                        className={classes.formField}
                        required
                        fullWidth
                    />

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