// Header.js
import axios from 'axios';
import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
// import { logout } from './actions/authActions'; // Assuming logout action creator is defined
import './header.css'; // Import CSS file
import { Link, useNavigate } from 'react-router-dom';
import Cookies from "js-cookie";
import { setUser } from '../reducers/userReducer';
function Header() {
    const dispatch = useDispatch();
    const btnRef = React.useRef();
    const userState = useSelector(state => state.user);
    let userId, userName, role;
    const [keyword, setKeyword] = useState();
    const navigate = useNavigate;//   const isAuthenticated = useSelector(state => state.auth.isAuthenticated);
    //   const dispatch = useDispatch();
    const [visible, setVisible] = useState(false);
    if (userState.isLoad == false) {
        axios.get('http://localhost:8080/user/me', {
            headers: {
                Authorization: `Bearer ${Cookies.get('authToken')}`,
            }
        })
            .then((res) => {
                if (res.status === 200) {
                    const user = {
                        userId: res.data.userId,
                        userName: res.data.user_name,
                        role: res.data.role
                    }
                    console.log(user);
                    dispatch(setUser(user));
                }
            })
            .catch(e => { console.log(e); })
    }
    userId = userState.userId;
    userName = userState.userName;
    console.log(userName);
    console.log(userId);
    role = userState.role;
    //   const handleLogout = () => {
    //     dispatch(logout());
    //   };
    const handleLogout = () => {
        console.log("logout");
        Cookies.remove('authToken');
        const user = {
            userId: -1,
            userName: '',
            role: 'USER',
            isLoad: false
        };
        dispatch(setUser(user));
        navigate('/');
    }
    const handleClick = () => {
        navigate('/login')

    }
    return (
        <div className="header">
            <div className="store-name">Tipi Store</div>
            <div className="search-box">
                <input type="text" placeholder="Search..." onChange={e => setKeyword(e.target.value)} />
                <i className="fas fa-search"></i>
            </div>
            <div className="user-actions">
                {userState.isLoad ? (
                    <>
                        <button onClick={handleLogout}>Logout</button>
                        <button>User Info</button>
                        <button>Purchased Items</button>
                    </>
                ) : (
                    <Link to={'/login'}>Login</Link>
                )}
            </div>
        </div>
    );
}

export default Header;
