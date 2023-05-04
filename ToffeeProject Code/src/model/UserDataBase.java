package model;

import actors.User;

import java.util.ArrayList;

public class UserDataBase {
    private ArrayList<User> userList;
    public void addUser(User user){

    };

    public void removeUser(User user){

    }
    public boolean checkIfUserFound(User user){
        return false;
    }

    public User getUser(String userName){
        return userList.get(0);
    }
}
