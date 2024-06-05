import React from 'react';
import ProductList from '../components/ProductList';
import { Container } from 'react-bootstrap';

const HomePage = () => {
  return (
    <Container>
      <h1>Welcome to Our Store</h1>
      <ProductList />
    </Container>
  );
};

export default HomePage;
