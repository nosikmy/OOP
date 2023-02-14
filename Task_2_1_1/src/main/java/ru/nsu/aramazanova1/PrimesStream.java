package ru.nsu.aramazanova1;

import java.math.BigInteger;
import java.util.List;

public class PrimesStream {

    public boolean streamCheck(List<Integer> listOfNumbers){
        return  listOfNumbers.stream().allMatch(x -> BigInteger.valueOf(x).isProbablePrime(100));
    }

    public boolean parallelStreamCheck(List<Integer> listOfNumbers){
        return listOfNumbers.parallelStream().allMatch(x -> BigInteger.valueOf(x).isProbablePrime(100));
    }
}
