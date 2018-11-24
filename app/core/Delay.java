package core;

/**
 * Created by peterszatmary on 6.10.2015.
 */
public class Delay {

    public static void get(final long ms) {
        try {Thread.sleep(ms);} catch (InterruptedException e) {}
    }

    public static void mockCrashAndDelay(final boolean exception, final long delay) {
        Delay.get(delay);
        if (exception) {
            throw new RuntimeException("Baaaad times comes for you application. Dark side of the force is stronger !");
        }
    }
}