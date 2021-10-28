import React, { useContext, useState } from "react";
import { useHistory } from "react-router-dom";
import Card from "@material-ui/core/Card";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import CardContent from "@material-ui/core/CardContent";
import TextField from "@material-ui/core/TextField";
import FormHelperText from "@material-ui/core/FormHelperText";

import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";

import classik from './Search.module.css'


const useStyles = makeStyles(() => ({
    formField: {
      marginBottom: "2rem",
    },
    cardGrid: {
      width: "500px",
    },
  }));

  
  function Search() {


    let history = useHistory();

    const classes = useStyles();
   /// const { updateToken } = useContext(AuthContext);
  
    const [values, setValues] = useState({
      search: "",
      location: "",
    });
    const [errorMessage, setErrorMessage] = useState("");
  
    const handleInputValue = (e) => {
      const { name, value } = e.target;
      setValues({
        ...values,
        [name]: value,
      });
    };
  
    const handleFormSubmit = async (e) => {
      e.preventDefault();
      console.log(values.search);

      <h1> Hola</h1>
    //   try {
    //     const response = await createUser(values);
    //     localStorage.setItem("token", response.data);

    //     console.log(response.data);
    //   //  updateToken(response.data.token);
    //  //S  history.push("/main");
    //   } catch (e) {
    //       console.log(e);
    //     setErrorMessage(e.response?.data?.message || "Something went wrong");
    //   }
    console.log("here");
      data =  await data.filter(a => {
            if(a.indexOf(values.search) > -1){
              
              return true;
            }
            return false
          })
          // console.log(arr);
      
          // for (const i in arr) {
          //  <div> iijjj</div>
           
              
          //   }
          //   console.log("here");
            values.search = ""
          }
    
  
    const createUser = async (values) => {
      return axios.post("http://localhost:5689/login", values);
    };
  
    return (
      <>
       <div style ={{alignItems:"center", paddingLeft:'25%', marginTop:"2%"}}>
         
          <form onSubmit={handleFormSubmit} className={classik.forma}>
            <FormHelperText error={true}>{errorMessage || " "}</FormHelperText>
           
            <TextField
              name="search"
              label="Search"
              value={values.search}
              onChange={handleInputValue}
              className={classes.formField}
              style = {{width:"30%", borderRight:"2px solid grey", paddingRight:"12px", marginRight:"12px"}}
              fullWidth
            />
          
             <TextField
              name="location"
              label="Location"
              value={values.location}
              style = {{width:"30%"}}
              onChange={handleInputValue}
              className={classes.formField}
             
              fullWidth
            />
          
           <Button type="submit">
                Search
              </Button>
         
          </form>
          </div>
       <div> 
           {data}
       </div>
    </>
    );




  }

  export default Search;