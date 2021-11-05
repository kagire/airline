import React, { Component } from 'react';
import './App.css';
import HeaderNavBar from './HeaderNavBar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
    render() {
        return (
            <div>
                <HeaderNavBar/>
                <h3>Department app</h3>
                <Container fluid>
                    <Button color="link"><Link to="/departments">View departments</Link></Button>
                    <Button color="link"><Link to="/employees">View employees</Link></Button>
                </Container>
            </div>
        );
    }
}
export default Home;