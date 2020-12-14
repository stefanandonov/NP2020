package mk.ukim.finki.prv_kolokvium;

import java.util.Random;
import java.util.UUID;

public class RandomGenerator {
    public static void main(String[] args) {
        Random random = new Random();

        int n = 15;
        for (int i=0;i<n;i++) {
            int shapes = random.nextInt(15)+5;

            System.out.print(UUID.randomUUID().toString().substring(0,8) + " ");
            for (int j=0;j<shapes;j++) {
                String type = random.nextInt(2)==0 ? "C" : "S";
                System.out.print(type + " " + String.valueOf(random.nextInt(21)+10) + " ");
            }
            System.out.println();

        }
    }
}
