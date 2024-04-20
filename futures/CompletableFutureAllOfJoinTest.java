package futures;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureAllOfJoin
 */
public class CompletableFutureAllOfJoinTest {

        public static void main(String[] args) throws URISyntaxException {
                var client = HttpClient.newBuilder().build();
                var uri = new URI("https://google.com");
                var request = HttpRequest.newBuilder(uri).build();
                var handler = HttpResponse.BodyHandlers.ofString();
                CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(
                                client.sendAsync(request, handler)
                                                .thenAccept((resp) -> System.out.println(resp.body())),
                                client.sendAsync(request, handler)
                                                .thenAccept((resp) -> System.out.println(resp
                                                                .body())),
                                client.sendAsync(request, handler)
                                                .thenAccept((resp) -> System.out.println(resp
                                                                .body())),
                                client.sendAsync(request, handler)
                                                .thenAccept((resp) -> System.out.println(resp
                                                                .body())));

                allOfFuture.thenAccept((Void) -> {
                        System.out.println("finished all");
                });
                System.out.println("before block");
                allOfFuture.join();
                System.out.println("after block");

        }

}