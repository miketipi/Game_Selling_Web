import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import store from './reducers/index.js';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { ChakraProvider } from '@chakra-ui/react';
import { RouterProvider } from 'react-router-dom';
import router from './route.js';
import { positions, transitions, Provider as AlertProvider } from 'react-alert';
import AlertTemplate from 'react-alert-template-basic';
const root = ReactDOM.createRoot(document.getElementById('root'));
const options = {
  timeout: 5000,
  position: positions.BOTTOM_CENTER,
  transition: transitions.SCALE
}
root.render(
  <Provider store = {store}>
    <AlertProvider template={AlertTemplate} {...options}>
  <React.StrictMode>
    <ChakraProvider>
    <RouterProvider router={router}/>
    </ChakraProvider>
  </React.StrictMode>
  </AlertProvider>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
