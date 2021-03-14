/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    private ArrayList<Venue> venueList;

    public VenueHireSystem() {
        // Auto-generated constructor stub
        this.venueList = new ArrayList<Venue>();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        // Implement other commands
        case "change":
            String id_change = json.getString("id");
            LocalDate start_change = LocalDate.parse(json.getString("start"));
            LocalDate end_change = LocalDate.parse(json.getString("end"));
            int small_change = json.getInt("small");
            int medium_change = json.getInt("medium");
            int large_change = json.getInt("large");

            JSONObject result_change = change(id_change, start_change, end_change, small_change, medium_change, large_change);

            System.out.println(result_change.toString(2));
            break;

        case "cancel":
            String id_cancel = json.getString("id");
            cancel(id_cancel);
            break;

        case "list":
            String id_list = json.getString("venue");
            System.out.println(list(id_list).toString(2));
            break;
        }
    }

    private void addRoom(String venue, String room, String size) {
        // Process the room command
        boolean exist = false;
        for(Venue v : this.venueList) {
            if(v.getName().equals(venue)) {
                exist = true;
                v.addRoom(room, size);
            }
        }
        if(!exist) {
            Venue newVenue = new Venue(venue);
            newVenue.addRoom(room, size);
            this.venueList.add(newVenue);
        }
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();
        ArrayList<Room> rqSmall = new ArrayList<Room>();
        ArrayList<Room> rqMedium = new ArrayList<Room>();
        ArrayList<Room> rqLarge = new ArrayList<Room>();
        for(Venue v : venueList) {
            rqSmall = v.findRQ(start, end, "small", small);
            rqMedium = v.findRQ(start, end, "medium", medium);
            rqLarge = v.findRQ(start, end, "large", large);
            if(rqSmall.size() >= small && rqMedium.size() >= medium && rqLarge.size() >= large) {
                v.register(rqSmall, rqMedium, rqLarge, id, start, end);
                result.put("venue", v.getName());
                JSONArray roomJS = new JSONArray();
                for(Room r : rqSmall) {
                    roomJS.put(r.getName());
                }
                for(Room r : rqMedium) {
                    roomJS.put(r.getName());
                }
                for(Room r : rqLarge) {
                    roomJS.put(r.getName());
                }
                result.put("rooms", roomJS); 
                result.put("status", "success");
                break;
            }
        }
        if(rqSmall.size() < small || rqMedium.size() < medium || rqLarge.size() < large) {
            result.put("status", "rejected");
        }
        return result;
    }

    public JSONObject change(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();
        LocalDate oldStart = null;
        LocalDate oldEnd = null;
        String oldsize = "null";
        boolean find = false;
        int oldsmall = 0, oldmedium = 0, oldlarge = 0;
        for(Venue v : venueList) {
            v.findID(id, oldStart, oldEnd, oldsize, find);
            if(find) {
                break;
            }
        }
        if(find) {
            cancel(id);
            result = request(id, start, end, small, medium, large);
            if(result.length() <= 2) {
                if(oldsize.equals("small")) oldsmall = 1;
                if(oldsize.equals("medium")) oldmedium = 1;
                if(oldsize.equals("large")) oldlarge = 1;
                result = request(id, oldStart, oldEnd, oldsmall, oldmedium, oldlarge);
                return null;
            }
        }
        else {
            result.put("status", "rejected");
        }
        return result;
    }

    public void cancel(String id) {
        for(Venue v : venueList) {
            v.cancel(id);
        }
    }

    public JSONArray list(String str) {
        JSONArray Result = new JSONArray();
        for(Venue v : venueList) {
            if(v.getName().equals(str)) {
                v.list(Result);
            }
        }
        return Result;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
