import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.List;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 */
class Main {
    static CompletableFuture<BusRoutes> processQuery(String query) {
        Scanner sc = new Scanner(query);
        BusStop srcBusStop = new BusStop(Integer.valueOf(sc.next()).toString());
        String searchString = sc.next();
        sc.close();
        return BusSg.findBusServicesBetween(srcBusStop, searchString);
    }

    public static void main(String[] args) {
        //Method 1: go through the list and print out one at a time
        //Method 2: Collect output and print out together
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<BusRoutes>> result = sc.useDelimiter("\n")
            .tokens()
            .map(s -> processQuery(s)) //process every query
            .toList();
        result.stream()
            .map(future -> future.thenCompose(br -> br.description()))
            .forEach(future -> System.out.println(future.join()));
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
