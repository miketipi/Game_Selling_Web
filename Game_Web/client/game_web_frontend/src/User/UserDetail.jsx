import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import { useDispatch, useSelector } from 'react-redux';
import { setUser } from '../reducers/userReducer';
const UserDetail = () => {
    const userState = useSelector(state => state.user);
    const navigate = useNavigate();
    const [user, setUser] = useState([]);
    const dispatch = useDispatch();
    if (userState.isLoad == false) {
        navigate('/');
    } else {
        axios.get('http://localhost:8080/user/me', {
            headers: {
                Authorization: `Bearer ${Cookies.get(['authToken'])}`
            }
        }).then(res => {
            if (res.status === 200) {
                const user = {
                    userName: res.data.user_name,
                    role: res.data.role,
                    realName: res.data.real_name,
                    phone: res.data.phone,
                    address: res.data.address,
                    password: res.data.pass_word
                }
            }
        }).catch(e => console.log(e))
    }
}