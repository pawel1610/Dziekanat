package pl.pwn.reaktor.dziekanat.model.utiils;

import pl.pwn.reaktor.dziekanat.model.User;

public class CurrentUser {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static void cleanCurrentUser(){
        CurrentUser.currentUser = null;
    }
}
