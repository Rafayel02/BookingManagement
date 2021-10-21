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


const useStyles = makeStyles(() => ({
    formField: {
        marginBottom: "2rem",
    },
    cardGrid: {
        width: "500px",
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
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await createPartner(values);
            localStorage.setItem("token", response.data);

            console.log(response.data);
            //  updateToken(response.data.token);
            //S  history.push("/main");
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
                        name="longitude"
                        label="Longitude"
                        value={values.longitude}
                        onChange={handleInputValue}
                        className={classes.formField}
                        required
                        fullWidth
                    />

                    <TextField
                        name="latitude"
                        label="Latitude"
                        value={values.latitude}
                        onChange={handleInputValue}
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

                    <Box>
                        <Button color="danger" variant="contained" type="submit">
                            Sign up
                        </Button>
                    </Box>
                </form>
            </CardContent>
        </Card>
    );
}

export default PartnerRegister;