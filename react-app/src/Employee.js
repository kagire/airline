import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import HeaderNavBar from './HeaderNavBar';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class Employee extends Component {

    newEmployee = {
        id: 0,
        name: '',
        employeeCount: 0,
        dateOfBirth: new Date(),
        salary: 0,
        departmentId: 0
    };

    constructor(props) {
        super(props);
        this.state = {
            employee: this.newEmployee
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const fetchedEmployee = await (await fetch(`/employees/${this.props.match.params.id}`)).json();
            this.setState({employee: fetchedEmployee});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let employee = {...this.state.employee};
        employee[name] = value;
        this.setState({employee});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {employee} = this.state;
        var isOk = false;

        const departments = await fetch('/departments').then(response => response.json()).then(data => {return data;});
        departments.forEach(department => {if(department.id == employee.departmentId) {isOk = true}})

        if(employee.name.length < 2) {alert("Name too short")}
        else if(employee.salary === 0) {alert("Fulfill the fields");}
        else if(!isOk) alert("Wrong department " + isOk);
        else{
            await fetch('/employees', {
                method: (employee.id) ? 'PUT' : 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employee),
            });
            this.props.history.push('/employees');
        }
    }

    render() {
        const {employee} = this.state;
        const title = <h2>{employee.id ? 'Edit Employee' : 'Add Employee'}</h2>;


        var date = employee.dateOfBirth.getFullYear()+'-'+(employee.dateOfBirth.getFullMonth()+1)+'-'+employee.dateOfBirth.getFullDate();

        return <div>
        <HeaderNavBar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit} autoComplete="off">
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={employee.name}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dateOfBirth">Birthdate</Label>
                        <Input type="date" name="dateOfBirth" id="dateOfBirth"
                         value={date} onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="salary">Salary</Label>
                        <Input type="number" name="salary" id="salary" value={employee.salary}
                              onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="salary">Department</Label>
                        <Input type="number" name="departmentId" id="departmentId" value={employee.departmentId}
                              onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/employees">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(Employee);