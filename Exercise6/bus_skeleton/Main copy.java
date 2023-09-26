import java.util.concurrent.CompletableFuture;

class Main {
    public static void main(String[] args) {
        CompletableFuture<Void> cf1 = BusAPI.getBusStopsServedBy("95")
            .thenAccept(x -> System.out.println(x));
        CompletableFuture<Void> cf2 = BusAPI.getBusStopsServedBy("96")
            .thenAccept(x -> System.out.println(x));
        cf1.join();
        cf2.join();
    }
}

