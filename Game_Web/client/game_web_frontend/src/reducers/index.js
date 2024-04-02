import { configureStore } from "@reduxjs/toolkit";
import rootReducer from "./allReducer";

const store = configureStore({
    reducer : rootReducer
});
export default store;