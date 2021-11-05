import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import HeaderNavBar from './HeaderNavBar';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class Department extends Component {

    newDepartment = {
        name: '',
        employeeCount: 0
    };

    constructor(props) {
        super(props);
        this.state = {
            department: this.newDepartment
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const fetchedDepartment = await (await fetch(`/departments/${this.props.match.params.id}`)).json();
            this.setState({department: fetchedDepartment});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let department = {...this.state.department};
        department[name] = value;
        this.setState({department});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {department} = this.state;
        if(department.name.length < 2) {alert("name too short")}
        else{
            await fetch('/departments', {
                method: (department.id) ? 'PUT' : 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(department),
            });
            this.props.history.push('/departments');
        }
    }

    render() {
        const {department} = this.state;
        const title = <h2>{department.id ? 'Edit Department' : 'Add Department'}</h2>;

        return <div>
        <HeaderNavBar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit} autoComplete="off">
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={department.name}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    {department.employeeCount !== 0 &&
                        <Label for="employeeCount">Employees related: {department.employeeCount}</Label>}
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/departments">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(Department);