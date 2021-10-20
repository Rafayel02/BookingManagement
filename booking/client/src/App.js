import logo from './logo.svg';
import './App.css';
import All from "./auth/All";
import React from "react";
import {
    BrowserRouter as Router,
} from "react-router-dom";
import axios from "axios";

function App() {
    return (
        <div className="App">
            <Router>
                <All/>
            </Router>
        </div>
    );
}

export default App;
