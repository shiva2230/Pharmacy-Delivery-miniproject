// PrescriptionContainer.jsx
import React from 'react';
import ImagePreviewComponent from './ImagePreviewComponent';
import AddMedicineComponent from './AddMedicineComponent';
import ChatComponent from './ChatComponent';

const PrescriptionContainer = () => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', height: '100vh' }}>
      <div style={{ display: 'flex', flex: 1 }}>
        <div style={{ flex: 1 }}>
          <ImagePreviewComponent />
        </div>
        <div style={{ flex: 1 }}>
          <ChatComponent />
        </div>
      </div>
      <div>
        <AddMedicineComponent />
      </div>
    </div>
  );
};

export default PrescriptionContainer;
