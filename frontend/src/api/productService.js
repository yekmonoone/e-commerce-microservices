import axios from 'axios';

const API_URL = 'http://localhost:8080/api/products';

export const getProducts = () => {
    return axios.get(`${API_URL}/getAll`);
};

export const getProductById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const addProduct = (product) => {
    return axios.post(`${API_URL}/addProduct`, product);
    
};

export const updateProduct = (product) => {
    return axios.put(`${API_URL}/updateProduct`, product);
};

export const deleteProduct = (id) => {
    if (!id) {
        throw new Error('Product ID is required');
    }
    return axios.delete(`${API_URL}/deleteByProductId/${id}`);
};

