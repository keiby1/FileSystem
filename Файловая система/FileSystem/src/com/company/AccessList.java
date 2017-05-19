package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccessList {   //список доступа к файлу
    ArrayList<AccessType> arrayList;

    //конструкторы
    public AccessList() {
        arrayList = new ArrayList<>();
    }

    public AccessList(String file) {
        arrayList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String t;
            while ((t = r.readLine()) != null) {
                AccessType accessType = new AccessType();
                accessType.setUser(t);
                accessType.setAcces(r.readLine());
                arrayList.add(accessType);
            }
            r.close();
        } catch (IOException e) {

        }
    }

    //методы
    public void SaveAcces(String file) {    //метод для записи доступа к файлу в текстовый файл
        try (FileWriter fw = new FileWriter(file, false)) {
            for (AccessType i : arrayList) {
                fw.append(i.getUser() + "\r\n");
                fw.append(i.getAcces() + "\r\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void OutAcces() {    //вывод на экран списка пользователей и режима доступа к файлу для них
        System.out.println(arrayList);
    }

    @Override
    public String toString() {//преобразование встроку
        String s = "";
        for (AccessType i : arrayList) {
            s += i.getUser() + " " + i.getAcces() + ";";
        }
        return s;
    }

    public void add(AccessType accessType) {//добавление пользоватлея и ежима доступа для него, к данному файлу
        arrayList.add(accessType);
    }

    public void CHK(String user) {  //вывод на экран доступа к данному файлу для заданного пользователя
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getUser().equals(user)) {
                System.out.println(user + " " + arrayList.get(i).getAcces());
                break;
            }
        }
    }
}
