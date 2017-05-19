package com.company;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Runtime.getRuntime;

public class Menu {             //главное меню
    public int key;             //ключ для ввода команд
    private boolean flagExit;   //флаг разрешащий/запрещабщий завершение приложения
    private FileSystem fileSystem;//объет для работы с файловой системой
    String file;                //путь и имя файла для считывания данных

    public Menu() {             //конструктор класса
        file = "DataBase.txt";  //имя файла по умолчанию
        fileSystem = new FileSystem(file);
        flagExit = true;
    }

    public void PrintMenu() {   //метод выводящий на экран главное меню приложения
        System.out.println("1)Вывести список файлов");
        System.out.println("2)Добавить файл");
        System.out.println("3)Удалить файл");
        System.out.println("4)Отсортировать файлы");
        System.out.println("5)Найти файл");
        System.out.println("6)Вывести объем файлов размещенных каждым пользователем");
        System.out.println("7)Проверить доступ для пользователя");
        System.out.println("8)Выход");
        System.out.print(">> ");
    }

    public void PrintSortMenu() {//метод выводящий на экран еню сортировки
        System.out.println("1)Сортировать по возрастанию объема файла");
        System.out.println("2)Сортировать по убыванию объема файла");
        System.out.println("3)Сортировать по названию файла(а-я)");
        System.out.println("4)Сортировать по названию файла(я-а)");
        System.out.println("5)Сортировать по типу файла(а-я)");
        System.out.println("6)Сортировать по типу файла(я-а)");
        System.out.println("7)Сортировать по дате(сначала новые)");
        System.out.println("8)Сортировать по дате(сначала старые)");
        System.out.print(">> ");
    }

    public void Run() {     //запуск основного меню приложения
        Scanner scanner = new Scanner(System.in);
        while (flagExit) {
            PrintMenu();    //вывод меню на экран
            key = scanner.nextInt();
            switch (key) {  //выбор действия
                case 1:
                    fileSystem.OutFile();   //вывод информации о файлах на экран
                    break;
                case 2:
                    fileSystem.AddFile();   //добавление файла
                    break;
                case 3:
                    fileSystem.DeleteFile();//удаление файла
                    break;
                case 4:
                    SortFile();             //сортировка файлов
                    break;
                case 5:
                    fileSystem.SearchFile();//поиск файлов
                    break;
                case 6:
                    fileSystem.Volume();    //определение объема файлов разещенных пользоваелем
                    break;
                case 7:
                    fileSystem.ChkAccess();//проверка доступа к файлу
                    break;
                case 8:
                    flagExit = false;       //выход из приложения
                    break;
            }
        }
        fileSystem.CloseSession(file);      //выход с сохранением
    }

    public void SortFile() {    //метод для выбора способа сортировки файлов
        PrintSortMenu();
        Scanner scanner = new Scanner(System.in);
        int keySort = scanner.nextInt();
        switch (keySort) {
            case 1:
                fileSystem.SortFile_VolumeUp();
                break;
            case 2:
                fileSystem.SortFile_VolumeDown();
                break;
            case 3:
                fileSystem.SortFile_NameUp();
                break;
            case 4:
                fileSystem.SortFile_NameDown();
                break;
            case 5:
                fileSystem.SortFile_TypeUp();
                break;
            case 6:
                fileSystem.SortFile_TypeDown();
                break;
            case 7:
                fileSystem.SortFile_DateUp();
                break;
            case 8:
                fileSystem.SortFile_DateDown();
                break;
        }
    }

}
