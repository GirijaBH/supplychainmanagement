import React, { useState, useEffect } from "react";
import UserService from "../Services/UserService";
import second from "../Images/second.jpg"
import first from "../Images/first.jpg"
import third from "../Images/third.jpg";
const Home = () => {
    const [content, setContent] = useState("");

    useEffect(() => {
        UserService.getPublicContent().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                setContent(_content);
            }
        );
    }, []);

    return (
        <div className="container" >
           
                {/* <div className="row">
                     <div className="col align-self-start">
                        <img src={second} alt={"img not available"} style={{ width: "200px", height: "320px" }} />
                    </div>
                    <div className="col align-self-center">
                        <img src={first} alt={"img not available"} style={{ width: "200px", height: "320px" }} />
                    </div>
                    <div className="col align-self-center">
                        <img src={third} alt={"img not available"} style={{ width: "200px", height: "320px" }} />
                    </div>
                    <div className="col align-self-start">
                        <img src={second} alt={"img not available"} style={{ width: "200px", height: "320px" }} />
                    </div> 
                </div> */}

           
        </div>
    );
}
export default Home;