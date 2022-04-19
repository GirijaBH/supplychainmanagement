import React from "react";
import ProductService from "../../Services/ProductService";
import { Link } from "react-router-dom";

class Products extends React.Component {

    constructor(props) {
        super(props);
        console.log("ProdcutList Component");
        this.state = {
            products: [],
            flag: false
        }
    }

    componentDidMount() {
        ProductService.getAllProducts().then((response) => {
            this.setState({ products: response.data })
        })
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.state.flag === true) {
            ProductService.getAllProducts().then((response) => {
                this.setState({ products: response.data })
                this.setState({ flag: false })
            });
        }
    }

    editProduct = (pid) => {
        console.log("in emplist edit emp")
        this.props.history.push(`/add/${pid}`)
    }
    deleteProduct = (pid) => {
        ProductService.deleteProduct(pid).then((response) => {
            //modify the the value of flag to true
            this.setState({ flag: true });

        })
    }



    render() {

        return (
            <div className="list" style={{ fontSize: "12px" }}>
            <h2 className="text-center" style={{color:"blue"}}>Products</h2>
            <div className="row">
                <Link to="/product/_add"><button className="btn btn-primary" size="sm"> Add Products</button></Link>
            </div>
            <br></br>
            <div className="row">
                <table className="table table-striped table-bordered">

                    <thead>
                        <tr>
                            <th>  Product Name</th>
                            <th>   Brand</th>
                            <th>   Description</th>
                            <th>  Stock</th>
                            <th>  Price </th>
                            <th>  Category </th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.products.map(
                                product =>
                                    <tr key={product.id}>
                                        <td> {product.name} </td>
                                        <td> {product.brand}</td>
                                        <td> {product.description}</td>
                                        <td> {product.stock} </td>
                                        <td> {product.price}</td>
                                        <td> {product.category.categoryName}</td>
                                      
                                        <td>
                                            <button onClick={() => this.editProduct(product.id)} className="btn btn-info" style={{ marginLeft: "10px", size: "50%" }} >Update </button>
                                            <button style={{ marginLeft: "10px", size: "50%" }} onClick={() => this.deleteProduct(product.id)} className="btn btn-danger" >Delete </button>

                                           
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>

            </div>


        </div>
        )
    }

}
export default Products;