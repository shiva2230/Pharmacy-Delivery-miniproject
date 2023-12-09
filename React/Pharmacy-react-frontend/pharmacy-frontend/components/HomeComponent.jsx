import React, { useState, useEffect } from 'react';
// import AWS from 'aws-sdk/dist/aws-sdk';
const AWS = window.AWS;
const HomeComponent = () => {
    const [imageList, setImageList] = useState([]);
  
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
          
          const images = data.Contents.map((object) => ({
            key: object.Key,
            lastModified: object.LastModified,
            size: object.Size,
          }));
          setImageList(images);
        }
      });
    }, []);
  
    return (
      <div>
        <h2>Image List</h2>
        <ul>
          {imageList.map((image) => (
            <li key={image.key}>
            <strong>{image.key}</strong> - {image.size} bytes - Last modified: {image.lastModified.toLocaleString()}
            <br />
            <img src={`https://prescriptionstoragebucket.s3.amazonaws.com/${image.key}`} alt={image.key} style={{ maxWidth: '100px', maxHeight: '100px' }} />
          </li>
          ))}
        </ul>
      </div>
    );
  };
export default HomeComponent