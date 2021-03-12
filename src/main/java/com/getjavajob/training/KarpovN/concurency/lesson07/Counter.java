package com.getjavajob.training.KarpovN.concurency.lesson07;

import static java.nio.charset.Charset.forName;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Counter{

    private static final Thread[] threads = new Thread[33];
    private static final CounterThread[] counterThreads = new CounterThread[33];
    private static final char[] ALPHABET = {'à', 'á', 'â', 'ã', 'ä', 'å', '¸', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò',
        'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ'};

    public static void main(String[] args) throws InterruptedException {
        String message = readFromFile();
        Map<Character, Integer> map = new LinkedHashMap<>();
        System.out.println(threadMethod(map, message).toString());
    }

    public static Map<Character, Integer> threadMethod(Map<Character, Integer> map, String message) throws InterruptedException {
        for (int i = 0; i < ALPHABET.length; i++) {
            CounterThread counterThread = new CounterThread(ALPHABET[i], message);
            Thread thread = new Thread(counterThread);
            threads[i] = thread;
            thread.start();
            counterThreads[i] = counterThread;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
            map.putAll(counterThreads[i].getMap());
        }
        return sortByValue(map);
    }

    private static String readFromFile() {
        String line = null;
        try (
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt"), forName("Cp1251")))
        ) {
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Catch Error");
            e.printStackTrace();
        }
        return line;
    }

    private static Map<Character, Integer> sortByValue(Map<Character, Integer> map)
    {
        Map<Character, Integer> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet()
            .stream()
            .sorted(comparingByValue(reverseOrder()))
            .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
}