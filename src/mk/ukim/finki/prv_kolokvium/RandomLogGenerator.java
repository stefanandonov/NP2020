package mk.ukim.finki.prv_kolokvium;

import java.awt.*;
import java.util.Random;

public class RandomLogGenerator {

    public static void main(String[] args) {
        Random rdm = new Random();

        int n = 10;
        System.out.println(n);

        for (int i=0;i<n;i++) {
            String type = rdm.nextBoolean() ? "INFO" : "ERROR";
            System.out.print(type+" ");
            boolean shouldItBeLong = rdm.nextBoolean();
            System.out.print(shouldItBeLong ?
                    "Some_looooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-"+rdm.nextInt(100) + " "
                    :"Some_short_message-"+rdm.nextInt(100) + " ");
            System.out.print(Math.abs(rdm.nextLong()));
            System.out.println();
        }
    }
}
