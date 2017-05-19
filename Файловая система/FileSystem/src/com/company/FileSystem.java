package com.company;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FileSystem {               //фаловая система
    private ArrayList<File> arrayFile;  //массив файлов

    public FileSystem(String file) {    //конструктор класса. Считывает данные из файла
        arrayFile = new ArrayList<>();

        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String t;
            while ((t = r.readLine()) != null) {
                File newFile = new File();
                newFile.setName(t);
                newFile.setAddress(r.readLine());
                newFile.setType(r.readLine());
                newFile.setVolume(r.readLine());
                newFile.setDate(r.readLine());
                newFile.setAutor(r.readLine());
                newFile.setAccess();
                arrayFile.add(newFile);
            }
            r.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CloseSession(String file) { //метод для записи данных в файл
        try (FileWriter fw = new FileWriter(file, false)) {
            for (File i : arrayFile) {
                fw.append(i.getName() + "\r\n");
                fw.append(i.getAddress() + "\r\n");
                fw.append(i.getType() + "\r\n");
                fw.append(i.getVolume() + "\r\n");
                fw.append(i.getDate() + "\r\n");
                fw.append(i.getAutor() + "\r\n");
                i.SaveAccess();
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void OutFile() { //вывод информации о файлах на экран
        String h1 = "Имя",
                h2 = "Тип",
                h3 = "Вес",
                h4 = "Создатель",
                h5 = "Путь",
                h6 = "Дата",
                h7 = "Пользователи и доступ";
        if (arrayFile.size() == 0)
            System.out.println("Пусто!");
        else {
            System.out.printf("%7s | %5s | %5s | %10s | %10s | %10s | %5s \n", h1, h2, h3, h4, h5, h6, h7);
            for (File file : arrayFile) {
                System.out.printf("%7s | %5s | %5s | %10s | %10s | %10s | %5s \n", file.getName(), file.getType(), file.getVolume(),
                        file.getAutor(), file.getAddress(), file.getDate(), file.getAccess().toString());
            }
        }
    }

    public void AddFile() { //метод для добавления файлов в массив
        try {
            Scanner scanner = new Scanner(System.in);
            File newFile = new File();      //создание файла

            //ввод информации и добавление её в поля файла
            String tmp;
            System.out.println("Введите имя файла: ");
            tmp = scanner.nextLine();
            newFile.setName(tmp);

            System.out.println("Введите адрес файла: ");
            tmp = scanner.nextLine();
            newFile.setAddress(tmp);

            System.out.println("Введите тип файла: ");
            tmp = scanner.nextLine();
            newFile.setType(tmp);

            System.out.println("Введите объем файла: ");
            tmp = scanner.nextLine();
            newFile.setVolume(tmp);

            System.out.println("Введите дату создания файла: ");
            tmp = scanner.nextLine();
            newFile.setDate(tmp);

            System.out.println("Введите логин создателя файла: ");
            tmp = scanner.nextLine();
            newFile.setAutor(tmp);

            newFile.AddAccess();

            arrayFile.add(newFile);     //добавление файла в массив
            System.out.println("Файл добавлен!");
        } catch (Exception e) {
            System.out.println("Файл не добавлен!");
            System.out.println(e.getMessage());

        }
    }

    public void DeleteFile() {  //удаление файла
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя файла и тип(в формате: Имя_файла.Тип): ");
        String delFile = sc.nextLine();
        for (File i : arrayFile) {
            if ((i.getName() + "." + i.getType()).equals(delFile)) {
                arrayFile.remove(i);
                break;
            }
        }
    }

    public void SortFile_VolumeUp() {   //сортировка файлов по вохрастанию объема
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.getVolume()) - Integer.parseInt(o2.getVolume());
            }
        });
    }

    public void SortFile_VolumeDown() {//сортировка файлов по убыванию объема
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o2.getVolume()) - Integer.parseInt(o1.getVolume());
            }
        });
    }

    public void SortFile_NameDown() {//сортировка файлов по имени файла в порядке убывания
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o2.getName().charAt(0) - o1.getName().charAt(0);
            }
        });
    }

    public void SortFile_NameUp() {//сортировка файлов по имени в порядке возрастания
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().charAt(0) - o2.getName().charAt(0);
            }
        });
    }

    public void SortFile_TypeUp(){
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getType().charAt(0) - o2.getType().charAt(0);
            }
        });
    }

    public void SortFile_TypeDown(){
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o2.getType().charAt(0) - o1.getType().charAt(0);
            }
        });
    }

    public void SearchFile() {  //метод для поиска файла. Ищет по 4м критериям. Выводит точные совдания и похожие по имени и типу.
        Scanner scanner = new Scanner(System.in);
        String name, type, autor, vol;
        boolean flagSearch = false;
        ArrayList<File> request = new ArrayList<>();

        System.out.println("Введите имя файла: ");
        name = scanner.nextLine();
        System.out.println("Введите тип файла: ");
        type = scanner.nextLine();
        System.out.println("Введите логин создателя файла: ");
        autor = scanner.nextLine();
        System.out.println("Введите объем файла: ");
        vol = scanner.nextLine();

        for (File file : arrayFile) {
            if (file.getName().equals(name) && file.getType().equals(type) && file.getAutor().equals(autor) && file.getVolume().equals(vol)) {
                System.out.println(file.toString());
                flagSearch = true;
            } else if (file.getName().equals(name) && file.getType().equals(type))
                request.add(file);
        }
        if (!flagSearch)
            System.out.println("Точных совпадений нет");
        if (request.size() == 0)
            System.out.println("Похожих не найдено");
        else {
            System.out.println("Похожие: ");
            for (File file : request)
                System.out.println(file.toString());
        }
    }

    public void Volume() {  //мтод попределения объема фалов размещенных определенным пользователем
        ArrayList<String> listUsers = new ArrayList<>();    //список пользователей размещавших что-либо
        boolean flagUser = false;
        for (File file : arrayFile) {
            for (String user : listUsers) {
                if (file.getAutor().equals(user)) {         //если пользователь уже есть в списке, тоничего не делать
                    flagUser = true;
                }
            }
            if (!flagUser) {                                //иначе добавить его в список
                listUsers.add(file.getAutor());
            }
            flagUser = false;
        }

        ArrayList<Integer> listVol = new ArrayList<>();    //список объемов фалов, разещенных каждым пользователем
        int vol = 0;

        for (String user : listUsers) {
            for (File file : arrayFile) {
                if (user.equals(file.getAutor())) {
                    vol += Integer.parseInt(file.getVolume());
                }
            }
            listVol.add(vol);
            vol = 0;
        }
        String s1 = "Логин",
                s2 = "Общий объем файлов";
        System.out.printf("%7s | %7s \n", s1, s2);
        for (int i = 0; i < listUsers.size(); i++) {
            System.out.printf("%7s | %7s \n", listUsers.get(i), listVol.get(i));
        }
    }

    public void ChkAccess() {   //метод для проверки доступности файла для определенного пользоваеля
        Scanner scanner = new Scanner(System.in);
        String user, file;
        boolean flag = true;
        System.out.println("Введите логин: ");
        user = scanner.nextLine();
        System.out.println("Введите имя файла: ");
        file = scanner.nextLine();

        for (File i : arrayFile) {
            if (i.getName().equals(file)) {
                i.getAccess().CHK(user);
                flag = false;
            }
        }
        if (flag)
            System.out.println("Данный пользователь не имеет доступа к этому файлу, либо файла не существует!");
    }

    public void SortFile_DateUp() {
        arrayFile.sort(new Comparator<File>() {     //сортировка по числам
            @Override
            public int compare(File o1, File o2) {
                int d1 = Integer.parseInt(o1.getDate().substring(0, 2)),
                        d2 = Integer.parseInt(o2.getDate().substring(0,2));
                return d2 - d1;
            }
        });


        arrayFile.sort(new Comparator<File>() { //сортировка по месяцам
            @Override
            public int compare(File o1, File o2) {
                int d1 = Integer.parseInt(o1.getDate().substring(3, 5)),
                        d2 = Integer.parseInt(o2.getDate().substring(3,5));
                return d2 - d1;
            }
        });

            arrayFile.sort(new Comparator<File>() {//сортировка по годам
                @Override
                public int compare(File o1, File o2) {
                    int d1 = Integer.parseInt(o1.getDate().substring(6, 10)),
                    d2 = Integer.parseInt(o2.getDate().substring(6,10));
                    return d2 - d1;
                }
            });
    }

    public void SortFile_DateDown() {
        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int d1 = Integer.parseInt(o1.getDate().substring(0, 2)),
                        d2 = Integer.parseInt(o2.getDate().substring(0,2));
                return d1 - d2;
            }
        });


        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int d1 = Integer.parseInt(o1.getDate().substring(3, 5)),
                        d2 = Integer.parseInt(o2.getDate().substring(3,5));
                return d1 - d2;
            }
        });

        arrayFile.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int d1 = Integer.parseInt(o1.getDate().substring(6, 10)),
                        d2 = Integer.parseInt(o2.getDate().substring(6,10));
                return d1 - d2;
            }
        });
    }
}
