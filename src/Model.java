import java.util.Map;
import java.util.TreeMap;

public class Model {
    private Map<Guest, Guest> guests = new TreeMap<>();

    public Map<Guest, Guest> getGuests() {
        return guests;
    }
}
