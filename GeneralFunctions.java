import java.util.*;

public class GeneralFunctions {

    static Random rand = new Random();

    private GeneralFunctions(){
        throw new IllegalStateException("Utility class");
    }

    public static int generate(int min, int max){
        int range = max - min;
        if(min > max){
            return -1;
        }
        int n = rand.nextInt(range + 1);
        return n + min;
    }
}