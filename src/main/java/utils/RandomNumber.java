package utils;

import java.util.Random;

public class RandomNumber {
    private static int max =1000000000;
    private static int min =100000000;

    public static int getRandomNumber(){
        Random random = new Random();
        int id = random.nextInt(max + 1-min)+min;
//         this.studentCode = random.nextInt(10000+ 1-1000)+1000;
        return id ;
    }
}
