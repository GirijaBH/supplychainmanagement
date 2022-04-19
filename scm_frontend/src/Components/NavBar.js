import React from 'react';
import { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import AuthService from "../Services/AuthService";
import Login from "./Login";
import Register from "./Register";
import Home from "./Home";
import UserProfile from "./UserProfile";
import AdminProfile from "./AdminProfile";
import EventBus from "../Common/EventBus";

const NavBar = () => {

    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
            setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
        }

        EventBus.on("logout", () => {
            logOut();
        });

        return () => {
            EventBus.remove("logout");
        };
    }, []);

    const logOut = () => {
        AuthService.logout();
        setShowAdminBoard(false);
        setCurrentUser(undefined);
    };



    return (

        <div>

            <nav className="navbar navbar-expand navbar-dark bg-dark" style={{ fontFamily: "sans-serif", fontSize:"20px", height:"40px"}}>
                <Link to={"/"} className="navbar-brand">
                    SupplyChainManagement
                </Link>
              
                    <p className="nav-item">
                        <Link to={"/home"} className="nav-link">
                            Home
                        </Link>
                    </p>
                    <p className="nav-item">
                        <Link to={"/AboutUs"} className="nav-link">
                            AboutUs
                        </Link>
                    </p>
                    <p className="nav-item">
                        <Link to={"/Contact"} className="nav-link">
                            Contact
                        </Link>
                    </p>
                    
                    <div>
                        {showAdminBoard && (
                            <p className="nav-item">
                                <Link to={"/AdminProfile"} className="nav-link">
                                    Admin Board
                                </Link>
                            </p>
                        )}
                    </div>
                    {currentUser ? (
                        <div className="navbar-nav ml-auto">
                            <p className="nav-item">
                                <Link to={"/profile"} className="nav-link">
                                    {currentUser.username}
                                </Link>
                            </p>
                            <p className="nav-item">
                                <a href="/login" className="nav-link" onClick={logOut}>
                                    LogOut
                                </a>
                            </p>
                        </div>
                    ) : (
                        <div className="navbar-nav ml-auto">
                            <p className="nav-item">
                                <Link to={"/login"} className="nav-link">
                                    Login
                                </Link>
                            </p>
                            <p className="nav-item">
                                <Link to={"/register"} className="nav-link">
                                    Sign Up
                                </Link>
                            </p>
                        </div>
                    )}
            </nav>
            <div className="container mt-3">
                <Routes>
                    <Route exact path={"/home"} element={<Home />} />
                    <Route exact path="/login" element={<Login />} />
                    <Route exact path="/register" element={<Register />} />
                    <Route exact path="/UserProfile" element={<UserProfile />} />
                    <Route exact path="/AdminProfile" element={<AdminProfile />} />
                </Routes>
            </div>
        </div>

    )
}


export default NavBar;