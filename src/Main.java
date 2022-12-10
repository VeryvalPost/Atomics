import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        AtomicInteger counter3 = new AtomicInteger();
        AtomicInteger counter4 = new AtomicInteger();
        AtomicInteger counter5 = new AtomicInteger();

        new Thread(() -> {
            for (String nick : texts) {

                if (wordBeauty(3, nick)) {
                    counter3.getAndIncrement();
                }
            }
            System.out.println("Красивых слов с длиной 3: " + counter3.get() + " шт");
        }).start();


        new Thread(() -> {
            for (String nick : texts) {
                if (wordBeauty(4, nick)) {
                    counter4.getAndIncrement();
                }
            }
            System.out.println("Красивых слов с длиной 4: " + counter4.get() + " шт");
        }).start();

        new Thread(() -> {
            for (String nick : texts) {
                if (wordBeauty(5, nick)) {
                    counter5.getAndIncrement();
                }
            }
            System.out.println("Красивых слов с длиной 5: " + counter5.get() + " шт");
        }).start();

        // for (String text: texts){
        //     System.out.println(text);
        // }
    }

    public static boolean wordBeauty(int qtyOfLetters, String nick) {

        if (nick.length() == qtyOfLetters) {
            char[] letters = nick.toCharArray();
            for (int i = 0; i <= qtyOfLetters - 1; i++) {
            }

            //проверка на "красоту" по одной палиндрому
            if ((qtyOfLetters == 3) && (letters[0] == letters[2])) return true;
            if ((qtyOfLetters == 4) && (letters[0] == letters[3]) && (letters[1] == letters[2])) return true;
            if ((qtyOfLetters == 5) && (letters[0] == letters[4]) && (letters[1] == letters[3])) return true;


            // проверка на "красоту" по одной букве
            if ((qtyOfLetters == 3) && (letters[0] == letters[1]) && (letters[1] == letters[2])) return true;
            if ((qtyOfLetters == 4) && (letters[0] == letters[1]) && (letters[2] == letters[3]) && (letters[1] == letters[2]))
                return true;
            if ((qtyOfLetters == 5) && (letters[0] == letters[1]) && (letters[2] == letters[3]) && (letters[1] == letters[2]) && (letters[3] == letters[4]))
                return true;

            // проверка на "красоту" по возрастанию
            if (isBeauty(letters)) return true;


        }
        return false;
    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }


    public static boolean isBeauty(char[] letters) {
        for (int i = 1; i < letters.length; i++) {
            if (letters[i] < letters[i - 1])
                return false;
        }
        return true;
    }

}

