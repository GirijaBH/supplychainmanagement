import axios from "axios";


const base_url = "http://localhost:9090/scm/api";



//const AccessToken=JSON.parse(localStorage.getItem("user")).token;
const AccessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb29qYV9nIiwiaWF0IjoxNjQ5Nzg2Mjk0LCJleHAiOjE2NDk4NzI2OTR9.ts-CUp3A0dRd6d4MW4D8rUedfZ2kQNKn5selIdeLlgCXFFNP0gx8agg79-t1OVcmzaPOgbm80jjC3ZgOqhs87g";
const authAxios = axios.create({
    baseURL: base_url,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${AccessToken}`
    }
});

const getEmployees = () => {
    return authAxios.get(base_url + "/admin/emp/all");
}


const addEmployee = (emp) => {
    return authAxios.post(base_url+ "/admin/emp/add",emp);
};

const getById = (id) => {
    return authAxios.get(base_url+ "/admin/emp" + id);
};

const updateEmployee = (emp) => {
    return authAxios.put(base_url+ "/admin/emp/" + emp.empid, emp);

};

const deleteEmployee = (empid) => {
    return authAxios.delete(base_url + "/admin/emp/" + empid);
}

const EmployeeService = {
    getEmployees,
    addEmployee,
    getById,
    updateEmployee,
    deleteEmployee,

}

export default EmployeeService;