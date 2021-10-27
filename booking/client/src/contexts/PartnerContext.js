import React, {createContext, useEffect, useState} from "react";
import axios from "axios";


export const PartnerContext = createContext({
  filt: {},
  getFilteredData: () => {},
  setFiltered: () => {}
});

const PartnerContextProvider = (props) => {
  const [filt, setFiltered] = useState([]);

const getFilteredData = (values) => {
   

   return setFiltered({
     values
    });
  }

//   useEffect(() => {
//     getFilteredData();
//   }, []);

  return (
    <PartnerContext.Provider
      value={{ filt, getFilteredData, setFiltered }}
    >
      {props.children}
    </PartnerContext.Provider>
  );
};

export default PartnerContextProvider;
