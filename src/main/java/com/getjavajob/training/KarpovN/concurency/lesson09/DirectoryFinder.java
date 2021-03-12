package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DirectoryFinder {

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("enter path... for exit print quit");
            String path = getString();
            if (path.equals("quit")) {
                break;
            }
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("one more time or print exit");
            }
            if (!file.isDirectory()) {
                System.out.println("Not directory: " + path);
                continue;
            }
            System.out.println("print extension...");
            String sMask = getString();
            List<File> fileList;
            if (sMask.isEmpty()) {
                fileList = noMaskList(file);
            } else {
                fileList = masksList(file, sMask);
            }
            printAll(fileList);
        }

    }


    public static List<File> masksList(File file, String sMask) {
        Deque<File> stack = new ArrayDeque<>();
        List<File> list = new ArrayList<>();
        stack.add(file);
        while (!stack.isEmpty()) {
            File element = stack.pollLast();
            if (element.getAbsolutePath().endsWith(sMask)) {
                list.add(element);
            }
            if (element.isDirectory()) {
                File[] files = element.listFiles();
                for (int i = files.length - 1; i >= 0; i--) {
                    stack.add(files[i]);
                }
            }
        }
        return list;
    }

    public static List<File> noMaskList(File baseDirectory) {
        List<File> list = new ArrayList<>();
        if (baseDirectory.isDirectory()) {
            for (File file : baseDirectory.listFiles()) {
                if (file.isFile()) {
                    list.add(file);
                } else {
                    list.add(file);
                    noMaskList(file);
                }
            }
        }
        return list;
    }

    private static void printAll(List<File> list) {
        for (File files : list) {
            if (files.isFile()) {
                System.out.println(files.getName() + " execute is " + files.canExecute() + " read is " + files.canRead() + " write is "
                        + files.canWrite() + " space is " + files.getTotalSpace() + " last modification " + new Date(files.lastModified() * 1000));
            }
            if (files.isDirectory()) {
                System.out.println(files.getName() + " execute is " + files.canExecute() + " read is " + files.canRead() + " write is "
                        + files.canWrite() + " last modification " + new Date(files.lastModified() * 1000));
            }
        }
    }

    private static String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}