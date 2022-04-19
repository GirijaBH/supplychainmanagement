import axios from "axios";
const base_url = "http://localhost:9090/scm/api/admin";

//const AccessToken=JSON.parse(localStorage.getItem("user")).token;
const AccessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb29qYV9nIiwiaWF0IjoxNjQ5NzcxNjM5LCJleHAiOjE2NDk4NTgwMzl9.qZVAwXsmd3FuYRv1S6iaQAeSbzwa3dQgeFhlDZplRgxSs8cdYws5tlVDeq0CDrtehcw2UwK_-tmUASeeI5w4nQ";
const authAxios = axios.create({
    baseURL: base_url,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${AccessToken}`
    }
}) 

const getAllProducts = () => {
    return authAxios.get(base_url + "/products");
}


const getById = (pid) => {
    return authAxios.get(base_url + "/product/" + pid)
}

const updateProduct = (product) => {
    return authAxios.put(base_url + "/products/" + product.pid, product, {
        headers: { 'Content-Type': 'application/json' }
    });
}


const deleteProduct = (pid) => {
    return authAxios.delete(base_url + "/products/" + pid);
}

const findByName = (name) => {
    return authAxios.get(base_url + "/products/" + name);
}

const addProduct = (data) => {
    return authAxios.post(base_url + "/admin/product/add", { data }, {
        headers: {
            "Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryKJVOzvuo4zCEQuf3",
            'Authorization': `Bearer ${AccessToken}`
        }

    });
}

const ProductService = {
    findByName,
    deleteProduct,
    updateProduct,
    getAllProducts,
    getById,
    addProduct,
}

export default ProductService;