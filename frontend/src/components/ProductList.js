import React, { useEffect, useState } from 'react';
import { getProducts, deleteProduct } from '../api/productService';
import { Link } from 'react-router-dom';
import { Button, Table, Container } from 'react-bootstrap';
import '../index.css'; // CSS dosyasını import etme

const ProductList = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await getProducts();
      setProducts(response.data);
    } catch (error) {
      console.error('There was an error fetching the products!', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteProduct(id);
      fetchProducts(); // Ürünleri yeniden yükler
    } catch (error) {
      console.error('There was an error deleting the product!', error);
    }
  };

  const buttonStyle = {
    padding: '0.375rem 0.75rem',
    fontSize: '0.875rem',
  };

  return (
    <Container>
      <h1>Product List</h1>
      <Link to="/add" className="btn btn-primary mb-3">Add Product</Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>
                <Link to={`/product/${product.id}`}>{product.name}</Link>
              </td>
              <td>${product.price}</td>
              <td>
                <Link to={`/edit/${product.id}`} className="btn btn-secondary mr-2" style={buttonStyle}>Edit</Link>
                <Button 
                  variant="danger" 
                  onClick={() => handleDelete(product.id)} 
                  style={buttonStyle}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default ProductList;
