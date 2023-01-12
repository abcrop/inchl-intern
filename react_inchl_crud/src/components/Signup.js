import {
  Alert,
  Button,
  CircularProgress,
  InputLabel,
  MenuItem,
  Select,
  Snackbar,
  TextField,
  Box,
} from "@mui/material";
import { blue } from "@mui/material/colors";
import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import UserService from "../services/UserService";

const SignupForm = ({ props }) => {
  const [user, setUser] = useState({
    id: "",
    fullName: "",
    email: "",
    password: "",
    userType: "",
    enabled: true,
    role: "",
  });

  const [loading, setLoading] = useState(false);
  const [signUpSuccess, setSignUpSuccess] = useState(false);
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
          props.setIsUserAddedSuccessfully(!props.isUserAddedSuccessfully);
          console.log(response);
        })
        .catch((error) => {
          setLoading(false);
          setSignUpSuccess(false);
          setOpenSnackbar(true);
          console.log(error);
        });
    }, 200);
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenSnackbar(false);
  };

  return (
    <form noValidate class="rounded-lg shadow-lg bg-white w-fullmy-4 p-2">
      <h5 class="text-md text-bold my-2">
        {props.isAddUserMode ? (
          <div class="font-bold flex justify-between items-center px-2">
            <p>Add</p>
            <Button
              onClick={() => props.setAddUserMode(!props.isAddUserMode)}
              sx={{ color: blue }}
            >
              Close(x)
            </Button>
          </div>
        ) : (
          "Sign up"
        )}
      </h5>
      <div class="mb-4">
        <label for="fullName" class="block text-gray-600 text-sm font-normal">
          Full name
        </label>
        <TextField
          variant="standard"
          required
          fullWidth
          class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
          id="fullName"
          name="fullName"
          type="text"
          value={user.fullName}
          onChange={(e) => handleFormChange(e)}
          placeholder="Your name"
        />
      </div>
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
      <div class="mb-4" variant="filled" sx={{ m: 1, minWidth: 150 }}>
        <InputLabel
          id="userType"
          class="block text-gray-600 text-sm font-normal"
        >
          User Type
        </InputLabel>
        <Select
          variant="standard"
          labelId="userType"
          id="userType"
          name="userType"
          value={user.userType}
          onChange={(e) => handleFormChange(e)}
          label="User Type"
        >
          <MenuItem value="">
            <em>Select</em>
          </MenuItem>
          <MenuItem value="ADMIN">ADMIN</MenuItem>
          <MenuItem value="STAFF">STAFF</MenuItem>
          <MenuItem value="CUSTOMER">CUSTOMER</MenuItem>
        </Select>
      </div>
      <div class="mb-4">
        <label for="role" class="block text-gray-600 text-sm font-normal">
          Role
        </label>
        <TextField
          variant="standard"
          required
          fullWidth
          class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
          id="role"
          name="role"
          value={user.role}
          onChange={(e) => handleFormChange(e)}
          type="text"
          placeholder="Your role"
        />
      </div>
      <>
        {loading ? (
          <CircularProgress variant="indeterminate" />
        ) : props.isAddUserMode ? (
          <Button
            type="submit"
            onClick={saveUser}
            size="small"
            variant="contained"
          >
            <p class="text-white font-bold">Add user</p>
          </Button>
        ) : (
          <div class="flex justify-between items-center">
            <Button
              type="submit"
              onClick={saveUser}
              size="small"
              variant="contained"
            >
              <p class="text-white font-bold">Signup</p>
            </Button>
            <span class="flex-1"></span>
            <p class="mx-2">Already have a account?</p>
            <Button
              onClick={console.log("login")}
              size="small"
              variant="contained"
            >
              <p class="text-white font-bold">
                <NavLink to="/login">Login</NavLink>
              </p>
            </Button>
          </div>
        )}
      </>
      <Snackbar
        open={openSnackbar}
        autoHideDuration={2000}
        onClose={handleCloseSnackbar}
      >
        <Alert severity={signUpSuccess ? "success" : "error"}>
          {signUpSuccess
            ? "Successfully created user."
            : "Something went wrong try again."}
        </Alert>
      </Snackbar>
    </form>
  );
};

export default SignupForm;
