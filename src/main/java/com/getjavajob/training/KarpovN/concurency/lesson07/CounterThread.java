package com.getjavajob.training.KarpovN.concurency.lesson07;

import java.util.LinkedHashMap;
import java.util.Map;

public class CounterThread implements Runnable {

    private final Character literal;
    private final String message;
    private Map<Character, Integer> map;

    public CounterThread(Character literal, String message) {
        this.literal = literal;
        this.message = message;
    }

    public Map<Character, Integer> getMap() {
        return map;
    }

    @Override
    public void run() {
        map = new LinkedHashMap<>();
        String text = message.toLowerCase();
        char[] charArray = text.toCharArray();
        for (char key : charArray) {
            if (key == literal) {
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
        }
    }
}