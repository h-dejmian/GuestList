import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public static int readNumber() {
        String n;
        int number = 0;
        while (true) {
            try {
                n = br.readLine();
                number = Integer.parseInt(n);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                System.out.println("Wrong data, try again");
                continue;
            }
            if(!isNumberInRange(number)) System.out.println("Wrong data, try again");
            else break;
        }
        return number;
    }

    public static boolean isNumberInRange(int number) {
        //Main menu, input validation
        return number <= 8 && number >= 1;
    }


    public static void viewMenu() {
        System.out.println("Guests list");
        System.out.println("*************************");
        System.out.println("1. Add guests");
        System.out.println("2. Show pairs");
        System.out.println("3. Show all guests");
        System.out.println("4. Add list to file");
        System.out.println("5. Add list to file without groups");
        System.out.println("6. Guest list by groups");
        System.out.println("7. Import list from file");
        System.out.println("8. Exit");
        System.out.println("Choose number...");
    }
}
