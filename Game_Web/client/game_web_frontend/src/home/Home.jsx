import React, { useState, useEffect } from 'react';
import { Card, Pagination, Dropdown, Container } from 'react-bootstrap';
import axios from 'axios';
import { Center } from '@chakra-ui/react';

const categories = [
  // Mảng chứa các loại game
  // 'Category 1',
  // 'Category 2',
  // ...
];

const ITEMS_PER_PAGE = 10;

function HomePage() {
  const [currentPage, setCurrentPage] = useState(1);
  const [currentCategory, setCurrentCategory] = useState('All');
  const [games, setGames] = useState([]);
const [count, setCount] = useState(0);
axios.get('http://localhost:8080/game/count').then(res => {
  console.log(res.data);
  setCount(res.data);
})
  useEffect(() => {
    const fetchGames = async () => {
      const response = await axios.get(`http://localhost:8080/game/page?page=${currentPage - 1}&offSet=${ITEMS_PER_PAGE}&sortBy=productId`);
      setGames(response.data);
    };

    fetchGames();
  }, [currentPage, currentCategory]);

  const handleChangePage = (event, value) => {
    setCurrentPage(value);
  };

  const handleChangeCategory = (event) => {
    setCurrentCategory(event.target.value);
  };

  return (
    <Container>
      {/* <Pagination count={Math.ceil(count / ITEMS_PER_PAGE)} page={currentPage} onChange={handleChangePage} /> */}
      <Dropdown onSelect={handleChangeCategory}>
        <Dropdown.Toggle variant="success" id="dropdown-basic">
          {currentCategory}
        </Dropdown.Toggle>

        <Dropdown.Menu>
          {categories.map((category, index) => (
            <Dropdown.Item key={index} eventKey={category}>{category}</Dropdown.Item>
          ))}
        </Dropdown.Menu>
      </Dropdown>

      {games.map((game) => (
        <Card style={{ width: '18rem' }} key={game.productId}>
          <Card.Img variant="top" src={game.gameImage} />
          <Card.Body>
            <Card.Title>{game.gameName}</Card.Title>
            <Card.Text>
              Giá: {game.gamePrice}
            </Card.Text>
          </Card.Body>
        </Card>
      ))}
      <Pagination >
                                        <Pagination.First disabled={parseInt(currentPage) === 1}
                                            onClick={() => setCurrentPage(1)} />
                                        <Pagination.Prev disabled={parseInt(currentPage) === 1}
                                            onClick={() => setCurrentPage(parseInt(currentPage) - 1)} />

                                        {Array.from({ length: Math.ceil(count/10) }).map((_, idx) => (
                                            <Pagination.Item key={idx + 1} active={(idx + 1) === parseInt(currentPage)}
                                                onClick={() => setCurrentPage(idx + 1)}>
                                                {idx + 1}
                                            </Pagination.Item>
                                        ))}

                                        <Pagination.Next disabled={parseInt(currentPage) === Math.ceil(count/10)}
                                            onClick={() => setCurrentPage(parseInt(currentPage) + 1)} />
                                        <Pagination.Last disabled={parseInt(currentPage) === Math.ceil(count/10)}
                                            onClick={() => setCurrentPage(parseInt(Math.ceil(count/10)))} />
                                    </Pagination>
      </Container>
  );
}

export default HomePage;
