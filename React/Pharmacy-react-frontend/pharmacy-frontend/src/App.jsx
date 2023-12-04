import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HeaderComponent from '../components/HeaderComponent';
import HomeComponent from '../components/HomeComponent';
import AboutUsComponent from '../components/AboutUsComponent';

function App() {
  return (
    
    <BrowserRouter>
    
      <>
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="/about" element={<AboutUsComponent />} />
          {/* Add more routes for other components */}
        </Routes>
      </>
    </BrowserRouter>
  );
}

export default App;
