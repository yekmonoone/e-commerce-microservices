import React, { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';
import { addProduct } from '../api/productService.js';  // API fonksiyonlarınızın olduğu dosyadan import ettiğiniz varsayılmaktadır.

function ProductForm() {
    const [product, setProduct] = useState({
        name: '',
        price: 0,
        description: ''
    });
    const [showModal, setShowModal] = useState(false);
    const [modalContent, setModalContent] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProduct(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async () => {
        try {
            const response = await addProduct(product);
            setModalContent(`Product added: ${response.data.name}`);
            setShowModal(true);
            // Formu temizleme veya diğer işlemler
        } catch (error) {
            setModalContent(`Error adding product: ${error.message}`);
            setShowModal(true);
        }
    };

    const handleCloseModal = () => setShowModal(false);

    return (
        <>
            <Form>
                <Form.Group>
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                        type="text"
                        name="name"
                        value={product.name}
                        onChange={handleInputChange}
                    />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Price</Form.Label>
                    <Form.Control
                        type="number"
                        name="price"
                        value={product.price}
                        onChange={handleInputChange}
                    />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Description</Form.Label>
                    <Form.Control
                        as="textarea"
                        name="description"
                        value={product.description}
                        onChange={handleInputChange}
                    />
                </Form.Group>
                <Button type="button" className="mt-3" onClick={handleSubmit}>
                    Add Product
                </Button>
            </Form>

            <Modal show={showModal} onHide={handleCloseModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Product Operation</Modal.Title>
                </Modal.Header>
                <Modal.Body>{modalContent}</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseModal}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default ProductForm;
