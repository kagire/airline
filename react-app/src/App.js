import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import Department from './Department';
import Departments from './Departments';
import Employee from './Employee';
import Employees from './Employees';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {

  // code is currently in VERY bad condition

  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/departments' exact={true} component={Departments}/>
            <Route path='/departments/:id' exact={true} component={Department}/>
            <Route path='/employees' exact={true} component={Employees}/>
            <Route path='/employees/:id' exact={true} component={Employee}/>
          </Switch>
        </Router>
    )
  }
}

export default App;