package cz.pv239.seminar2;

public class User {

    private String mName;
    private String mSurname;

    public User(String name, String surname) {
        mName = name;
        mSurname = surname;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }
}
