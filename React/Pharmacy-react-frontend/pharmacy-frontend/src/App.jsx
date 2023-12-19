import React, { useState } from 'react';
import { BrowserRouter, Route, Routes, Link } from 'react-router-dom';
import HeaderComponent from '../components/HeaderComponent';
import HomeComponent from '../components/HomeComponent';
import AboutUsComponent from '../components/AboutUsComponent';
import './Sidebar.css';
import TopBar from '../components/TopBar';
import ImagePreviewComponent from '../components/ImagePreviewComponent';
import AddMedicineComponent from '../components/AddMedicineComponent';
import PrescriptionContainer from '../components/PrescriptionContainer';
import ChatComponent from '../components/ChatComponent';
import DisplayMedicineComponent from '../components/DisplayMedicineComponent'; 

function App() {
  const [selectedMedicines, setSelectedMedicines] = useState([]);

  const handleMedicineSubmit = (medicineData) => {
    setSelectedMedicines(medicineData);
  };

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

          
          <div className="main-content">
            <Routes>
              <Route path="/" element={<HomeComponent />} />
              <Route path="/about" element={<AboutUsComponent />} />
              <Route path="/PrescriptionContainer/:imageKey" element={<PrescriptionContainer />} />
              <Route
                path="/AddMedicineComponent"
                element={<AddMedicineComponent onSubmit={handleMedicineSubmit} />}
              />
              <Route
                path="/DisplayMedicineComponent/:medicineDetails"
                element={<DisplayMedicineComponent />}
/>
              <Route path="/ChatComponent" element={<ChatComponent />} />
            </Routes>
          </div>
        </div>
      </>
    </BrowserRouter>
  );
}

export default App;
