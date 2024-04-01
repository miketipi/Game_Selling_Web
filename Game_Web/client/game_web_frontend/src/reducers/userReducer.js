import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
const initialState = {
    value : 1,
    userId : -1,
    userName : '',
    role : 'USER',
    isLoad : false
}
const userReducer = createSlice({
    name : 'user',
    initialState,
    reducers: {
        setUser : (state, action) => {
            state.userId = action.payload.userId;
            state.userName = action.payload.userName;
            state.role = action.payload.role;
            state.isLoad = true;
        },
        loadUser : async (state, action) => {
            await axios.get('/user/me',{
                headers: {
                    Authorization: `Bearer ${action.payload}`,
                }
            }).then(res => {
                if(res.status === 200){
                    state.userId = res.data.userId;
                    state.userName = res.data.userName;
                    state.role = res.data.role;
                    state.isLoad = true
                }
            })
            .catch(e => {
                state.isLoad = false;
                state.userId = -1
            });
        }
    }
});
export const {setUser, loadUser} = userReducer.actions;
export default userReducer.reducer;