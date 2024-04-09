import React, { useState, useEffect } from 'react';
import axios from 'axios';

function GameDetail({ match }) {
  const [product, setProduct] = useState({});
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const fetchProductAndComments = async () => {
      const productResponse = await axios.get(`http://localhost:8080/game/${match.params.id}`);
      const commentsResponse = await axios.get(`http://localhost:8080/comments/${match.params.id}`);

      setProduct(productResponse.data);
      setComments(commentsResponse.data);
    };

    fetchProductAndComments();
  }, [match.params.id]);

  return (
    <div>
      <h1>{product.gameName}</h1>
      <img src={product.gameImage} alt={product.gameName} />
      <p>Giá: {product.gamePrice}</p>
      <p>Trạng thái: {product.status}</p>
      <p>Số người tải: {product.downloadCount}</p>
      <p>Version: {product.version}</p>

      <h2>Bình luận:</h2>
      {comments.map((comment) => (
        <div key={comment.id}>
          <h3>{comment.author}</h3>
          <p>{comment.content}</p>
        </div>
      ))}
    </div>
  );
}

export default GameDetail;
