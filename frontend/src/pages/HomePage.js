import React from 'react';
import ProductList from '../components/ProductList';
import ListedWithUser from '../components/ListedWithUser';
import { Container } from 'react-bootstrap';

const HomePage = () => {
  return (
    <Container>
      <h1>Welcome to Our Store</h1>
      <ProductList />
      <ListedWithUser />
    </Container>
  );
};

export default HomePage;
