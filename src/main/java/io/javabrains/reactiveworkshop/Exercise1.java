package io.javabrains.reactiveworkshop;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercise1 {

  public static void main(String[] args) {

    // Use StreamSources.intNumbersStream() and StreamSources.userStream()

    // Print all numbers in the intNumbersStream stream
    System.out.println("// Print all numbers in the intNumbersStream stream");
    StreamSources.intNumbersStream()
        .forEachOrdered(System.out::println);

    // Print numbers from intNumbersStream that are less than 5
    System.out.println("// Print numbers from intNumbersStream that are less than 5");
    Predicate<Integer> isBelowFive = number -> number < 5;
    StreamSources.intNumbersStream()
        .filter(isBelowFive)
        .forEachOrdered(System.out::println);

    // Print the second and third numbers in intNumbersStream that's greater than 5
    System.out.println("// Print the second and third numbers in intNumbersStream that's greater than 5");
    Predicate<Integer> isAboveFive = number -> number > 5;
    StreamSources.intNumbersStream()
        .filter(isAboveFive)
        .skip(1)
        .limit(2)
        .forEachOrdered(System.out::println);

    //  Print the first number in intNumbersStream that's greater than 5.
    //  If nothing is found, print -1
    System.out.println("//  Print the first number in intNumbersStream that's greater than 5.");
    var firstNumberGreaterThanFive = StreamSources.intNumbersStream()
        .filter(isAboveFive)
        .findFirst()
        .orElse(-1);
    System.out.println(firstNumberGreaterThanFive);

    // Print first names of all users in userStream
    System.out.println("// Print first names of all users in userStream");
    StreamSources.userStream()
        .map(User::getFirstName)
        .forEach(System.out::println);

    // Print first names in userStream for users that have IDs from number stream
    System.out.println("// Print first names in userStream for users that have IDs from number stream");
    var usersById = StreamSources.userStream()
        .collect(Collectors.groupingBy(User::getId));
    StreamSources.intNumbersStream()
        .map(usersById::get)
        .filter(Objects::nonNull)
        .flatMap(List::stream)
        .map(User::getFirstName)
        .forEach(System.out::println);

  }

}
