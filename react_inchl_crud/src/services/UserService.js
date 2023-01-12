import axios from "axios";

const USER_API_BASE_URL = "http://127.0.0.1:8082/api/v1";
const CREATE_USER = "/createUser";
const GET_ALL_USERS = "/getAllUsers";
const UPDATE_USER = "/updateUser";
const GET_USER = "/getUser";
const DELETE_USER = "/deleteUser";

class UserService {
  saveUser(user) {
    return axios.post(USER_API_BASE_URL + CREATE_USER, user, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  getAllUsers() {
    return axios.get(USER_API_BASE_URL + GET_ALL_USERS, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  updateUser(userId, user) {
    return axios.put(USER_API_BASE_URL + UPDATE_USER + `/${userId}`, user, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  deleteUser(userId) {
    return axios.delete(USER_API_BASE_URL + DELETE_USER + `/${userId}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}

export default new UserService();
