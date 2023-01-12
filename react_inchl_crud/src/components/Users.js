import { Alert, Button, MenuItem, Select, Snackbar } from "@mui/material";
import { React, useEffect, useState } from "react";
import UserService from "../services/UserService";

const Users = ({ isAddUserMode, setAddUserMode, isUserAddedSuccessfully }) => {
  const [allUsers, setAllUsers] = useState([]);
  const [editCurrentUser, setEditCurrentUser] = useState({
    fullName: "",
    email: "",
    userType: "",
  });
  const [inEditMode, setInEditMode] = useState({
    status: false,
    rowKey: null,
  });
  const [isUpdated, setIsUpdated] = useState(false);
  const [snackBar, setSnackBar] = useState({
    msg: "",
    isSuccess: false,
    openSnackbar: false,
  });

  useEffect(() => {
    getAllUsers();
  }, [isUpdated, isUserAddedSuccessfully]);

  const getAllUsers = () => {
    UserService.getAllUsers()
      .then((response) => {
        setAllUsers(sortDataByAccendingNumeric(response.data));
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const updateUser = (userId) => {
    UserService.updateUser(userId, editCurrentUser)
      .then((response) => {
        setIsUpdated(!isUpdated);
        onCancelEdit();
        setSnackBar({
          isSuccess: true,
          openSnackbar: true,
          msg: "User successfully updated",
        });
      })
      .catch((error) => {
        onCancelEdit();
        setSnackBar({
          isSuccess: false,
          openSnackbar: true,
          msg: "Error while updating user.",
        });
      });
  };

  const deleteUser = (userId) => {
    UserService.deleteUser(userId)
      .then((response) => {
        setIsUpdated(!isUpdated);
        setSnackBar({
          isSuccess: true,
          openSnackbar: true,
          msg: "User deleted successfully",
        });
      })
      .catch((error) => {
        setSnackBar({
          isSuccess: false,
          openSnackbar: true,
          msg: "Error while deleting user.",
        });
      });
  };

  const sortDataByAccendingNumeric = (data) => {
    console.log(data);
    return data.sort((a, b) => a.id - b.id);
  };

  const handleSnackbar = () => {
    console.log(snackBar.msg);
    setSnackBar({
      msg: "",
      isSuccess: false,
      openSnackbar: false,
    });
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    console.log({ name, value });
    setEditCurrentUser({ ...editCurrentUser, [name]: value });
    console.log(editCurrentUser);
  };

  const onEditClick = (rowKey, currentEditUser) => {
    setInEditMode({
      status: true,
      rowKey: rowKey,
    });
    setEditCurrentUser(currentEditUser);
  };

  const onCancelEdit = () => {
    setInEditMode({
      status: false,
      rowKey: null,
    });
    setEditCurrentUser(null);
  };

  return (
    <div class="rounded-lg shadow-lg p-2 bg-white">
      {!isAddUserMode ? (
        <Button
          sx={{
            marginTop: 2,
            marginBottom: 2,
          }}
          variant="contained"
          size="small"
          onClick={() => setAddUserMode(!isAddUserMode)}
        >
          Add User
        </Button>
      ) : (
        ""
      )}
      <table class=" table-auto w-full text-left">
        <thead class="border-b py-2">
          <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Type</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody class="my-2">
          {allUsers.map((user) => (
            <tr
              key={user.id}
              class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100"
            >
              <td>
                {user.fullName}
                {/* Edit's Full name */}
                {inEditMode.status && inEditMode.rowKey === user.id ? (
                  <div class="edit py-2">
                    <div class="mb-4 pr-2 edit-fullName">
                      <input
                        class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
                        id="fullName"
                        name="fullName"
                        type="text"
                        placeholder=""
                        value={editCurrentUser.fullName}
                        onChange={(e) => handleFormChange(e)}
                      />
                    </div>
                  </div>
                ) : (
                  ""
                )}
              </td>
              <td>
                {user.email}
                {/* Edit's Email */}
                {inEditMode.status && inEditMode.rowKey === user.id ? (
                  <div class="edit py-2">
                    <div class="mb-4 pr-2 edit-email">
                      <input
                        class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
                        id="email"
                        name="email"
                        type="text"
                        placeholder=""
                        value={editCurrentUser.email}
                        onChange={(e) => handleFormChange(e)}
                      />
                    </div>
                  </div>
                ) : (
                  ""
                )}
              </td>
              <td class="px-3">
                {user.userType}
                {/* Edit's User Type */}
                {inEditMode.status && inEditMode.rowKey === user.id ? (
                  <div class="edit py-2">
                    <div
                      class="mb-4 edit-userType"
                      variant="filled"
                      sx={{ m: 1, minWidth: 150 }}
                    >
                      <Select
                        variant="standard"
                        labelId="userType"
                        id="userType"
                        name="userType"
                        value={editCurrentUser.userType}
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
                  </div>
                ) : (
                  ""
                )}
              </td>
              <td class="pb-3">
                {/* Action's Edit and Delete Buttons */}
                <div class="flex items-center">
                  <div class="mx-2">
                    <Button
                      onClick={() => onEditClick(user.id, user)}
                      size="small"
                      variant="contained"
                    >
                      <p class="text-white font-bold">Edit</p>
                    </Button>
                  </div>

                  <div>
                    <Button
                      onClick={() => deleteUser(user.id)}
                      color="error"
                      size="small"
                      variant="contained"
                    >
                      <p class="text-white font-bold">Delete</p>
                    </Button>
                  </div>
                </div>
                {/* Edit's Save Button */}
                {inEditMode.status && inEditMode.rowKey === user.id ? (
                  <div class=" py-2 flex edit edit-saveBtn items-center">
                    <div class="mx-2">
                      <Button
                        onClick={() => updateUser(user.id)}
                        color="success"
                        size="small"
                        variant="contained"
                      >
                        <p class="text-white font-bold">Save</p>
                      </Button>
                    </div>
                    <div class="">
                      <Button
                        onClick={onCancelEdit}
                        color="primary"
                        size="small"
                        variant="contained"
                      >
                        <p class="text-white font-bold">Cancel</p>
                      </Button>
                    </div>
                  </div>
                ) : (
                  ""
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Snackbar
        open={snackBar.openSnackbar}
        autoHideDuration={2000}
        onClose={() => handleSnackbar()}
      >
        <Alert severity={snackBar.isSuccess ? "success" : "error"}>
          {snackBar.msg}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default Users;
