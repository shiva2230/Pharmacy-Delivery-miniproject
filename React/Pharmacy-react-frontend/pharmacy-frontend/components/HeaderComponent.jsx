// HeaderComponent.jsx

import React from 'react';
import { Link } from 'react-router-dom';

const HeaderComponent = () => {
  return (
    <div className="header">
      <h1>Chat App</h1>
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/about">About</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default HeaderComponent;
