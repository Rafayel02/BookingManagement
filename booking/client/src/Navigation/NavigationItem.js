import React, {useContext, useEffect} from 'react';
import NavigationItem from './NavigationItem/NavigationItem';
import classes from './NavigationItem.module.css';
// import logo from '../../../assets/logo.png';
import {NavLink, useHistory} from 'react-router-dom';

import {AuthContext} from '../contexts/AuthContext';

function NavigationItems() {

    let history = useHistory();

    const {token, updateToken, getToken} = useContext(AuthContext);
    // const [token, setToken] = useState(localStorage.getItem("token"));
    // if(token){
    //    // axios.defaults.headers.common["authorization"] = `Bearer ${token}`;
    //  }
    function logout() {
        updateToken("");
        history.push("/login")

    }

    useEffect(() => {

        function checkUserData() {
            const item = localStorage.getItem('token');
            if (item) {
                updateToken(item)
            }
        }

        window.addEventListener('storage', checkUserData)
        return () => {
            window.removeEventListener('storage', checkUserData)
        }
    }, [token]);
    console.log(token)
    return (
        <div className={classes.dist}>
            {!token ?
                <ul className={classes.NavigationItems}>
                    <NavigationItem><NavLink to="/">Home</NavLink></ NavigationItem>
                    <NavigationItem><NavLink to="/filter"> Restaurants & Cafes </NavLink></ NavigationItem>
                    <NavigationItem><NavLink to="/register"> Sign Up </NavLink> </ NavigationItem>
                    <NavigationItem><NavLink to="/login"> Login </NavLink> </ NavigationItem>
                </ul>
                :
                <ul className={classes.NavigationItems}>
                    <NavigationItem><NavLink to="/">Home</NavLink></ NavigationItem>
                    <NavigationItem><NavLink to="/filter"> Restaurants & Cafes </NavLink></ NavigationItem>

                    <img src={"https://i.pinimg.com/564x/be/5c/9a/be5c9abb9f508c3f61381f724c8ca75a.jpg"}/>
                    <NavigationItem>
                        <NavLink to="/profile"> My profile </NavLink>{" "}
                    </NavigationItem>
                    <NavigationItem>
                        <NavLink to="/" onClick={logout}> Log Out </NavLink>{" "}
                    </NavigationItem>
                </ul>
            }

        </div>);
}


export default NavigationItems;