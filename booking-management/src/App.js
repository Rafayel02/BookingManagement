import logo from './logo.svg';
import './App.css';
import axios from "axios";
import React, {useState, useEffect} from "react";

function App() {
    const [boxkik, setBoxkik] = useState("");

    async function Meh() {
        const result = await axios.get(`http://localhost:5689/login`);
        console.log(result);
        setBoxkik(result);
    }

    const [test, setTest] = useState("")

    async function testFunc() {
        const result = await axios.post('http://localhost:5689/register?username=a&password=a');
        setTest(result);
    }

    useEffect(() => {
        Meh();
    }, [])
    return (
        <div className="App">
            <h1>{boxkik.data}</h1>
            <input type={"text"} placeholder={"username"}/><br/>
            <input type={"text"} placeholder={"password"}/><br/>
            <button onClick={testFunc}>register</button>
        </div>
    );
}

export default App;
