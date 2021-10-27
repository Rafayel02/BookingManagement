import React, { useState } from "react";

export const AuthContext = React.createContext({
  token: {},
  updateToken: () => {},
  getToken: () => {},
});

const AuthContextProvider = (props) => {
  const [token, setToken] = useState(getToken());

  function getToken() {
    return localStorage.getItem("token");
  }

  const updateToken = (userToken) => {
    setToken(userToken);
    if (userToken === "") {
      localStorage.removeItem("token");
    } else {
      localStorage.setItem("token", userToken);
    }
  };

  return (
    <AuthContext.Provider value={{ token, updateToken, getToken }}>
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthContextProvider;
