// HeaderComponent.jsx

import React from 'react';
import { Link } from 'react-router-dom';

const HeaderComponent = () => {
  return (
    <div className="header">
      <nav className='navbar navbar-dark bg-dark'>
        <div className='container-fluid'>
          <Link to="/" className='navbar-brand'>
            <button className="btn btn-dark">Home</button>
          </Link>
          <Link to="/about" className='navbar-brand'>
            <button className="btn btn-dark">About Us</button>
          </Link>
        </div>
      </nav>
    </div>
  );
}

export default HeaderComponent;
