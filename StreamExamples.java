import java.util.*;
import java.util.stream.Collectors;

public class StreamExamples {

    public static void main(String[] args){


        List<List<String>> lists =new ArrayList<>();

        lists = Arrays.asList(
                Arrays.asList("java", "python", "c++"),
                Arrays.asList("jet", "joker", "fan"),
                Arrays.asList("jemon", "joker", "basket")
        );

        Set<String> s = new HashSet<>();

        List<String> result = lists.stream().
                flatMap(List:: stream).
                filter(s1->s1.contains("j")).
                distinct().
                sorted().
                map(s1->s1.toLowerCase()).
                peek(s1->s.add(s1))
                .collect(Collectors.toList());

        System.out.println(result.toString());

        List<Integer> ages = Arrays.asList(0,1 , -1, 2, -3, 7, 9);

        List<Integer> resultSet = ages.stream().
                filter(age ->age>0).
                distinct().
                sorted().
                collect(Collectors.toList());

        System.out.println(resultSet.toString());



    }
}
