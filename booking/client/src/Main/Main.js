import React, {Component} from 'react';
// import NavigationItems from '../../components/Navigation/NavigationItems/NavigationItems';
// import MainItems from './MainItems/MainItems';
// import Cafes from '../../components/Cafes/Cafes';
// import Footer from '../../components/Footer/Footer.js';
// import Text from '../../components/Text/Text.js';
import { Route, NavLink, Switch, Redirect } from 'react-router-dom';
import classes from './Main.module.css';
import NavigationItems from '../Navigation/NavigationItem';
import Search from '../Search/Search';

class Main extends Component {
  render() {
    return(
      <div>  
        <Search/>
        {/*<Search />*/}
        {/*<Switch>*/}
        {/*  <Route path="/" exact component={Login} />*/}
        {/*  /!* <Route path="/filter" component={Cafes} /> *!/*/}

        {/*  <Route path="/login" component={Login} />*/}
        {/*  <Route path="/signup" component={Register} />*/}
        {/*</Switch>*/}
        {/* <Footer /> */}
      </div>
  );
  }
}

export default Main;
