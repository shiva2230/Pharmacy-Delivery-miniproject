import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Gallery from 'react-image-gallery';
import 'react-image-gallery/styles/css/image-gallery.css';
import './ImagePreviewComponent.css'; 
const AWS = window.AWS;

const ImagePreviewComponent = () => {
  const { imageKey } = useParams();
  const [images, setImages] = useState([]);

  useEffect(() => {
    AWS.config.update({
      accessKeyId: 'AKIATK4YL4WORWVRQDWQ',
      secretAccessKey: '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',
      region: 'eu-north-1',
    });

    const s3 = new AWS.S3();

    const params = {
      Bucket: 'prescriptionstoragebucket',
      Key: imageKey,
    };

    s3.getObject(params, (err, data) => {
      if (err) {
        console.error(err);
      } else {
        
        const blob = new Blob([data.Body], { type: data.ContentType });
        const imageUrl = URL.createObjectURL(blob);
        setImages([{ original: imageUrl, thumbnail: imageUrl }]);
      }
    });

    
    return () => {
      if (images.length > 0) {
        URL.revokeObjectURL(images[0].original);
      }
    };
  }, [imageKey]);

  return (
    <div className="show-prescription-container">
      <h2>Show Prescription</h2>
      {images.length > 0 && <Gallery items={images} />}
    </div>
  );
};

export default ImagePreviewComponent;
