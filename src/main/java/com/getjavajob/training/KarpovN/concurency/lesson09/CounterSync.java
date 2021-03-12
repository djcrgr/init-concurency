package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

import static java.nio.charset.Charset.forName;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;

public class CounterSync {


    private static final char[] ALPHABET = {'à', 'á', 'â', 'ã', 'ä', 'å', '¸', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò',
            'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ'};

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String message = readFromFile();
        System.out.println(threadMethod(message).toString());
    }

    public static Map<Character, Integer> threadMethod(String message) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch countDownLatch = new CountDownLatch(ALPHABET.length);
        Map<Character, Future<Integer>> map = new ConcurrentHashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            Future<Integer> future = service.submit(new CounterThreadSync(countDownLatch, ALPHABET[i], message));
            map.put(ALPHABET[i], future);
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            if (map.get(ALPHABET[i]).get() != 0) {
                map2.put(ALPHABET[i], map.get(ALPHABET[i]).get());
            }
        }
        service.shutdown();
        return sortByValue(map2);
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

    private static Map<Character, Integer> sortByValue(Map<Character, Integer> map) {
        Map<Character, Integer> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted(comparingByValue(reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
}