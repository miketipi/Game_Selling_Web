import axios from 'axios';
import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import Cookies from 'js-cookie';
import { useDispatch } from 'react-redux';
import { setUser } from '../reducers/userReducer';

const SignUp = () => {
    const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    realName: '',
    userName: '',
    passWord: '',
    address: '',
    phone: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    const userName = formData.userName;
    const passWord = formData.passWord;
    const phone = formData.phone;
    const address = formData.address;
    const realName = formData.realName;
    const signupRequest = {
        realName,
        userName,
        passWord,
        phone,
        address
    }
    // Handle form submission here, e.g., send data to server
    console.log(signupRequest);
    axios.post("http://localhost:8080/authenticate/signup", signupRequest)
    .then(res => {
        if(res.status === 200){
        const user = {
            userName : res.data.userName,
            userId : res.data.userId,
            isLoad : true,
            role : res.data.role,
        }
        dispatch(setUser(user));
        Cookies.set('authToken', res.data.token);
    }}).catch(e => console.log(e))
  };

  return (
    <Container>
      <Row className="justify-content-center">
        <Col md={6}>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formUserName">
              <Row>
              <Form.Label>Tên đăng nhập</Form.Label>
              </Row>
              <Row>
              <Form.Control 
                type="text" 
                placeholder="Nhập tên đăng nhập" 
                name="userName" 
                value={formData.userName} 
                onChange={handleChange} 
                required 
              /></Row>
            </Form.Group>
            <Form.Group controlId="formRealName">
              <Row><Form.Label>Tên thật</Form.Label></Row>
              <Row><Form.Control 
                type="text" 
                placeholder="Nhập tên thật" 
                name="realName" 
                value={formData.realName} 
                onChange={handleChange} 
                required 
              />
              </Row>
            </Form.Group>
            <Form.Group controlId="formPassword">
              <Row>
              <Form.Label>Mật khẩu</Form.Label>
              </Row>
              <Row>
              <Form.Control 
                type="password" 
                placeholder="Nhập mật khẩu" 
                name="passWord" 
                value={formData.passWord} 
                onChange={handleChange} 
                required 
              />
              </Row>
            </Form.Group>

            <Form.Group controlId="formAddress">
              <Row><Form.Label>Địa chỉ</Form.Label></Row>
              <Row>
              <Form.Control 
                type="text" 
                placeholder="Nhập địa chỉ" 
                name="address" 
                value={formData.address} 
                onChange={handleChange} 
                required 
              />
              </Row>
            </Form.Group>

            <Form.Group controlId="formPhone">
              <Row>
              <Form.Label>Điện thoại</Form.Label></Row>
              <Row>
              <Form.Control 
                type="tel" 
                placeholder="Nhập số điện thoại" 
                name="phone" 
                value={formData.phone} 
                onChange={handleChange} 
                required 
              />
              </Row>
            </Form.Group>
<Row>
            <Button variant="primary" type="submit">
              Đăng ký
            </Button>
            </Row>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default SignUp;
