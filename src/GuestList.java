public class GuestList {
    private final Controller controller = new Controller();

    public void mainMenu(int number) {

        switch(number) {
            case 1:{
               controller.addGuest();
               break;
            }

            case 2: {
                controller.showPairs();
                break;
            }

            case 3: {
                controller.showAllGuests();
                break;
            }

            case 4: {
                controller.addGuestListToFile();
                break;
            }

            case 5: {
                controller.addListToFileWithoutGroups();
                break;
            }

            case 6:{
                controller.showGuestsByGroups();
                break;
            }

            case 7:{
                controller.importListFromFile("I:/programowanie/IdeaProjects/Guests/src/guestlist.txt");
                break;
            }

            case 8:{
                System.exit(0);
                break;
            }
        }
    }
}
