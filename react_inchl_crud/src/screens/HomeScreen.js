import { React, useState } from "react";
import EditForm from "../components/EditForm";
import { Navbar } from "../components/Navbar";
import SignupForm from "../components/Signup";
import Users from "../components/Users";

const HomeScreen = () => {
  const [isAddUserMode, setAddUserMode] = useState(false);
  const [isUserAddedSuccessfully, setIsUserAddedSuccessfully] = useState(false);

  return (
    <div>
      <Navbar />
      <div class="flex justify-center">
        <div class="block p-6 rounded-lg shadow-lg bg-white w-full">
          <h4 class="text-gray-900 text-xl font-bold mb-4"> List Users</h4>
          <div class="my-2">
            {isAddUserMode ? (
              <SignupForm
                props={{
                  isAddUserMode,
                  setAddUserMode,
                  isUserAddedSuccessfully,
                  setIsUserAddedSuccessfully,
                }}
              />
            ) : (
              ""
            )}
          </div>
          <Users
            setAddUserMode={setAddUserMode}
            isAddUserMode={isAddUserMode}
            isUserAddedSuccessfully={isUserAddedSuccessfully}
          />
        </div>
      </div>
    </div>
  );
};

export default HomeScreen;
