package core;

/**
 * Created by nue on 6.10.2015.
 */
public class Delay {

    public static void get(long ms) {
        try {Thread.sleep(ms);} catch (InterruptedException e) {}
    }


    public static void mockCrashAndDelay(boolean exception, long delay) {
        Delay.get(delay);
        if (exception) {
            throw new RuntimeException("Baaaad times comes for you application. Dark side of the force is stronger !");
        }
    }


}
