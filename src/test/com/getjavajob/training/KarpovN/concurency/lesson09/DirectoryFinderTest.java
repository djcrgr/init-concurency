package com.getjavajob.training.KarpovN.concurency.lesson09;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectoryFinderTest {

    private final String sMask = "txt";

    private File expectedFile;
    List<String> expectedFileList;
    List<String> expectedFileList2;

    @Before
    public void setUp() {
        expectedFile = new File("tr");
        expectedFileList = new ArrayList<>();
        expectedFileList2 = new ArrayList<>();
        File[] files = expectedFile.listFiles();
        for (File runner : files) {
            if (runner.isFile()) {
                expectedFileList.add(runner.getName());
            }
            expectedFileList2.add(runner.getName());
        }
    }

    @Test
    public void masksListTest() {
        List<File> list = DirectoryFinder.masksList(expectedFile, sMask);
        List<String> resultList = new ArrayList<>();
        for (File runner : list) {
            resultList.add(runner.getName());
        }
        assertEquals(expectedFileList, resultList);
    }

    @Test
    public void noMaskList() {
        List<File> list = DirectoryFinder.noMaskList(expectedFile);
        List<String> resultList = new ArrayList<>();
        for (File runner : list) {
            resultList.add(runner.getName());
        }
        assertEquals(expectedFileList2, resultList);
    }
}