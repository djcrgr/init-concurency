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
        String message = "������";
        exceptedMap.put('�', 3);
        exceptedMap.put('�', 2);
        exceptedMap.put('�', 1);
        assertThat(threadMethod(map, message), is(exceptedMap));
    }
}