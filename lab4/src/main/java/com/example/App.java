package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

public class App {
        public static void main(String[] args) {

                Faker faker = new Faker();
                Random random = new Random();

                Location[] locs = new Location[15];

                for (int i = 0; i < locs.length; i++) {

                        int ran = random.nextInt(10);

                        switch (ran % 3) {
                                case 0:
                                        locs[i] = new Location(faker.country().name(), Type.FRIENDLY);
                                        break;
                                case 1:
                                        locs[i] = new Location(faker.country().name(), Type.ENEMY);
                                        break;
                                case 2:
                                        locs[i] = new Location(faker.country().name(), Type.NEUTRAL);
                                        break;
                        }

                }

                Map map = new Map();
                for (Location loc : locs) {
                        map.addLocation(loc);
                }

                int count = 0;
                int max = 15;

                while (count < max) {
                        int i = random.nextInt(locs.length);
                        int j = random.nextInt(locs.length);

                        if (i == j) {
                                continue;
                        }

                        double time = random.nextDouble() * 100;
                        int round = (int) (time * 100);

                        if (round % 3 == 1) {
                                round = 0;
                        }
                        time = (double) round / 100;

                        if (time != 0) {
                                map.connectLocations(locs[i], locs[j], time);
                                count++;

                        }
                }

                List<Location> friendly = Arrays.stream(locs).filter(loc -> loc.isFriendly())
                                .collect(Collectors.toList());
                List<Location> enemy = Arrays.stream(locs).filter(loc -> loc.isEnemy()).collect(Collectors.toList());
                List<Location> neutral = Arrays.stream(locs).filter(loc -> loc.isNeutral())
                                .collect(Collectors.toList());

                System.out.println("time to friendly locations:");

                for (Location loc : friendly) {
                        if (loc.equals(locs[0])) {
                                continue;
                        }
                        System.out.println(locs[0].toString() + " to " + loc.toString() + " takes "
                                        + String.valueOf(map.getTimeFromAtoB(locs[0], loc)) + " with a probability of "
                                        + map.getsafeProbability(locs[0], loc) + "%");
                }

                System.out.println("time to enemy locations:");

                for (Location loc : enemy) {
                        System.out.println(locs[0].toString() + " to " + loc.toString() + " takes "
                                        + String.valueOf(map.getTimeFromAtoB(locs[0], loc)) + " with probability "
                                        + map.getsafeProbability(locs[0], loc) + "%");
                }

                System.out.println("time to neutral locations:");

                for (Location loc : neutral) {
                        System.out.println(locs[0].toString() + " to " + loc.toString() + " takes "
                                        + String.valueOf(map.getTimeFromAtoB(locs[0], loc)) + " with probability "
                                        + map.getsafeProbability(locs[0], loc) + "%");
                }

        }

}