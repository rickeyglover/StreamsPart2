package com.pluralsight.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    static Scanner myScanner = new Scanner(System.in);
    static List<Person> people = new ArrayList<>();
    static List<Person> matches = new ArrayList<>();

    public static void main(String[] args) {
        matches.clear();
        people.add(new Person("John", "Cena", 46));
        people.add(new Person("Dave", "Bautista", 54));
        people.add(new Person("Rey", "Mysterio", 48));
        people.add(new Person("Randy", "Orton", 43));
        people.add(new Person("Brock", "Lesnar", 46));
        people.add(new Person("Steve", "Austin", 58));
        people.add(new Person("Dwayne", "Johnson", 51));
        people.add(new Person("Shawn", "Michaels", 58));
        people.add(new Person("Paul", "Levesque", 54));
        people.add(new Person("Adam", "Copeland", 50));

        while (true) {
            System.out.println("Name & Age Searcher");
            System.out.println("\t(S) - Search a name");
            System.out.println("\t(L) - Show full list of people");
            System.out.println("\t(A) - Average age of all people");
            System.out.println("\t(O) - Oldest Person");
            System.out.println("\t(Y) - Youngest Person");
            System.out.println("\t(X) - Exit");

            Scanner userInput = new Scanner(System.in);

            switch (userInput.nextLine().toUpperCase()) {
                case "S":
                    userSearch();
                    break;

                case "L":
                    showList();
                    break;

                case "A":
                    averageAge();
                    break;

                case "O":
                    oldestPerson();
                    break;

                case "Y":
                    youngestPerson();
                    break;

                case "X":
                    System.exit(0);

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public static void userSearch() {
        while (true) {
            System.out.print("Enter a name to search (or type 'X' to quit): ");
            String search = myScanner.nextLine();

            if (search.equalsIgnoreCase("X")) {
                break;
            }

            List<Person> filteredMatches = people.stream()
                    .filter(person -> person.getFirstName().equalsIgnoreCase(search) || person.getLastName().equalsIgnoreCase(search))
                    .collect(Collectors.toList());

            if (filteredMatches.isEmpty()) {
                System.out.println("No matches found");
            } else {
                System.out.println("Matches found: ");
                for (Person match : filteredMatches) {
                    System.out.println(match.getFirstName() + " " + match.getLastName() + ", Age: " + match.getAge());
                }
            }
        }
    }


    public static void showList() {
        System.out.println("List of Names and Ages:");
        for (Person person : people) {
            System.out.println(person.getFirstName() + " " + person.getLastName() + ", Age: " + person.getAge());
        }
    }

    public static void averageAge() {
        int totalAge = 0;
        for (Person person : people) {
            totalAge += person.getAge();
        }

        if (people.size() > 0) {
            double average = (double) totalAge / people.size();
            System.out.println("Average age of all people: " + average);
        } else {
            System.out.println("No people in the list.");
        }
    }

    public static void oldestPerson() {
        if (people.isEmpty()) {
            System.out.println("No people in the list.");
            return;
        }

        Person oldest = people.get(0);

        for (Person person : people) {
            if (person.getAge() > oldest.getAge()) {
                oldest = person;
            }
        }

        System.out.println("Oldest person:");
        System.out.println(oldest.getFirstName() + " " + oldest.getLastName() + ", Age: " + oldest.getAge());
    }

    public static void youngestPerson() {
        if (people.isEmpty()) {
            System.out.println("No people in the list.");
            return;
        }

        Person youngest = people.get(0);

        for (Person person : people) {
            if (person.getAge() < youngest.getAge()) {
                youngest = person;
            }
        }

        System.out.println("Youngest person:");
        System.out.println(youngest.getFirstName() + " " + youngest.getLastName() + ", Age: " + youngest.getAge());
    }
}

