import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './DisplayMedicineComponent.css';

const DisplayMedicineComponent = () => {
  const { medicineDetails } = useParams();
  const medicineDropdowns = JSON.parse(decodeURIComponent(medicineDetails || '[]'));
  const [prescriptionData, setPrescriptionData] = useState([]);
  const [customerInfo, setCustomerInfo] = useState({});
  const [emailSent, setEmailSent] = useState(false);
  const [loading, setLoading] = useState(false);
  const [expandedMedicine, setExpandedMedicine] = useState(null);

  const stripHtmlTags = (htmlString) => {
    const doc = new DOMParser().parseFromString(htmlString, 'text/html');
    return doc.body.textContent || "";
  };

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
      setLoading(true); // Set loading to true when starting to send email
      const response = await fetch('http://localhost:8080/api/email/send', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          to: customerInfo.email,
          subject: 'Order Confirmation',
          body: stripHtmlTags(`
            Your Order Was Successfully Placed!
  
            Thank you for placing an order with us. Below are the details of your prescription:
  
            ${prescriptionData.map((prescription, index) => `
              Medicine Name: ${prescription.medicineName}
              Quantity: ${medicineDropdowns[index].quantity}
              MRP: ${prescription.mrp}
              
              ${prescription.usage && `Usage: ${prescription.usage}`}
              ${prescription.sideEffects && `Side Effects: ${prescription.sideEffects}`}
              ${prescription.howToUse && `How to Use: ${prescription.howToUse}`}
              ${prescription.howItWorks && `How It Works: ${prescription.howItWorks}`}
  
              ${index < prescriptionData.length - 1 ? '---------------------------------------' : ''}
            `).join('')}
            
            Total Price: ${calculateTotalPrice()}
  
            For more details about each medicine, please visit our website.
            Thank you for choosing our service!
          `),
        }),
      });
  
      if (response.ok) {
        setEmailSent(true);
      } else {
        console.error('Error in sending email:', response.status, response.statusText);
      }
    } catch (error) {
      console.error('Error sending email:', error);
    } finally {
      setLoading(false); // Set loading back to false when email sending is done
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

  const toggleExpandedMedicine = (index) => {
    setExpandedMedicine((prevIndex) => (prevIndex === index ? null : index));
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
            <br />
            <button
              className="details-button"
              onClick={() => toggleExpandedMedicine(index)}
            >
              {expandedMedicine === index ? 'Hide Details' : 'Show More Details ▼'}
            </button>
            {expandedMedicine === index && (
              <div className="medicine-details">
                {/* Additional information for the expanded medicine */}
                <p>Usage: {prescription.usage}</p>
                <p>Side Effects: {prescription.sideEffects}</p>
                <p>How to Use: {prescription.howToUse}</p>
                <p>How It Works: {prescription.howItWorks}</p>
              </div>
            )}
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

      <button onClick={sendEmail} disabled={emailSent || loading}>
        {loading ? 'Sending Email...' : (emailSent ? 'Email Sent' : 'Send Email')}
      </button>
    </div>
  );
};

export default DisplayMedicineComponent;
