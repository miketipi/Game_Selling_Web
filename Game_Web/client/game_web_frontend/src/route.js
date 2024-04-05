import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Login from "./login/login.jsx";
import SignUp from "./signup/signup.jsx";
import HomePage from "./home/Home.jsx";
const router = createBrowserRouter([
    {
        path: '/',
        element: <App/>,
            children: [
                {
                    path: '/',
                    element: <HomePage />
                },
                {
                    path: '/home',
                    element: <HomePage />
                },
                {
                    path: '/login',
                    element: <Login/>
                },
                {
                    path: '/signup',
                    element: <SignUp/>
                }
        ]
    }
])
export default router;