import axios from 'axios';

const API_URL = 'http://localhost:8081/api/orders';

export const placeOrder = (order) => {
    return axios.post(`${API_URL}/placeOrder`, order);
};
//a
export const getOrderById = (orderId) => {
    return axios.get(`${API_URL}/${orderId}`);
};

export const findOrdersByUserId = (userId) => {
    return axios.get(`${API_URL}/getOrdersByUserId/${userId}`);
};

export const getStatus = (orderId) => {
    return axios.get(`${API_URL}/status/${orderId}`);
};
