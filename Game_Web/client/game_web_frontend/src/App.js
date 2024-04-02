import logo from './logo.svg';
import './App.css';
import Footer from './footer/footer';
import Header from './header/header';
import {Outlet} from 'react-router-dom';
function App() {
  return (
    <div className="App">
      <Header/>
      <Outlet/>
      <Footer></Footer>
    </div>
  );
}

export default App;
