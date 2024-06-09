import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const mockServerUrl = 'https://a3e4ce0a-612d-4e4f-b007-79236bad33f9.mock.pstmn.io';  // Replace with your actual Postman mock server URL

function ReviewList() {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    axios.get(`${mockServerUrl}/reviews`)
      .then(response => {
        setReviews(response.data.reviews);
      })
      .catch(error => {
        console.error('There was an error fetching the reviews!', error);
      });
  }, []);

  const handleDelete = (reviewId) => {
    axios.delete(`${mockServerUrl}/reviews/${reviewId}`)
      .then(() => {
        setReviews(reviews.filter(review => review.reviewId !== reviewId));
      })
      .catch(error => {
        console.error('There was an error deleting the review!', error);
      });
  };

  return (
    <div>
      <h1>Reviews</h1>
      <Link to="/add">Add Review</Link>
      <ul>
        {reviews.map(review => (
          <li key={review.reviewId}>
            {review.comment} - {review.rating} stars
            <button onClick={() => handleDelete(review.reviewId)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ReviewList;
