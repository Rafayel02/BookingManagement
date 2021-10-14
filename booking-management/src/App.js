import logo from './logo.svg';
import './App.css';
import axios from "axios";
import React, {useState, useEffect} from "react";

function App() {
    const [boxkik , setBoxkik ] = useState("");
     async function Meh(){
         const result = await axios.get(`http://localhost:5689/login`);
         console.log(result);
         setBoxkik(result);
     }

     useEffect(() => {
         Meh();
     }, [])
    return (
        <div className="App">
           <h1>{boxkik.data}</h1>
        </div>
    );
}

export default App;
