import React from "react";
import { Button } from "@mui/material";
import { NavLink } from "react-router-dom";

export const Navbar = () => {
  return (
    <div class="bg-gray-800">
      <div class="h-16 px-8 flex items-center">
        <p class="text-white font-bold">Simple CURD App</p>

        <span class="flex-1"></span>

        <div class="mx-4">
          <Button variant="contained">
            <p class="text-white font-bold">
              <NavLink to="/login">Logout</NavLink>
            </p>
          </Button>
        </div>
      </div>
    </div>
  );
};
