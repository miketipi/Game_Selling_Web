import { combineReducers } from "redux";
import userReducer from "./userReducer";
import orderReducer from "./orderReducer";
const rootReducer = combineReducers({
    user: userReducer,
    order: orderReducer
});
export default rootReducer;