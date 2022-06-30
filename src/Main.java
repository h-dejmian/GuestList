public class Main {

    public static void main(String[] args) {
        GuestList guestList = new GuestList();

         while(true) {
             ConsoleHelper.viewMenu();
             guestList.mainMenu(ConsoleHelper.readNumber());
         }
    }
}
