package com.reeyabhatt.medicoodac;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String name;
    private String email;
    private String dr_uname;
    private String app_type;
    private String date;
    private String time;

    public User() {
    }

    public User(String name, String email, String dr_uname, String app_type, String date, String time) {
        this.name = name;
        this.email = email;
        this.dr_uname = dr_uname;
        this.app_type = app_type;
        this.date = date;
        this.time = time;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getDr_uname() { return dr_uname; }

    public String getApp_type() { return app_type; }

    public String getDate() { return date; }

    public String getTime() { return time; }
}
