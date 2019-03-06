package ru.stqa.pft.mantis.model;

public class MantisUser {
    public final int id;
    public final String username;
    public final String email;

    public MantisUser(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
