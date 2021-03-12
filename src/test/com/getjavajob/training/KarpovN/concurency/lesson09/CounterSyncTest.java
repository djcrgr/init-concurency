package com.getjavajob.training.KarpovN.concurency.lesson09;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.getjavajob.training.KarpovN.concurency.lesson09.CounterSync.threadMethod;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CounterSyncTest {

    @Test
    public void threadMethodTest() throws InterruptedException, ExecutionException {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Map<Character, Integer> exceptedMap = new LinkedHashMap<>();
        String message = "������";
        exceptedMap.put('�', 3);
        exceptedMap.put('�', 2);
        exceptedMap.put('�', 1);
        assertThat(threadMethod(message), is(exceptedMap));
    }
}