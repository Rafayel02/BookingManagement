import logo from './logo.svg';
import './App.css';
import All from "./auth/All";
import React from "react";
import { Route } from 'react-router-dom'
import axios from 'axios';

import {
    BrowserRouter as Router,
} from "react-router-dom";
let id =100;
function App() {




    const token  = localStorage.getItem('token');

    if(token){
        axios.defaults.headers.common["authorization"] = `Bearer`+token;
        console.log('here');
    }
    return (
        <div className="App">
            
            <Router>
                <All/>
                {/* <Route render={({ history}) => (
                  <button
                    type='button'
                    onClick={() => { history.push('/new-location/'+{id}) }}
                  >
                    Click Me!
                     </button>
                     )} /> */}
            </Router>
        </div>
    );
}

export default App;
