import React, { useEffect, useState } from 'react';
import { getProductById, updateProduct } from '../api/productService';
import { useNavigate, useParams } from 'react-router-dom';
import { Container, Form, Button } from 'react-bootstrap';

const EditProduct = () => {
  const { id } = useParams();
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetchProduct();
  }, []);

  const fetchProduct = async () => {
    try {
      const response = await getProductById(id);
      const product = response.data;
      setName(product.name);
      setDescription(product.description);
      setPrice(product.price);
    } catch (error) {
      console.error('There was an error fetching the product!', error);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const product = {
      id: parseInt(id), // Ensure ID is an integer
      name,
      description,
      price: parseFloat(price)
    };
    try {
      await updateProduct(product);
      navigate('/');
    } catch (error) {
      console.error('There was an error updating the product!', error);
    }
  };

  return (
    <Container>
      <h1>Edit Product</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group>
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />
        </Form.Group>
        <Button type="submit" className="mt-3">Update Product</Button>
      </Form>
    </Container>
  );
};

export default EditProduct;
