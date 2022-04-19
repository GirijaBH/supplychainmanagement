import React, { Component } from "react";
import { Form, Button } from 'react-bootstrap';
import { Navigate, useNavigate, useParams } from "react-router-dom";

import EmployeeService from "../../Services/EmployeeService"
import "./emplist.css";


class EmployeeAdd extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            // for add url /add/_add it will retrive _add from url 
            //or for update url will /add/1234 employee id receiving via url
              ///either _add or actual empid
            firstName: "",
            lastName: "",
            email:"",
            salary: "",
            joinDate: "",
            departmentName: [],
            address:""
        }


    }
    componentDidMount(){
        console.log("in emp componentDidMount");
    }
       

    addOrUpdate = (event) => {
        event.preventDefault(); // to stop the refreshing the page
        console.log(this.state)
        //create a objevt using form data to send it spring sertvice to add in the database
        const emp = {
             firstName: this.state.firstName, lastName: this.state.lastName,email: this.state.email, salary: this.state.salary,
            joinDate: this.state.joinDate, departmentName: this.state.departmentName, address:this.state.address
        };

            EmployeeService.addEmployee(emp).then((result) => {
                console.log(result.data);
                this.props.Navigate("/");
            })
    }
    render() {
        return (
            <div className="empform">
                <h1>Add Employee Details</h1>
                <Form>

                    <Form.Group className="mb-3" controlId="formBasicename">
                        <Form.Label>First Name</Form.Label>
                        <Form.Control type="text" placeholder="enter name"
                            value={this.state.firstName}
                            onChange={(event) => this.setState({ firstName: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicename">
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control type="text" placeholder="enter name"
                            value={this.state.lastName}
                            onChange={(event) => this.setState({ lastName: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicsal">
                        <Form.Label>Email ID:</Form.Label>
                        <Form.Control type="text" placeholder="Enter email"
                            value={this.state.email}
                            onChange={(event) => this.setState({ email: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicsal">
                        <Form.Label>Salary</Form.Label>
                        <Form.Control type="text" placeholder="Enter salary"
                            value={this.state.salary}
                            onChange={(event) => this.setState({ salary: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicsal">
                        <Form.Label>Join Date</Form.Label>
                        <Form.Control type="date" placeholder="Enter salary"
                            value={this.state.joinDate}
                            onChange={(event) => this.setState({ joinDate: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicsal">
                        <Form.Label>Department</Form.Label>&nbsp;&nbsp;
                        <select className="form-select" onChange={(event) => this.setState({ category: event.target.value })} >
                            <option value="1">PRODUCTION</option>
                            <option value="2">HR</option>
                            <option value="3">SALES</option>
                            <option value="4">MARKETING</option>
                            <option value="5">PURCHASING</option>
                        </select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicsal">
                        <Form.Label>Address</Form.Label>
                        <Form.Control type="text" placeholder="Enter address"
                            value={this.state.address}
                            onChange={(event) => this.setState({ address: event.target.value })} />
                    </Form.Group>
                    <Button variant="primary" type="submit" onClick={this.addOrUpdate}>
                        Add or Update Employee
                    </Button>
                </Form>
            </div>
        )

    }
}

export default (props)=>(
    <EmployeeAdd {...props}
    params={useParams()}  Navigate={useNavigate()} />
);