import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();
        list.add(scanner.nextLine());

        long count = list.stream()
                .flatMap(line -> Stream.of(line.split("\\s+")))
                .count();
        System.out.println("В тексте " + count + " слов\n" + "ТОП 10:" );

        list.stream()
                .flatMap(line -> Stream.of(line.split("\\s+")))
                .map(word -> word.replaceAll("[^A-Za-zА]+", ""))
                .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                .entrySet().stream()
                .sorted((e1, e2) -> {
                    int val = e1.getValue().compareTo(e2.getValue()) * -1;
                    if (val == 0) {
                        val = e1.getKey().compareTo(e2.getKey());
                        if (e1.getKey().charAt(0) <= 'z'
                                && e2.getKey().charAt(0) > 'z'
                                || e1.getKey().charAt(0) > 'z'
                                && e2.getKey().charAt(0) <= 'z') {
                            val *= -1;
                        }
                    }
                    return val;
                })
                .limit(10)
                .forEach(e -> System.out.println(e.getValue() + " - " + e.getKey()));
    }
}