import {
  Alert,
  Button,
  CircularProgress,
  Snackbar,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import UserService from "../services/UserService";

const Login = () => {
  const [user, setUser] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);
  const [loginSuccess, setSignUpSuccess] = useState(false);
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    console.log(value);
    setUser({ ...user, [name]: value });
  };

  const saveUser = (e) => {
    e.preventDefault();
    if (user.email.length <= 0) {
      setEmailError("Email is required");
      return;
    } else {
      setEmailError("");
    }

    if (user.password.length <= 0) {
      setPasswordError("Password is required");
      return;
    } else {
      setPasswordError("");
    }

    setLoading(true);
    setTimeout(() => {
      UserService.saveUser(user)
        .then((response) => {
          setLoading(false);
          setSignUpSuccess(true);
          setOpenSnackbar(true);
          console.log(response);
        })
        .catch((error) => {
          setLoading(false);
          setSignUpSuccess(false);
          setOpenSnackbar(true);
          console.log(error);
        });
    }, 5000);
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenSnackbar(false);
  };

  return (
    <form noValidate class="rounded-lg shadow-lg bg-white w-fullmy-4 p-2">
      <h5 class="text-md text-bold my-2">Login</h5>

      <div class="mb-4">
        <label for="email" class="block text-gray-600 text-sm font-normal">
          Email
        </label>
        <TextField
          variant="standard"
          required
          error={emailError.length > 0}
          helperText={emailError.length > 0 ? emailError : ""}
          fullWidth
          class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
          id="email"
          name="email"
          type="email"
          value={user.email}
          onChange={(e) => handleFormChange(e)}
          placeholder="Your email"
        />
      </div>
      <div class="mb-4">
        <label for="password" class="block text-gray-600 text-sm font-normal">
          Password
        </label>
        <TextField
          variant="standard"
          required
          fullWidth
          error={passwordError.length > 0}
          helperText={passwordError.length > 0 ? passwordError : ""}
          class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
          id="password"
          name="password"
          type="password"
          value={user.password}
          onChange={(e) => handleFormChange(e)}
          placeholder="Your password"
        />
      </div>
      <>
        {loading ? (
          <CircularProgress variant="indeterminate" />
        ) : (
          <div class="flex justify-between items-center">
            <Button
              type="submit"
              onClick={saveUser}
              size="small"
              variant="contained"
            >
              <p class="text-white font-bold">Login</p>
            </Button>
            <span class="flex-1"></span>
            <p class="mx-2">Don't have a account?</p>
            <Button size="small" variant="contained">
              <p class="text-white font-bold">
                <NavLink to="/signup">Signup</NavLink>
              </p>
            </Button>
          </div>
        )}
      </>
      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
      >
        <Alert severity={loginSuccess ? "success" : "error"}>
          {loginSuccess
            ? "Successfully created user."
            : "Something went wrong try again."}
        </Alert>
      </Snackbar>
    </form>
  );
};

export default Login;
