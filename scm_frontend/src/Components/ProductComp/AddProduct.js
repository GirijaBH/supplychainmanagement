import React from 'react';
import { Form, Button } from 'react-bootstrap';
// import { useParams } from "react-router-dom";
import ProductService from '../../Services/ProductService';
import './product.css';
import axios from 'axios';
class AddProduct extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            productName: "",
            brand: "",
            stock: "",
            description: "",
            price: "",
            category: "",
            file: null
        }

    }


    handleFile = (event) => {

        console.log(event.target.file, "$$$");
        console.log(event.target.files[0], "$$$");
        console.log(JSON.parse(localStorage.getItem("user")).token);
        let file = event.target.files[0];
        this.setState({ file: file });


    }

    onSubmit = (event) => {
        event.preventDefault(); // to stop the refreshing the page
        console.log(this.state)
        console.log(this.state.file);
        //create a objevt using form data to send it spring sertvice to add in the database


        const {
            productName, brand, stock, description, price, category
        } = this.state;

        let file = this.state.file;
        let formdata = new FormData();
        //const AccessToken = JSON.parse(localStorage.getItem("user")).token;
        formdata.append('file', file);
        formdata.append("productName", productName);
        formdata.append("productName", brand);
        formdata.append("productName", stock);
        formdata.append("productName", description);
        formdata.append("productName", price);
        formdata.append("productName", category);

          
        // formdata.append('product',product);
        // formdata.append('name', "Product Img");
        // const url = 'http://localhost:9090/scm/api/admin';
        // axios.post(url + "/product/add",
        //     {
        //         product, formdata
        //     }).then((res) => {
        //         console.log(res);
        //     }).catch((err) => {
        //         console.log(err);
        //     })
        // axios({
        //     method: "POST",
        //     url: `http://localhost:9090/scm/api/admin/product/add`,
        //     data: formdata,
        //     headers: {
        //         "Content-Type": "multipart/form-data",
        //         'Authorization': `Bearer ${AccessToken}`
        //     },
        // }).then((res) => {
        //     <div>file Uploaded</div>
        // }).catch((err) => {
        //     <div>somthing went wrong.</div>
        // })



        // }
    }

    render() {

        return (

            <div>
                <Form className='addprod'>

                    <Form.Group className="mb-3" controlId="formBasicpname">
                        <Form.Label>First Name</Form.Label>
                        <Form.Control type="text" placeholder="enter product name"
                            value={this.state.productName}
                            onChange={(event) => this.setState({ productName: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicbrand">
                        <Form.Label>brand</Form.Label>
                        <Form.Control type="text" placeholder="enter brand"
                            value={this.state.brand}
                            onChange={(event) => this.setState({ brand: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicstock">
                        <Form.Label>Stock</Form.Label>
                        <Form.Control type="number" placeholder="Enter stock"
                            value={this.state.stock}
                            onChange={(event) => this.setState({ stock: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicdescription">
                        <Form.Label>Description</Form.Label>
                        <Form.Control type="text" placeholder="Enter description"
                            value={this.state.description}
                            onChange={(event) => this.setState({ description: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicprice">
                        <Form.Label>Price</Form.Label>
                        <Form.Control type="number" placeholder="Enter price"
                            value={this.state.price}
                            onChange={(event) => this.setState({ price: event.target.value })} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasiccat">
                        <Form.Label>Category  </Form.Label>&nbsp;&nbsp;
                        {/* <Form.Control
                            value={this.state.category}
                            onChange={(event) => this.setState({ category: event.target.value })} /> */}
                        <select className="form-select" onChange={(event) => this.setState({ category: event.target.value })} >
                            <option defaultValue>category</option>
                            <option value="MOBILE">Mobile</option>
                            <option value="MOUSE">Mouse</option>
                            <option value="LAPTOP">Laptop</option>
                        </select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicprice">
                        <Form.Label>selct product Image</Form.Label>
                        <Form.Control type="file" placeholder="choose file"
                            onChange={(event) => this.handleFile(event)} />
                    </Form.Group>
                    <Button variant="primary" type="submit" onClick={this.onSubmit}>
                        Add Product
                    </Button>
                </Form>
            </div>


        );
    }

}

// export default (props) => (
//     <AddProduct {...props}
//         params={useParams()} />
// );

export default AddProduct;