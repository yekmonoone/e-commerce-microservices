import React, { useState } from 'react';
import { findOrdersByUserId, getStatus } from '../api/orderService';
import { Button, Container, Form, Table } from 'react-bootstrap';

const OrderList = () => {
  const [userId, setUserId] = useState('');
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState('');

  const handleInputChange = (e) => {
    setUserId(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const response = await findOrdersByUserId(userId);
      const ordersWithStatus = await Promise.all(
        response.data.map(async (order) => {
          const statusResponse = await getStatus(order.id);
          return { ...order, status: statusResponse.data };
        })
      );
      setOrders(ordersWithStatus);
    } catch (error) {
      setError('There was an error fetching the orders!');
      console.error('There was an error fetching the orders!', error);
    }
  };

  return (
    <Container>
      <h1>Order List</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group>
          <Form.Label>User ID</Form.Label>
          <Form.Control
            type="text"
            value={userId}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Button type="submit" className="mt-3">Get Orders</Button>
      </Form>

      {error && <p className="text-danger mt-3">{error}</p>}

      {orders.length > 0 && (
        <>
          <h2 className="mt-3">Orders</h2>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Order ID</th>
                <th>Product IDs</th>
                <th>Total Amount</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {orders.map((order) => (
                <tr key={order.id}>
                  <td>{order.id}</td>
                  <td>{order.productIds.join(', ')}</td>
                  <td>${order.totalAmount}</td>
                  <td>{order.status}</td>
                </tr>
              ))}
            </tbody>
          </Table>
          <p>Total Orders: {orders.length}</p>
        </>
      )}
    </Container>
  );
};

export default OrderList;
