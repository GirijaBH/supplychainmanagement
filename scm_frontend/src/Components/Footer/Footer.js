import React from 'react';

import "./Footer.css";

const Footer = () => {
    return (
        <footer className="page-footer">
            <div className="container">
                <div className="d-flex justify-content-between">
                    <div className="footer-body">
                        <h4>SCM</h4>
                    </div>

                </div>
                <div className="mx-auto" style={{ width: "200px" }}>
                    <p>© Copy right SCM</p>
                </div>
            </div>
        </footer>
    );
}

export default Footer;