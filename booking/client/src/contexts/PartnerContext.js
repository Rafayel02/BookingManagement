import React, {createContext, useEffect, useState} from "react";
import axios from "axios";


export const PartnerContext = createContext({
  location: {},
  address: "",
  setAddress: () => {},
  getFilteredData: () => {},
  setLocation: () => {}
});

const PartnerContextProvider = (props) => {
  const [location, setLocation] = useState([]);
  const [address, setAddress] = useState("");

const getFilteredData = (values) => {
   

   return setLocation({
     values
    });
  }



  return (
    <PartnerContext.Provider
      value={{ location, getFilteredData, setLocation, address, setAddress}}
    >
      {props.children}
    </PartnerContext.Provider>
  );
};

export default PartnerContextProvider;
