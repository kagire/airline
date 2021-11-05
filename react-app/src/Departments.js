import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import HeaderNavBar from './HeaderNavBar';
import { Link } from 'react-router-dom';

class Departments extends Component {

    constructor(props) {
        super(props);
        this.state = {departments: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/departments')
            .then(response => response.json())
            .then(data => this.setState({departments: data}));
    }

    async remove(id) {
        await fetch(`/departments/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedDepartments = [...this.state.departments].filter(i => i.id !== id);
            this.setState({departments: updatedDepartments});
        });
    }
    
    render() {
        const {departments, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const departmentList = departments.map(department => {
            return <tr key={department.id}>
                <td style={{whiteSpace: 'nowrap'}}>{department.name}</td>
                <td>{department.employeeCount}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/departments/" + department.id}>Edit</Button>
                        {department.employeeCount === 0 &&
                        <Button size="sm" color="danger" onClick={() => this.remove(department.id)}>Delete</Button>}
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return (
            <div>
            <HeaderNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/departments/new">Add Department</Button>
                    </div>
                    <h3>Departments</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="50%">Name</th>
                                <th width="10%">Employees</th>
                                <th width="40%">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        {departmentList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default Departments;