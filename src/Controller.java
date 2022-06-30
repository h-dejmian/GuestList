import java.io.*;
import java.util.Map;

public class Controller {
    private final Model model = new Model();

    public void addGuest(){
        System.out.println("Guest's name, surname and group number:");
        String guestStr;
        do {guestStr = ConsoleHelper.readLine();} while (!isGuestInfoValid(guestStr, 1));

        Guest guest1 = createGuestFromString(guestStr);

        System.out.println("Partner's name, surname and group number or write 'N' if N/A:");

        String guest2Str;
        do {guest2Str = ConsoleHelper.readLine();} while (!isGuestInfoValid(guest2Str, 2));

        if(guest2Str.equalsIgnoreCase("N")) {
            model.getGuests().put(guest1, null);
        }
        else {
            Guest guest2 = createGuestFromString(guest2Str);
            model.getGuests().put(guest1, guest2);
        }
    }

    public void showPairs() {
        if(isListEmpty()) {
            System.out.println("List is empty!");
            System.out.println();
            return;
        }

        int pairNumber = 1;
        System.out.println();

        for(Map.Entry<Guest, Guest> entry : model.getGuests().entrySet()) {
            String value;
            if(entry.getValue()==null) value = "N/A";
            else value = entry.getValue().toString();

            System.out.println(pairNumber++ + ". " + entry.getKey() + ", " + value);
        }
        System.out.println();
    }

    public void showAllGuests() {
        if(isListEmpty()) {
            System.out.println("List is empty!");
            System.out.println();
            return;
        }
        int num = 0;

        System.out.println();

        for(Map.Entry<Guest, Guest> entry : model.getGuests().entrySet()) {
            System.out.println(++num + ". " + entry.getKey());
            if(entry.getValue() != null) System.out.println(++num + ". " + entry.getValue());
        }
        System.out.println();
        System.out.println("Number of all guests: " + num);
    }

    public void showGuestsByGroups() {
        int numberInGroups = 0;
        int allGuests = 0;

        System.out.println();

        for(int i=0; i < Group.values().length; i++) {

            System.out.println(Group.values()[i] + ":");

            for (Map.Entry<Guest, Guest> entry : model.getGuests().entrySet()) {
                if (entry.getKey().getGroup().ordinal() == i) {
                    System.out.println(++numberInGroups + ". " + entry.getKey()); allGuests++;
                    if (entry.getValue() != null) {System.out.println(++numberInGroups + ". " + entry.getValue()); allGuests++;}
                }
            }
            System.out.println();
            numberInGroups = 0;
        }
        System.out.println("Number of all guests: " + allGuests);
    }

    public boolean isListEmpty(){
        return model.getGuests().isEmpty();
    }

    public Guest createGuestFromString(String guestInfo) {
        String[] g = guestInfo.split(" ");
        Group group = Group.values()[Integer.parseInt(g[2])];
        return new Guest(g[0], g[1], group);
    }

    public boolean isGuestInfoValid(String guestinfo, int guestNum) {

        if(guestinfo.equalsIgnoreCase("n") && guestNum == 2) return true;

        String[] info = guestinfo.split(" ");
        if(info.length != 3) {
            System.out.println("Wrong input try again");
            return false;
        }

        try {
            int i = Integer.parseInt(info[2]);
            if(i > 3 || i < 0 ) {
                System.out.println("Wrong input try again");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input try again");
            return false;
        }

        return true;
    }

    public void addGuestListToFile() {

        try {
            File file = new File("I:/programowanie/IdeaProjects/Guests/src/guestlist.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            int pairNumber = 1;

            for(Map.Entry<Guest, Guest> entry : model.getGuests().entrySet()) {
                String strValue;
                String valueGroupOrdinal = "-";
                Guest key = entry.getKey();
                Guest value = entry.getValue();

                if(value==null) strValue = "N/A";
                else {
                    strValue = value.toString();
                    valueGroupOrdinal = String.valueOf(value.getGroup().ordinal());
                }

                bw.write(pairNumber++ + ". "
                        + "(" + key.getGroup().ordinal() + ")"
                        + key
                        + ", " + "(" + valueGroupOrdinal + ")" + strValue + "\n");
            }

            bw.close();
            System.out.println("Successfully added to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListToFileWithoutGroups() {

        try {
            File file = new File("I:/programowanie/IdeaProjects/Guests/src/guestlist.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            int pairNumber = 1;

            for(Map.Entry<Guest, Guest> entry : model.getGuests().entrySet()) {
                String strValue;
                Guest key = entry.getKey();
                Guest value = entry.getValue();

                if(value==null) strValue = "N/A";
                else {
                    strValue = value.toString();
                }

                bw.write(pairNumber++ + ". " + key + ", " + strValue + "\n");
            }

            bw.close();
            System.out.println("Successfully added to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importListFromFile(String path) {
        File file = new File(path);

        try {
            BufferedReader br =  new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();
            br = new BufferedReader(new FileReader(file));
            if(line.contains("(") && line.contains(")")) importListWithGroups(br);
            else importListWithoutGroups(br);
            br.close();

            System.out.println("List imported successfully!");
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importListWithoutGroups(BufferedReader br) throws IOException {
        String line;
        while((line = br.readLine()) != null) {
            String[] values = line.split(" ");
            Guest guest1 = new Guest(values[1], values[2].substring(0, values[2].length()-1), Group.values()[3]);

            if(values[3].equals("N/A")) {
                model.getGuests().put(guest1, null);
            }
            else {
                Guest guest2 = new Guest(values[3], values[4], Group.values()[3]);
                model.getGuests().put(guest1, guest2);
            }
        }
    }

    public void importListWithGroups(BufferedReader br) throws IOException {
        String line;
        while((line = br.readLine()) != null) {
            String[] values = line.split(" ");
            int group1 = Integer.parseInt(values[1].substring(1,2));
            Guest guest1 = new Guest(values[1].substring(3), values[2].substring(0, values[2].length()-1), Group.values()[group1]);

            if(values[3].equals("N/A")) {
                model.getGuests().put(guest1, null);
            }
            else {
                int group2 = Integer.parseInt(values[3].substring(1,2));
                Guest guest2 = new Guest(values[3].substring(3), values[4], Group.values()[group2]);
                model.getGuests().put(guest1, guest2);
            }
        }
    }
}
