package com.tdr.wisdome;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Integer i1 = Integer.valueOf("00010a", 16);
        Integer i2 = Integer.valueOf("10a", 16);
        Integer i3 = Integer.valueOf("010A", 16);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
    }
}