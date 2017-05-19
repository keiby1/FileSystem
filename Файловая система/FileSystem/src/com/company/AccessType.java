package com.company;

public class AccessType {   //Доступ к файлу
    private String user;    //логин пользователя
    private String acces;   //режим доступа

    public void setUser(String user) {
        this.user = user;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public String getUser() {
        return user;
    }

    public String getAcces() {
        return acces;
    }

}
