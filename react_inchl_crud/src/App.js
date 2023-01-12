import { Button } from "@mui/material";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import EditForm from "./components/EditForm";
import { Navbar } from "./components/Navbar";
import SignupForm from "./components/Signup";
import Users from "./components/Users";
import SignupScreen from "./screens/SignupScreen";
import LoginScreen from "./screens/LoginScreen";
import HomeScreen from "./screens/HomeScreen";
import NoMatchScreen from "./screens/NoMatchScreen";

function App() {
  return (
    <div class="w-4/6 mx-auto">
      <Routes>
        <Route path="/signup" element={<SignupScreen />} />
        <Route path="/login" element={<LoginScreen />} />
        <Route path="/home" element={<HomeScreen />} />
        <Route path="*" element={<NoMatchScreen />} />
      </Routes>
    </div>
  );
}

export default App;
