package ru.gb.java1.lesson7_files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        task0("generated_files");
        task1("generated_files/file1.txt", "generated_files/file2.txt");
        task2("generated_files/file1.txt", "generated_files/file2.txt", "generated_files/file3.txt");
        System.out.println(task3("generated_files/file3.txt", "word"));
        System.out.println(task4("generated_files", "word"));
        task5("generated_files", "word");
        System.out.println(task3("generated_files/file3.txt", "word"));
        System.out.println(task4("generated_files", "word"));
    }

    /*
        0. Проверить существование директории Если ее нет, создать
    */
    private static void task0(String dirname) {
        if (!Files.exists(Paths.get(dirname))) {
            new File(dirname).mkdir();
        }
    }

    /*
        1. Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет);
    */
    private static void task1(String filename1, String filename2) {
        generateRandomCharFile(filename1, 100);
        generateRandomCharFile(filename2, 50);
    }

    private static void generateRandomCharFile(String filename, int length) {
        Random rand = new Random();

        try {
            PrintStream ps = new PrintStream(filename);
            for (int i = 0; i < length; i++) {
                int b = rand.nextInt((int)'z' - (int)'a' + 1);
                ps.print((char)((int)'a' + b));
            }
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        2. Написать программу, «склеивающую» эти файлы,
        то есть вначале идет текст из первого файла, потом текст из второго в новый файл.
    */
    private static void task2(String filename1, String filename2, String filename3) {
        transferTo(filename1, filename3, false);
        transferTo(filename2, filename3, true);
    }

    private static void transferTo(String filenameFrom, String filenameTo, boolean append) {
        try {
            FileInputStream input = new FileInputStream(filenameFrom);
            FileOutputStream output = new FileOutputStream(filenameTo, append);

            int i;
            while((i = input.read()) != -1) {
                output.write(i);
            }

            input.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        3. * Написать программу, которая проверяет присутствует ли
        указанное пользователем слово в файле (работаем только с латиницей).
    */
    private static boolean task3(String filename, String word) {
        File file = new File(filename);
        return isFileContainsWord(file, word);
    }

    private static boolean isFileContainsWord(File file, String word) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));

            while (scanner.hasNext()) {
                if (scanner.next().equals(word)) {
                    return true;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
        4. ** Написать метод, проверяющий, есть ли указанное слово в файлах в папке
    */
    private static boolean task4(String dirname, String word) {
        File dir = new File(dirname);
        return findWordInDirectory(dir, word);
    }

    private static boolean findWordInDirectory(File dir, String word) {
        for (File fileEntry : dir.listFiles()) {
            if (fileEntry.isDirectory()) {
                findWordInDirectory(fileEntry, word);
            } else {
                if (isFileContainsWord(fileEntry, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
        5. *** Написать метод, добавляющий, указанное слово в файлы в папке
    */
    private static void task5(String dirname, String word) {
        File dir = new File(dirname);
        appendWordToDirectory(dir, word);
    }

    private static void appendWordToDirectory(File dir, String word) {
        for (File fileEntry : dir.listFiles()) {
            if (fileEntry.isDirectory()) {
                findWordInDirectory(fileEntry, word);
            } else {
                appendWordToFile(fileEntry, word);
            }
        }
    }

    private static void appendWordToFile(File file, String word) {
        try {
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pr = new PrintWriter(fr);
            pr.println(" " + word);
            pr.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


