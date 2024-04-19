package com.example.recyclingapp;

import java.util.ArrayList;
import java.util.List;


public class UserService {
    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
    }

    public void createUser(String id,String username, String password, String email) {
        User newUser = new User(id,username,password,email);
        userList.add(newUser);
    }

    public void assignPointsForReward(String userId, int points) {
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                user.setPoints(user.getPoints() + points);
                break;
            }
        }
    }

    // Μέθοδος για ανάκτηση χρήστη βάσει του αναγνωριστικού χρήστη
    public User getUserById(String userId) {
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null; // Επιστρέφει null εάν δεν βρεθεί χρήστης με το συγκεκριμένο αναγνωριστικό
    }
}