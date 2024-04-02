// Header.js

import React from 'react';
// import { useSelector, useDispatch } from 'react-redux';
// import { logout } from './actions/authActions'; // Assuming logout action creator is defined
import './header.css'; // Import CSS file

function Header() {
//   const isAuthenticated = useSelector(state => state.auth.isAuthenticated);
//   const dispatch = useDispatch();

//   const handleLogout = () => {
//     dispatch(logout());
//   };

  return (
    <div className="header">
      <div className="store-name">Tipi Store</div>
      <div className="search-box">
        <input type="text" placeholder="Search..." />
        <i className="fas fa-search"></i>
      </div>
      <div className="user-actions">
        {/* {isAuthenticated ? (
          <>
            <button onClick={handleLogout}>Logout</button>
            <button>User Info</button>
            <button>Purchased Items</button>
          </>
        ) : (
          <button>Login</button>
        )} */}
      </div>
    </div>
  );
}

export default Header;
