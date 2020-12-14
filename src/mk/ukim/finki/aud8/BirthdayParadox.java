package mk.ukim.finki.aud8;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BirthdayParadox {

    private static Random random = new Random();

    public static void main(String[] args) {
        for (int i=2;i<=50;i++) {
            double probability = calculateProbability(i);
            System.out.println(String.format("For %d people, " +
                    "the probability of two birthdays is about %f",
                    i,
                    probability));
        }
    }

    private static double calculateProbability(int roomSize) {
        int success = 0;
        for (int i=0;i<10000;i++) {
            if (trial(roomSize))
                ++success;
        }
        return success/10000.0;
    }

    private static boolean trial(int roomSize) {
        Set<Integer> birthdays = new HashSet<>();
        for (int i=0;i<roomSize;i++) {
            int newBirthday = random.nextInt(365)+1;
            if (birthdays.contains(newBirthday))
                return true;
            birthdays.add(newBirthday);
        }
        return false;
    }
}
