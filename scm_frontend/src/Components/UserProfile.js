import React from "react";
import AuthService from "../Services/AuthService";

const UserProfile = () => {
    const currentUser = AuthService.getCurrentUser();

    return (
        <div className="container">
            <header className="jumbotron">
                <h3>
                    <strong>User Profile</strong>
                </h3>
            </header>
            <strong>User Authorities:</strong>
            
            

        </div>
    );
};
export default UserProfile;