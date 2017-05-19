package com.company;

import java.util.Scanner;

public class File {
    private String Name;        //имя файла
    private String Address;     //адрес файла
    private String Type;        //тип файла
    private String Volume;      //объем файла
    private String Date;        //дата создания
    private String Autor;       //логин создателя файла
    private AccessList Access;  //режим доступа

    //--------------сеттеры---------
    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public void setAccess() {
        Access = new AccessList(getName() + ".txt");
    }

    //-------------гетеры----------------

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getType() {
        return Type;
    }

    public String getVolume() {
        return Volume;
    }

    public String getDate() {
        return Date;
    }

    public String getAutor() {
        return Autor;
    }

    public AccessList getAccess() {
        return Access;
    }

    //-----------------------------------

    public String toString() {  //преобразование в строку
        return Name + " " + Type + " " + Volume + " " + Autor + " " + Address + " " + Date + " " + Access.toString();
    }

    public void SaveAccess() {  //метод для сохранения списка доступа в файл
        Access.SaveAcces(getName() + ".txt");
    }

    public void AddAccess() {   //метод для добавления файлу режима доступа для пользовтаелей
        Access = new AccessList();
        String user, access;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Для выхода введите -");
            System.out.println("Введите логин: ");
            user = scanner.nextLine();
            if (user.equals("-")) {
                break;
            }
            System.out.println("Режим доступа в формате rwx, где\n r чтение, w запись, x выполнение, - нет доступа");
            System.out.println("Примеры: rwx или rw- или ---");
            System.out.println("Введите: ");
            access = scanner.nextLine();

            AccessType accessType = new AccessType();
            accessType.setUser(user);
            accessType.setAcces(access);
            Access.add(accessType);
        }
    }
}
