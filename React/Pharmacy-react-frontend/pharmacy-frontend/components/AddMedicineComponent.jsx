import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; 
import './AddMedicineComponent.css';
import DisplayMedicineComponent from './DisplayMedicineComponent'; 

const AddMedicineComponent = () => {
  const [medicineDropdowns, setMedicineDropdowns] = useState([{ id: 1, selectedMedicine: '', quantity: 1 }]);
  const [medicineNames, setMedicineNames] = useState([]);
  const navigate = useNavigate(); 

  useEffect(() => {
    fetch('http://localhost:8080/api/pharmacy/medicines/getAll')
      .then(response => response.json())
      .then(data => {
        if (Array.isArray(data)) {
          const names = data.map((medicine, index) => {
            if (medicine && medicine.medicineName) {
              return medicine.medicineName;
            } else {
              console.error(`Invalid medicine data at index ${index}:`, medicine);
              return '';
            }
          });
          setMedicineNames(names);
        } else {
          console.error('Invalid API response format:', data);
        }
      })
      .catch(error => console.error('Error fetching medicine names:', error));
  }, []);

  const handleMedicineChange = (index, selectedMedicine) => {
    const updatedDropdowns = [...medicineDropdowns];
    updatedDropdowns[index].selectedMedicine = selectedMedicine;
    setMedicineDropdowns(updatedDropdowns);
  };

  const handleQuantityChange = (index, quantity) => {
    const updatedDropdowns = [...medicineDropdowns];
    updatedDropdowns[index].quantity = quantity;
    setMedicineDropdowns(updatedDropdowns);
  };

  const handleAddItem = () => {
    const newDropdownId = medicineDropdowns.length + 1;
    setMedicineDropdowns([...medicineDropdowns, { id: newDropdownId, selectedMedicine: '', quantity: 1 }]);
  };

  const handleDeleteItem = (index) => {
    const updatedDropdowns = medicineDropdowns.filter((_, i) => i !== index);
    setMedicineDropdowns(updatedDropdowns);
  };

  const handleSubmit = () => {
    
    const medicineDropdownsString = JSON.stringify(medicineDropdowns);

  
  navigate(`/DisplayMedicineComponent/${encodeURIComponent(medicineDropdownsString)}`);

  };

  return (
    <div className="add-medicine-container">
      <h2>Add Medicine</h2>
      {medicineDropdowns.map((dropdown, index) => (
        <div key={dropdown.id} className="medicine-dropdown">
          <label htmlFor={`medicine${index + 1}`}>Select Medicine:</label>
          <select
            id={`medicine${index + 1}`}
            value={dropdown.selectedMedicine}
            onChange={(event) => handleMedicineChange(index, event.target.value)}
          >
            <option value="" disabled>Select a medicine</option>
            {medicineNames.map((medicine, medicineIndex) => (
              <option key={medicineIndex} value={medicine}>
                {medicine}
              </option>
            ))}
          </select>
          <label htmlFor={`quantity${index + 1}`}>Quantity:</label>
          <input
            type="number"
            id={`quantity${index + 1}`}
            value={dropdown.quantity}
            onChange={(event) => handleQuantityChange(index, event.target.value)}
          />
          <button onClick={() => handleDeleteItem(index)}>Delete</button>
        </div>
      ))}
      <button onClick={handleAddItem}>Add Item</button>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
};

export default AddMedicineComponent;
