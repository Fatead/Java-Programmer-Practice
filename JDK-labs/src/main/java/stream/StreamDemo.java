package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Java 8 Stream 使用声明式的方式处理数据
 * 将要处理的元素集合看作是一种流，流在管道中传输，并且可以在管道的节点上进行处理，
 * 比如筛选、排序、聚合等。
 * 元素流在管道中经过中间操作的处理，最后由最终操作得到前面处理的结果
 *
 * stream of elements --->  filter -> sorted -> map -> collect
 */
public class StreamDemo {


    private static List<String> filterByCondition(List<String> list){
        long count = list.stream().filter(str->!str.isEmpty()).count();
        System.out.println(count);
        return list.stream().filter(str->!str.isEmpty()).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = filterByCondition(list);
        Random random = new Random();
        //random.ints().limit(10).forEach(System.out::println);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squareList = numbers.stream().map(a->a*a).distinct().collect(Collectors.toList());
        String mergeString = list.stream().filter(str->!str.isEmpty()).collect(Collectors.joining("-"));
        System.out.println(mergeString);
        System.out.println(squareList);

    }

}
