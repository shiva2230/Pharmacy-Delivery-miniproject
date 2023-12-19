import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const DisplayMedicineComponent = () => {
  const { medicineDetails } = useParams();
  const medicineDropdowns = JSON.parse(decodeURIComponent(medicineDetails || '[]'));
  const [prescriptionData, setPrescriptionData] = useState([]);
  const [customerInfo, setCustomerInfo] = useState({});
  const [emailSent, setEmailSent] = useState(false);

  useEffect(() => {
    const fetchPrescriptionData = async () => {
      try {
        const promises = medicineDropdowns.map(async (dropdown) => {
          const response = await fetch(
            `http://localhost:8080/api/pharmacy/medicines/details?medicineName=${dropdown.selectedMedicine}`
          );
          if (response.ok) {
            const data = await response.json();
            return data;
          } else {
            console.error('Error in API response:', response.status, response.statusText);
            return null;
          }
        });

        const prescriptionDataList = await Promise.all(promises);
        setPrescriptionData(prescriptionDataList.filter(Boolean));
      } catch (error) {
        console.error('Error fetching prescription data:', error);
      }
    };

    const fetchCustomerInfo = async () => {
      try {
        
        const imageRef = '1702984882635_image-34.jpg';
        const response = await fetch(
          `http://localhost:8080/api/prescription-images/${imageRef}`
        );
        if (response.ok) {
          const data = await response.json();
          setCustomerInfo(data); 
        } else {
          console.error('Error in API response:', response.status, response.statusText);
        }
      } catch (error) {
        console.error('Error fetching customer info:', error);
      }
    };

    fetchPrescriptionData();
    fetchCustomerInfo();
  }, [medicineDropdowns]);

  const sendEmail = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/email/send', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          to: customerInfo.email, 
          subject: 'Prescription Details',
          body: `
            Prescription Details:
            Medicines: ${prescriptionData.map((prescription) => prescription.medicineName).join(', ')}
            Total Price: ${calculateTotalPrice()}
            Customer Name: ${customerInfo.name}
            Customer Email: ${customerInfo.email}
          `,
        }),
      });

      if (response.ok) {
        setEmailSent(true);
      } else {
        console.error('Error in sending email:', response.status, response.statusText);
      }
    } catch (error) {
      console.error('Error sending email:', error);
    }
  };

  const calculateTotalPrice = () => {
    let totalPrice = 0;
    prescriptionData.forEach((prescription, index) => {
      
      const mrp = parseFloat(prescription.mrp.replace(/[^\d.]/g, '')); 
      const quantity = medicineDropdowns[index].quantity;
      totalPrice += mrp * quantity;
    });
    return totalPrice;
  };

  return (
    <div>
      <h2>Prescription Data</h2>
      <ul>
        {prescriptionData.map((prescription, index) => (
          <li key={index}>
            <strong>Medicine Name:</strong> {prescription.medicineName}
            <br />
            <strong>Quantity:</strong> {medicineDropdowns[index].quantity}
            <br />
            <strong>MRP:</strong> {prescription.mrp}
            <hr />
          </li>
        ))}
      </ul>
      <p>Total Price: {calculateTotalPrice()}</p>

      <h2>Customer Info</h2>
      <p>
        <strong>Name:</strong> {customerInfo.name}
        <br />
        <strong>Email:</strong> {customerInfo.email}
      </p>

      <button onClick={sendEmail} disabled={emailSent}>
        {emailSent ? 'Email Sent' : 'Send Email'}
      </button>
    </div>
  );
};

export default DisplayMedicineComponent;
