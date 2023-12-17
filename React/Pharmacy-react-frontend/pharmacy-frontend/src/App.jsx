import React from 'react';
import { BrowserRouter, Route, Routes, Link } from 'react-router-dom';
import HeaderComponent from '../components/HeaderComponent';
import HomeComponent from '../components/HomeComponent';
import AboutUsComponent from '../components/AboutUsComponent';
import './Sidebar.css';
import TopBar from '../components/TopBar';
import ImagePreviewComponent from '../components/ImagePreviewComponent';

function App() {
  return (
    <BrowserRouter>
      <>
        <TopBar />
        <HeaderComponent />
        <div className="app-container">
          {/* Sidebar */}
          <div className="sidebar">
            <nav>
              <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/about">About</Link></li>
              </ul>
            </nav>
          </div>

          {/* Main Content */}
          <div className="main-content">
            <Routes>
              <Route path="/" element={<HomeComponent />} />
              <Route path="/about" element={<AboutUsComponent />} />
              <Route path="/ImagePreviewComponent/:imageKey" element={<ImagePreviewComponent />} />
            </Routes>
          </div>
        </div>
      </>
    </BrowserRouter>
  );
}

export default App;
