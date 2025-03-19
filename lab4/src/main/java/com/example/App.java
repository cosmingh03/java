package com.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class App {
        public static void main(String[] args) {

                Location[] locs = new Location[6];
                locs[0] = new Location("P0", Type.FRIENDLY);
                locs[1] = new Location("P1", Type.ENEMY);
                locs[2] = new Location("P2", Type.FRIENDLY);
                locs[3] = new Location("P3", Type.ENEMY);
                locs[4] = new Location("P4", Type.NEUTRAL);
                locs[5] = new Location("P5", Type.FRIENDLY);

                Set<Location> friendlyLocs = Arrays.stream(locs)
                                .filter(location -> location.getType() == Type.FRIENDLY)
                                .collect(Collectors.toCollection(TreeSet::new));

                System.out.println("friendly locations:");

                friendlyLocs.forEach(System.out::println);

                List<Location> enemyLocs = Arrays.stream(locs)
                                .filter(location -> location.getType() == Type.ENEMY)
                                .collect(Collectors.toCollection(LinkedList::new));

                System.out.println("enemy locations:");

                enemyLocs.stream()
                                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                                .forEach(System.out::println);

        }

}