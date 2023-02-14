package ru.nsu.aramazanova1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

class Thread1 implements Callable {

    List<Integer> partOfList;
    int idx1;
    int idx2;
    public Thread1(List<Integer> partOfList, int idx1, int idx2){
        this.partOfList = partOfList;
        this.idx1 = idx1;
        this.idx2 = idx2;
    }

    @Override
    public Boolean call() throws Exception {
        return IntStream.range(idx1, idx2).allMatch(idx -> BigInteger.valueOf(partOfList.get(idx)).isProbablePrime(100));
    }
}

public class PrimesThread {

    public boolean threadCheck(int countThreads, List<Integer> listOfNumbers){
        AtomicBoolean ans = new AtomicBoolean(false);
        int sizeList = listOfNumbers.size();
        if(countThreads > sizeList){
            countThreads = sizeList;
        }
        int div = sizeList/countThreads;
        int mod = sizeList%countThreads;
        List<FutureTask<Boolean>> list = new ArrayList<>();
        for(int i = 0; i < div - mod; i++){
            list.add(new FutureTask<Boolean>(
                    new Thread1(listOfNumbers, i * (div + 1), i * div + div)));
        }
        for(int i = div - mod; i < div; i++){
            list.add(new FutureTask<Boolean>(
                    new Thread1(listOfNumbers, i * (div + 1), i * (div + 1) + div + 1)));
            new Thread(list.get(i)).start();
        }
        return list.stream().allMatch(x -> {
            try {
                return x.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
