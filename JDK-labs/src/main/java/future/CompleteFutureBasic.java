package future;

import io.netty.util.concurrent.CompleteFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteFutureBasic {

    static class Client extends Thread{

        CompletableFuture<Integer> f;

        Client(String threadName, CompletableFuture<Integer> f){
            super(threadName);
            this.f = f;
        }

        @Override
        public void run(){
            try {
                System.out.println(this.getName() +":" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        new Client("client1",completableFuture).start();
        new Client("client2",completableFuture).start();
        System.out.println("waiting");
        completableFuture.complete(100);
    }

}
