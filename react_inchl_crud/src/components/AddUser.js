import { Button } from "@mui/material";
import React from "react";

const AddForm = () => {
  return (
    <div class="rounded-lg shadow-lg bg-white w-full">
      <form class="my-4 p-2">
        <h5 class="text-md text-bold my-2"> Edit User</h5>
        <div class="mb-4">
          <input
            class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
            id="fullname"
            type="text"
            placeholder="Your name"
          />
        </div>
        <div class="mb-4">
          <input
            class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
            id="email"
            type="text"
            placeholder="Your email"
          />
        </div>
        <div class="mb-4">
          <input
            class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
            id="password"
            type="text"
            placeholder="Your password"
          />
        </div>
        <div class="mb-4">
          <input
            class="p-2 shadow appearance-none border rounded w-full text-gray-800 leading-tight focus:outline-none"
            id="role"
            type="text"
            placeholder="Your role"
          />
        </div>

        <div>
          <Button
            onClick={console.log("click edit form")}
            type="submit"
            size="small"
            variant="contained"
          >
            <p class="text-white font-bold">Edit</p>
          </Button>
        </div>
      </form>
    </div>
  );
};

export default EditForm;
