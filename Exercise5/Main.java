import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class Main {
    private static final int three = 3; 

    static boolean isPrime(int n) {
        return IntStream.range(2,n).noneMatch(x -> n % x == 0);
    }

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(three, n)
            .filter(x -> isPrime(x) && (isPrime(x - 2) || isPrime(x + 2)));
    }

    static String reverse(String str) {
        return Stream.<String>of(str.split("")).reduce("", (rev, ch) -> ch + rev);
    }

    static long countRepeats(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
            .filter(i -> list.get(i).equals(list.get(i + 1)))
            .filter(i -> i == 0 || !list.get(i - 1).equals(list.get(i)))
            .count();
    }

    //.range generates stream of integers, then map applies lambda to each index
    static UnaryOperator<List<Integer>> generateRule() {
        return list -> {
            int size = list.size();
            return IntStream.range(0, size).map(i -> {
                if (list.get(i) == 1) {
                    return 0;
                } else if (i > 0 && i < size - 1) {
                    if ((list.get(i - 1) == 1 && list.get(i + 1) == 0) || 
                            (list.get(i + 1) == 1 && list.get(i - 1) == 0)) {
                        return 1;
                    }
                } else if (i == 0 && list.get(i + 1) == 1) {
                    return 1;
                } else if (i == size - 1 && list.get(i - 1) == 1) {
                    return 1;
                }
                return 0;
            }).boxed().collect(Collectors.toList());
        };
    }
    //.boxed to convert Stream of int to Stream of Integer
    //.collect collects stream of Inetger into a new List<Integer>

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, rule)
            .limit(n)
            .map(cells -> cells.stream().map(cell -> (cell == 1) ? "x" : ".")
                    .reduce("", (c1, c2) -> c1 + c2));
    }
}
//List.<Integer>of will expect elements
//forEach to replace for loop


