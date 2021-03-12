package com.getjavajob.training.KarpovN.concurency.lesson07;

import static com.getjavajob.training.KarpovN.concurency.lesson07.Counter.threadMethod;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

public class CounterTest {

    @Test
    public void threadMethodTest() throws InterruptedException {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Map<Character, Integer> exceptedMap = new LinkedHashMap<>();
        String message = "сррстр";
        exceptedMap.put('р', 3);
        exceptedMap.put('с', 2);
        exceptedMap.put('т', 1);
        assertThat(threadMethod(map, message), is(exceptedMap));
    }
}