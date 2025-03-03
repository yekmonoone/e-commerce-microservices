import React,  { useState, useEffect } from 'react';
import axios from 'axios';
import './inventorylist.css';

const ProductStockInput = ({ productId, initialQuantity, onUpdate }) => {
  const [quantity, setQuantity] = useState(initialQuantity);

  useEffect(() => {
    setQuantity(initialQuantity);
  }, [initialQuantity]);

  const handleQuantityChange = (e) => {
    setQuantity(e.target.value);
  };

  const handleUpdate = () => {
    onUpdate(productId, quantity);
  };

  return (
    <div>
      <input
        type="number"
        value={quantity}
        onChange={handleQuantityChange}
      />
      <button className="update-button" onClick={handleUpdate}>Add Stock</button>
    </div>
  );
};

const InventoryPage = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const productResponse = await axios.get('http://localhost:8080/api/products/getAll');
      const productsData = productResponse.data;

      console.log('Fetched products data:', productsData);

    
      const productsDataWithId = productsData.map((product, index) => ({
        id: product.id || index + 1, // Use product.id if it exists, else assign a temporary unique ID
        ...product
      }));

      const productsWithStock = await Promise.all(
        productsDataWithId.map(async (product) => {
          console.log('Processing product:', product);
          try {
            const stockResponse = await axios.get(`http://localhost:8082/api/inventory/check/${product.id}`);
            console.log(`Stock response for product ${product.id}:`, stockResponse.data);
            const currentStock = stockResponse.data; 

            return {
              ...product,
              currentStock,
            };
          } catch (stockError) {
            console.error(`Error fetching stock for product ${product.id}:`, stockError);
            return {
              ...product,
              currentStock: 0,
            };
          }
        })
      );

      setProducts(productsWithStock);
    } catch (error) {
      console.error('Error fetching products or stock:', error);
    }
  };

  const handleStockChange = async (productId, newStock) => {
    try {
      const stock = newStock || 0;
      await axios.put(`http://localhost:8082/api/inventory/update/${productId}/${stock}`);
      

      setProducts(prevProducts =>
        prevProducts.map(product =>
          product.id === productId ? { ...product, currentStock: stock } : product
        )
      );

      fetchProducts();
    } catch (error) {
      console.error('Error updating or adding stock:', error);
    }
  };

  const deleteProductStock = async (productId) => {
    try {
      await axios.delete(`http://localhost:8082/api/inventory/delete/${productId}`);
      fetchProducts();
    } catch (error) {
      console.error('Error deleting product stock:', error);
    }
  };

  const getCurrentStock = async (productId) => {
    try {
      const response = await axios.get(`http://localhost:8082/api/inventory/check/${productId}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching stock for product ${productId}:`, error);
      return 0;
    }
  };

  return (
    <div className="inventory-container">
      <h1>Inventory Management</h1>
      <table className="inventory-table">
        <thead>
          <tr>
            <th>Product</th>
            <th>Current Stock</th>
            <th>New Stock</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.name}</td>
              <td>{product.currentStock}</td>
              <td>
                <ProductStockInput
                  productId={product.id}
                  initialQuantity={product.currentStock}
                  onUpdate={handleStockChange}
                />
              </td>
              <td>
                <button className="delete-button" onClick={() => deleteProductStock(product.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};


export default InventoryPage;