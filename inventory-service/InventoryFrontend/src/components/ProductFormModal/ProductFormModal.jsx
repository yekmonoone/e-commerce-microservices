import React, { useState, useEffect } from 'react';
import './productformmodal.css';

const ProductFormModal = ({ isOpen, onClose, onSave, initialProduct }) => {
  const [product, setProduct] = useState(initialProduct);

  useEffect(() => {
    setProduct(initialProduct);
  }, [initialProduct]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(product);
  };

  if (!isOpen) return null;

  return (
    <div className="modal">
      <div className="modal-content">
        <span className="close" onClick={onClose}>&times;</span>
        <form onSubmit={handleSubmit}>
          Name<input
            type="text"
            name="name"
            placeholder="Name"
            value={product.name}
            onChange={handleChange}
          />
          Description<input
            type="text"
            name="description"
            placeholder="Description"
            value={product.description}
            onChange={handleChange}
          />
          
          price <input 
            type="number"
            name="price"
            placeholder="Price"
            value={product.price}
            onChange={handleChange}
          />
          Quantity<input
            type="number"
            name="quantity"
            placeholder="Quantity"
            value={product.quantity}
            onChange={handleChange}
          />
          
          <button type="submit">{initialProduct.id ? 'Update Product' : 'Add Product'}</button>
        </form>
      </div>
    </div>
  );
};

export default ProductFormModal;