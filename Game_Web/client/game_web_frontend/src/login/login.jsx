import axios from "axios";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom"
import { setUser } from "../reducers/userReducer";
import Cookies from "js-cookie";
import {useAlert} from "react-alert";
import './login.css';
const Login = () => {
    const navigate = useNavigate();
    const [userName, setUsername] = useState('');
    const [passWord, setPassword] = useState('');
    const dispatch = useDispatch();
    const alert = useAlert();
    const handleLogin =  (e)=> {
        e.preventDefault();
        const loginRequest = {
            userName,
            passWord
        }
        console.log(loginRequest);
        axios.post("http://localhost:8080/authenticate/login", loginRequest
        ).then(res => {
            if(res.status === 200){
                const token = res.data.token;
                console.log(token);
                if(token !== ''){
                    const user = {
                        userId : res.data.userId,
                        userName : res.data.userName,
                        role : res.data.role,
                    }

                    Cookies.set('authToken' , token,{expires : 1});
                    dispatch(setUser(user));
                    alert.success("Login success");
                    navigate('/');
                }
            }
        }).catch(e => {
            console.log(e);
            alert.error("Login unsuccessfully")
        })
    };
    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
      };
    
      const handlePasswordChange = (event) => {
        setPassword(event.target.value);
      };
    return(
<div className="login-container">
      <h2>Đăng nhập</h2>
      <form className="login-form">
        <div className="input-group">
          <label htmlFor="username">Tài khoản:</label>
          <input
            type="text"
            id="username"
            value={userName}
            onChange={handleUsernameChange}
            className="input-field"
            required
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">Mật khẩu:</label>
          <input
            type="password"
            id="password"
            value={passWord}
            onChange={handlePasswordChange}
            className="input-field"
            required
          />
        </div>
        <button onClick  = {handleLogin}  className="login-button">Đăng nhập</button>
      </form>
    </div>
    );
}
export default Login;