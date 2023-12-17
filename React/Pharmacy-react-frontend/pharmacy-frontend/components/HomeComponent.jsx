import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './HomeComponent.css'; // Import your CSS file for styling
const AWS = window.AWS;

const HomeComponent = () => {
  const [prescriptionList, setPrescriptionList] = useState([]);
  const [hoveredImage, setHoveredImage] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    AWS.config.update({
      accessKeyId: 'AKIATK4YL4WORWVRQDWQ',
      secretAccessKey: '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',
      region: 'eu-north-1',
    });

    const s3 = new AWS.S3();

    const params = {
      Bucket: 'prescriptionstoragebucket',
    };

    s3.listObjectsV2(params, (err, data) => {
      if (err) {
        console.error(err);
      } else {
        const prescriptions = data.Contents.map((object) => ({
          key: object.Key,
          lastModified: object.LastModified,
          size: object.Size,
        }));
        setPrescriptionList(prescriptions);
      }
    });
  }, []);

  const handleImageHover = (imageKey) => {
    setHoveredImage(imageKey);
  };

  const handleImageClick = (imageKey) => {
    // Redirect to ShowPrescriptionComponent with the selected imageKey
    navigate(`/ImagePreviewComponent/${imageKey}`);
      console.log({imageKey})

  };
  // console.log({imageKey})

  return (
    <div className="prescription-container">
      <h2>Prescriptions</h2>
      <ul className="prescription-list">
        {prescriptionList.map((prescription) => (
          <li key={prescription.key} className="prescription-item">
            <strong>{prescription.key}</strong>
            <div className="prescription-details">
              <span>{prescription.size} bytes</span>
              <span>Last modified: {prescription.lastModified.toLocaleString()}</span>
            </div>
            <div
              className="prescription-image-container"
              onMouseEnter={() => handleImageHover(prescription.key)}
              onMouseLeave={() => setHoveredImage(null)}
              onClick={() => handleImageClick(prescription.key)}
              >
              <img
                src={`https://prescriptionstoragebucket.s3.amazonaws.com/${prescription.key}`}
                alt={prescription.key}
                className={`prescription-image ${hoveredImage === prescription.key ? 'hovered' : ''}`}
              />
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default HomeComponent;
