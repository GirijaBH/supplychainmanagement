import axios from "axios";
const url = "http://localhost:9090/scm/api/auth";


//for signup new user 
const register = (username, email, password) => {
    return axios.post(url + "/signup", {
        username,
        email,
        password,
    });
};

//for login of existing user
const login = (username, password) => {
    return axios.post(url + "/signin", {
        username,
        password,
    }).then((response) => {
        if (response.data.username) {
            localStorage.setItem("user", JSON.stringify(response.data));

        }
        return response.data;
    });
};

const logout = () => {
    localStorage.removeItem("user");
    return axios.post(url + "/signout").then((response) => {
        return response.data;
    });
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
}

export default AuthService;
