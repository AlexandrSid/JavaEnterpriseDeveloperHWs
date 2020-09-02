package test.java.lesson7;

import main.java.com.alexsid.lesson7.MultiFactorial;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MultiFactorialTest {
    private List<Integer> listToSplit;
    private MultiFactorial mf;

    @Before
    public void setUp() throws Exception {
        listToSplit = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        mf = new MultiFactorial();
    }

    @After
    public void tearDown() throws Exception {
        listToSplit = null;
        mf = null;
    }

    @Test
    public void partitionTest(){
        List<List<Integer>> partitions = mf.partition(listToSplit, 3);
        List<List<Integer>> expectedPartitions = new ArrayList<>();
        expectedPartitions.add(Arrays.asList(1,2,3));
        expectedPartitions.add(Arrays.asList(4,5,6));
        expectedPartitions.add(Arrays.asList(7,8,9));
        expectedPartitions.add(Arrays.asList(10,11,12));
        expectedPartitions.add(Arrays.asList(13,14,15));
        expectedPartitions.add(Arrays.asList(16,17,18));
        expectedPartitions.add(Arrays.asList(19,20));
        assertEquals(expectedPartitions,partitions);
    }

    @Test
    public void computeFactorialTest(){
        try {
            BigInteger bigInteger = mf.computeFactorial(20);
            assertEquals("2432902008176640000", bigInteger.toString());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resultCachedTest(){
        try {
            mf.computeFactorial(20);
            assertEquals("2432902008176640000", mf.getKnownFactorials().get(20).toString());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}