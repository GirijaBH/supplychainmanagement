import React from "react";
import EmployeeService from "../../Services/EmployeeService";
import { Link } from "react-router-dom";
import "./emplist.css";


class EmployeeList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [],
            flag: false

        }

        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);

    }



    editEmployee = (empid) => {
        console.log("in emplist edit emp")
        this.props.history.push(`/add/${empid}`)
    }
    deleteEmployee = (empid) => {
        EmployeeService.deleteEmployee(empid).then((response) => {
            //modify the the value of flag to true
            this.setState({ flag: true });

        })
    }
    componentDidUpdate(prevProps, prevState) {

        if (this.state.flag === true) {
            console.log("in employeelist componentDidupdate");
            EmployeeService.getEmployees().then((response) => {
                this.setState({ flag: false })

            });

        }
    }

    componentDidMount() {
        EmployeeService.getEmployees().then((res) => {
            this.setState({ employees: res.data });
            console.log(res);
        });
    }


    render() {
        return (
            <div className="list" style={{fontSize:"15px",fontWeight:"bold" }}>
                <h2 className="text-center">Employees List</h2>
                <div className="row" style={{marginLeft:"5px"}}>
                    <Link to="/emp/_add"><button className="btn btn-primary" size="sm"> Add Employee</button></Link>
                </div>
                <br></br>
                <div className="row">
                    <table className="table table-striped table-bordered">

                        <thead>
                            <tr>
                                <th>  First Name</th>
                                <th>  Last Name</th>
                                <th>  Email Id</th>
                                <th>  Salary</th>
                                <th>  Join Date</th>
                                <th>  Department </th>
                                <th> Address</th>
                                <th> Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.employees.map(
                                    employee =>
                                        <tr key={employee.id}>
                                            <td> {employee.firstName} </td>
                                            <td> {employee.lastName}</td>
                                            <td> {employee.email}</td>
                                            <td> {employee.salary} </td>
                                            <td> {employee.joinDate}</td>
                                            <td> {employee.department.departmentName}</td>
                                            <td> {employee.address}</td>
                                            <td>
                                                <button onClick={() => this.editEmployee(employee.id)} className="btn btn-info" style={{ marginLeft: "10px", size: "50%" }} >Update </button>
                                                <button style={{ marginLeft: "20px", size: "50%" }} onClick={() => this.deleteEmployee(employee.id)} className="btn btn-danger" >Delete </button>

                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>

                </div>


            </div>
        );
    }
}


export default EmployeeList;