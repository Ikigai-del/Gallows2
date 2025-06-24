import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Gallows {
    private static final String[] WORDS = {
            "кошка", "собака", "дом", "окно", "компьютер", "яблоко", "машина", "дерево", "парк", "река"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру 'Виселица'!");

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Начать игру или завершить?");
            System.out.println("1 — да  |  2 — нет");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                playGame(scanner);
            } else if (choice.equals("2")) {
                System.out.println("Игра завершена.");
                break;
            } else {
                System.out.println("Неверная команда.");
            }

            System.out.println("Хотите сыграть еще раз? (да/нет)");
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("да");
        }

        System.out.println("Спасибо за игру! До новых встреч.");
        scanner.close();
    }

    public static void playGame(Scanner scanner) {
        String word = randomWord();
        char[] guessedWord = new char[word.length()];
        Set<Character> guessedLetters = new HashSet<>();
        int attempts = 6;

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        System.out.println("Игра началась! Угадайте слово.");
        System.out.println("Количество попыток: " + attempts);
        System.out.println("Слово: " + String.valueOf(guessedWord));

        while (attempts > 0) {
            System.out.print("Введите букву: ");
            char guess = scanner.next().toLowerCase().charAt(0);
            scanner.nextLine(); // Очистка буфера

            if (guessedLetters.contains(guess)) {
                System.out.println("Вы уже вводили эту букву!");
                continue;
            }

            guessedLetters.add(guess);

            boolean isCorrect = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    isCorrect = true;
                }
            }

            if (!isCorrect) {
                attempts--;
                System.out.println("Неверно! Осталось попыток: " + attempts);
                printGallows(6 - attempts);
            } else {
                System.out.println("Верно! Текущее слово: " + String.valueOf(guessedWord));
            }

            if (String.valueOf(guessedWord).equals(word)) {
                System.out.println("Поздравляю! Вы угадали слово: " + word);
                return;
            }
        }

        printGallows(6);
        System.out.println("Вы проиграли! Загаданное слово было: " + word);
    }

    public static String randomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }

    public static void printGallows(int stage) {
        String[] gallows = {
                """
               -----
               |   |
                   |
                   |
                   |
                   |
            =========
            """,
                """
               -----
               |   |
               O   |
                   |
                   |
                   |
            =========
            """,
                """
               -----
               |   |
               O   |
               |   |
                   |
                   |
            =========
            """,
                """
               -----
               |   |
               O   |
              /|   |
                   |
                   |
            =========
            """,
                """
               -----
               |   |
               O   |
              /|\\  |
                   |
                   |
            =========
            """,
                """
               -----
               |   |
               O   |
              /|\\  |
              /    |
                   |
            =========
            """,
                """
               -----
               |   |
               O   |
              /|\\  |
              / \\  |
                   |
            =========
            """
        };

        System.out.println(gallows[stage]);
    }
}

