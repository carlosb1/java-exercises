package exercises;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by carlos on 2/12/17.
 */
public class Exercises {
    public static void main (String args []) {
       // Exercise5_3();
        Exercise5_4();

    }

    private static void Exercise5_3() {
        Set<Integer> numbers = new HashSet<>(asList(4,3,2,1));
        List<Integer> sameOrder = numbers.stream().sorted().collect(toList());
        sameOrder.forEach(v->System.out.println( " Count : " + v));
    }

    private static void Exercise5_4() {
        List<Integer> numbers = asList(1,2,3,4);
        List<Integer> stillOrdered =  numbers.stream().map(x -> x+1).collect(toList());
        stillOrdered.forEach(v->System.out.println( " Count : " + v));
    }




}
