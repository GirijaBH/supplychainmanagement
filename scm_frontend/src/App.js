import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from './Components/NavBar';
import { Routes, Route, Link } from "react-router-dom";
import EmployeeList from "./Components/EmpComp/EmployeeList"
import EmployeeAdd from './Components/EmpComp/EmployeeAdd';
import Footer from './Components/Footer/Footer';
import Home from './Components/Home';
import Products from './Components/ProductComp/Products';
import AddProduct from './Components/ProductComp/AddProduct';
import AboutUs from './AboutUs';
import Contact from './Contact';
const App = () => {


  return (
    <div className='app'>
      <NavBar />
      <div>
        <Link to={"/employeelist"} ></Link>
        <Link to={"/emp/_add"} />
        <Link to={"/products"}></Link>
        <Link to={"/product/_add"}></Link>
      </div>
      <div>
        <Routes>
          <Route exact path={'/'} element={<Home />}></Route>
          <Route exact path={'/aboutus'} element={<AboutUs/>}/>
          <Route exact path={'/contact'} element={<Contact/>}/>
          <Route exact path={"/employeelist"} element={<EmployeeList />}></Route>
          <Route exact path={"/emp/_add"} element={<EmployeeAdd />}></Route>
          <Route exact path={'/products'} element={<Products />}></Route>
          <Route exact path={'/product/_add'} element={<AddProduct/>}/>
        </Routes>
      </div>
        {/* <Footer /> */}
    </div>
  );
};
export default App;
