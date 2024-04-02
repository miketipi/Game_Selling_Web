import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Login from "./login/login.jsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <App/>,
            children: [
                // {
                //     path: '/',
                //     element: <Home />
                // },
                // {
                //     path: '/home',
                //     element: <Home />
                // },
                {
                    path: '/login',
                    element: <Login/>
                },
        ]
    }
])
export default router;