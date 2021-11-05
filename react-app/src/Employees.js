import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import HeaderNavBar from './HeaderNavBar';
import { Link } from 'react-router-dom';

class Employees extends Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
        this.remove = this.remove.bind(this);
    }

    async componentDidMount() {
        await fetch('/employees')
            .then(response => response.json())
            .then(data => this.setState({employees: data}));

        /*var employeesN = this.state.employees.forEach(employee => {
            employee.departmentId = this.fetchDepartmentName(employee.departmentId)
            }
        )
        this.setState({employees: employeesN});

        console.log(this.state.employees);*/
    }

    /*async fetchDepartmentName(id){
        await fetch('/departments/' + id)
                    .then(response => response.json())
                    .then(data => {return data.name;})
    }*/

    async remove(id) {
        await fetch(`/employees/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedEmployees = [...this.state.employees].filter(i => i.id !== id);
            this.setState({employees: updatedEmployees});
        });
    }

    render() {
        const {employees, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const employeeList = employees.map(employee => {
            return <tr key={employee.id}>
                <td style={{whiteSpace: 'nowrap'}}>{employee.name}</td>
                <td>{new Date(employee.dateOfBirth).toISOString().split('T')[0]}</td>
                <td>{employee.salary}$</td>
                <td>{employee.departmentId}
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/employees/" + employee.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(employee.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return (
            <div>
            <HeaderNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/employees/new">Add Employee</Button>
                    </div>
                    <h3>Employees</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="30%">Name</th>
                                <th width="20%">Birthdate</th>
                                <th width="10%">Salary</th>
                                <th width="20%">Department</th>
                                <th width="20%">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        {employeeList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default Employees;