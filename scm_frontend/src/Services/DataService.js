import axios from "axios";
const API_URL = "http://localhost:3000/api/test/";

const getPublicContent = () =>{
    return axios.get(API_URL+"all");
};

const getUserContent = ()=>{
    return axios.get(API_URL+"user");
};

const getAdminContent = ()=>{
    return axios.get(API_URL+"admin");
};

const UserService = {
    getPublicContent,
    getUserContent,
    getAdminContent,
}

export default UserService;