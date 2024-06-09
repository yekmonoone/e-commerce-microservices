import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import AddProduct from './components/AddProduct';
import EditProduct from './components/EditProduct';
import ProductDetail from './components/ProductDetail';
import OrderList from './components/ListedWithUser';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<HomePage />} />
        <Route path="/add" element={<AddProduct />} />
        <Route path="/edit/:id" element={<EditProduct />} />
        <Route path="/product/:id" element={<ProductDetail />} />
        <Route path="/orders" element={<OrderList />} /> {/* Yeni rota */}
      </Routes>
    </Router>
  );
}

export default App;
