import React from 'react';
import './productcard.css';

const ProductCard = ({ product, onDelete, onEdit, onCheckStock, onFetchDetails }) => {
  
    return (
      <div className="product-card">
        <h2>{product.name}</h2>
        <p>{product.description}</p>
        <p>Price: {product.price}</p>
        <p>Quantity: {product.quantity}</p>
        <button onClick={() => onEdit(product.id)}>Edit</button>
        <button onClick={() => onDelete(product.id)}>Delete</button>
        <button onClick={() => onFetchDetails(product.id)}> Product Details</button>
      </div>
    );
  };
  
  export default ProductCard;