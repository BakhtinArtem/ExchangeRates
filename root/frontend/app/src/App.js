import axios from 'axios';

import {Pagination, Nav, Card, Col, Container, Navbar, Row, ListGroup} from 'react-bootstrap'
import { useState, useEffect } from 'react';

function App() {
  const [rates, setRates] = useState([])
  const [loading, setLoading] = useState(true)
  const [currentRates, setCurrentRates] = useState([])
  const [selectedRate, setSelectedRate] = useState(null)
  const [page, setPage] = useState(1)
  const ratesPerPage = 15
  //  URLS
  const fetchAndGetRates = "http://localhost:8080/rates?usedb=true";
  const getRates = "http://localhost:8080/rates?usedb=false";
  const deleteAllRates = "http://localhost:8080/deleteAll";
  let items = []
  const indexOfLastPage = (page) * ratesPerPage
  const indexOfFirstPage = indexOfLastPage - ratesPerPage

  for (let number = 1; number <= Math.ceil(rates.length / ratesPerPage); number++) {
    items.push(
      <Pagination.Item key={number} active={number === page}
        onClick={() => {
          setPage(number) 
        }}
      >
        {number}
      </Pagination.Item>,
    );
  }

  const processFetchAndGetRates = async(e) => {
    e.preventDefault();
    try {
      const resp = await axios({
        method: 'get',
        withCredentials: false,
        url: fetchAndGetRates,
        headers: {
          "Access-Control-Allow-Origin": "*"
        },
      });
      setSelectedRate(null)   //  reset selected rate
      setLoading(true)
      setRates(resp.data)
    } catch (error) {
      console.log(error)
    }
  }

  const processGetRates = async(e) => {
    if (e !== undefined)
      e.preventDefault();
    try {
      const resp = await axios({
        method: 'get',
        withCredentials: false,
        url: getRates,
        headers: {
          "Access-Control-Allow-Origin": "*"
        },
      });
      setSelectedRate(null)     //  reset selected rate
      setLoading(true)
      setRates(resp.data)
    } catch (error) {
      console.log(error)
    }
  }

  const processDeleteAll = async(e) => {
    if (e !== undefined)
      e.preventDefault();
    try {
      await axios({
        method: 'delete',
        withCredentials: false,
        url: deleteAllRates,
        headers: {
          "Access-Control-Allow-Origin": "*"
        },
      });
      setSelectedRate(null)   //  reset selected rate
    } catch (error) {
      console.log(error)
    }
  }

  useEffect((e) => {
    processGetRates(e);    //  get list of rates after downloading
    setCurrentRates(rates.slice(indexOfFirstPage, indexOfLastPage))
    setLoading(false)
  }, []);
  
  useEffect((e) => {
    setCurrentRates(rates.slice(indexOfFirstPage, indexOfLastPage))
    setLoading(false)
  }, [page, rates]);

  return (
    <>
      <Navbar bg='dark'>
        <Container>
          <Navbar.Brand>
            <div style={{color: "white"}}>Exchange Rates</div>
          </Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link onClick={processFetchAndGetRates}>
              <div style={{color: "white"}}>Fetch and Get</div>
            </Nav.Link>
            <Nav.Link onClick={processGetRates}>
              <div style={{color: "white"}}>Get</div>
            </Nav.Link>
            <Nav.Link onClick={processDeleteAll}>
              <div style={{color: "white"}}>Clear</div>
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>

      <Row style={{height: "85vh"}}>
        <Col style={{height: "85vh"}}>
          <ListGroup style={{height: "85vh"}}>
            {loading ? "Loading wait..." : currentRates.map(rate => {
              return <ListGroup.Item onClick={() => setSelectedRate(rate)} key={rate.id} as="li">
                <Row>
                  <Col>
                    Label: {rate.shortName}
                  </Col>
                  <Col>
                    Buy: {rate.valBuy}
                  </Col>
                  <Col>
                    Sell: {rate.valSell}
                  </Col>
                </Row>
              </ListGroup.Item>
            })}
          </ListGroup>
          <Container>
            <Pagination style={{justifyContent: "center"}} >
              {items}
            </Pagination>
          </Container>
        </Col>

        <Col>
          <Container style={{justifyContent: "center"}}>
            {selectedRate == null ? <></> :
              <Card style={{ width: '20rem' }}>
                <Card.Body>
                  <Card.Title>Currency info</Card.Title>
                  <Card.Subtitle>Updated: {new Date(selectedRate.validFrom).toString()}</Card.Subtitle>
                  <Card.Text>
                    {selectedRate.name} ({selectedRate.shortName}) is a currency of {selectedRate.country}. 
                  </Card.Text>
                  <Card.Text>
                    Buy: {<span style={{color: "red"}}>{selectedRate.valBuy}</span>} {}
                    Sell: {<span style={{color: "blue"}}>{selectedRate.valSell}</span>} {}
                    ValMid: {selectedRate.valMid} {}
                    Move: {selectedRate.move}
                  </Card.Text>
                  <Card.Text>
                    Amount: {selectedRate.amount} {}
                    currBuy: {<span style={{color: "red"}}>{selectedRate.currBuy}</span>} {}
                    currSell: {<span style={{color: "blue"}}>{selectedRate.currSell}</span>} {}
                    currMid: {selectedRate.currMid}
                  </Card.Text>
                  <Card.Text>
                    Version: {selectedRate.version} {}
                    cnbMid: {selectedRate.cnbMid} {}
                    ecbMid: {selectedRate.ecbMid} {}
                    currMid: {selectedRate.currMid}
                  </Card.Text>
                </Card.Body>
              </Card>
            }
          </Container>
        </Col>
      </Row>
    </>
  );
}

export default App;
