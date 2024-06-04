import React, { useEffect, useState } from 'react';
import { getProductById } from '../api/productService';
import { useParams } from 'react-router-dom';
import { Container, Card, ListGroup, ListGroupItem, Alert } from 'react-bootstrap';
import '../index.css'; // Stil dosyasını import etme

const ProductDetail = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProduct();
  }, [id]);

  const fetchProduct = async () => {
    try {
      const response = await getProductById(id);
      setProduct(response.data);
    } catch (error) {
      setError('There was an error fetching the product details!');
    }
  };

  if (error) {
    return <Alert variant="danger">{error}</Alert>;
  }

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <Container>
      <Card className="mt-4">
        <Card.Img 
          variant="top" 
          src="https://www.apple.com/newsroom/images/2023/09/apple-unveils-iphone-15-pro-and-iphone-15-pro-max/article/Apple-iPhone-15-Pro-lineup-hero-230912_Full-Bleed-Image.jpg.large.jpg" 
          alt={product.name} 
          className="card-img-top" // CSS sınıfını ekleme
        />
        <Card.Body>
          <Card.Title>{product.name}</Card.Title>
          <Card.Text>{product.description}</Card.Text>
        </Card.Body>
        <ListGroup className="list-group-flush">
          <ListGroupItem>Price: ${product.price}</ListGroupItem>
        </ListGroup>
        <Card.Body>
          <Card.Title>Reviews</Card.Title>
          {/* Here you can map through the reviews and display them */}
          {product.reviews && product.reviews.length > 0 ? (
            product.reviews.map((review, index) => (
              <Card.Text key={index}>
                <strong>{review.username}:</strong> {review.comment}
              </Card.Text>
            ))
          ) : (
            <Card.Text>No reviews yet.</Card.Text>
          )}
        </Card.Body>
      </Card>
    </Container>
  );
};

export default ProductDetail;
