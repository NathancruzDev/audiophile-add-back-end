package com.example.back_end.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class OrderHashCodeService {
    Random generateRandom=new Random();

        Integer codeGeneration(){
            ArrayList<String> hash = new ArrayList<>();
            Integer n;
            for(int i=0;i <= 3; i++){
                n=generateRandom.nextInt();
                hash.add(n.toString());
            }
            String number=hash.stream().reduce("",(a,b)-> a+b);
            Integer numberCreated=Integer.parseInt(number);
            return numberCreated;
        }
}
