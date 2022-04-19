import React from "react";
import AuthService from "../Services/AuthService";
import {Link} from "react-router-dom";

const AdminProfile = () => {
    const currentUser = AuthService.getCurrentUser();

    return (
        <div className="container">
            <div>
                <h2 style={{backgroundColor:"lightblue", fontSize:"45px", textAlign:"center"}}>Admin Panel :</h2>
                <div className="container">
                    <div className="row">
                        <div className="col1">
                            <Link to={"/employeelist"} >
                                Manage  Employee
                            </Link>
                        </div>
                        <div className="col2"><Link to={"/products"} >
                            Manage  Products
                        </Link></div>
                        <div className="w-100"></div>
                        <div className="col3"><Link to={"/Delars"} >
                            Manage Dealers
                        </Link></div>
                        <div className="col4"><Link to={"/Orders"} >
                            Manage Orders
                        </Link></div>
                    </div>
                </div>
               
            </div>







        </div>
    );
};
export default AdminProfile;