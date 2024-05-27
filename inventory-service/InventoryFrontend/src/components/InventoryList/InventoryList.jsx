import React,  { useState, useEffect } from 'react';
import ProductCard from '../ProductCard/ProductCard';
import ProductFormModal from '../ProductFormModal/ProductFormModal';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch, faPlus } from '@fortawesome/free-solid-svg-icons';
import './inventorylist.css';

const InventoryList = () => {
  const [inventory, setInventory] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentProduct, setCurrentProduct] = useState({ id: null, name: '', description: '', price: 0, quantity: 0 });

  useEffect(() => {
    fetchInventory();
  }, []);

  const fetchInventory = async () => {
    try {
      const response = await axios.get('/inventory'); 
      setInventory(response.data);
    } catch (error) {
      console.error("Error fetching inventory", error);
    }
  };

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
  };

  const filteredInventory = inventory.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleDelete = async (id) => {
    try {
      await axios.delete(`/inventory/delete/${id}`);
      setInventory(inventory.filter(item => item.id !== id));
    } catch (error) {
      console.error("Error deleting product", error);
    }
  };

  const handleEdit = (id) => {
    const productToEdit = inventory.find(item => item.id === id);
    setCurrentProduct(productToEdit);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setCurrentProduct({ id: null, name: '', description: '', price: 0, quantity: 0});
    setIsModalOpen(true);
  };

  const handleSave = async (product) => {
    if (product.id) {
      try {
        await axios.put(`/inventory/update/${product.id}`, { stock: product.quantity });
        setInventory(inventory.map(item => (item.id === product.id ? product : item)));
      } catch (error) {
        console.error("Error updating product", error);
      }
    } else {
      try {
        const response = await axios.post('/inventory/add', { productId: product.id, stock: product.quantity });
        setInventory([...inventory, { ...product, id: response.data.id }]);
      } catch (error) {
        console.error("Error adding product", error);
      }
    }
    setIsModalOpen(false);
  };

  const fetchProductDetails = async (id) => {
    try {
      const response = await axios.get(`/products/${id}`);
      alert(`Product details: ${JSON.stringify(response.data)}`);
    } catch (error) {
      console.error("Error fetching product details", error);
    }
  };



  return (
    <div className="inventory-container">
      <h1>Inventory</h1>
      <div className="inventory-controls">
        <div className="search-bar">
          <FontAwesomeIcon icon={faSearch} />
          <input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={handleSearch}
          />
        </div>
        <button className="add-button" onClick={handleAdd}>
          <FontAwesomeIcon icon={faPlus} /> Add New Product
        </button>
      </div>
      <div className="inventory-list">
        {filteredInventory.map(product => (
          <ProductCard
            key={product.id}
            product={product}
            onDelete={handleDelete}
            onEdit={handleEdit}
            onFetchDetails={fetchProductDetails}


          />
        ))}
      </div>
      <ProductFormModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onSave={handleSave}
        initialProduct={currentProduct}
      />
    </div>
  );
};

export default InventoryList;