package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Room {

    private String name;
    private String size;
    private Boolean free;

    private ArrayList<Booking> bookingList;
    
    /** 
	 * @param name, name of the room. 
     * @param size, size of the room. 
	*/
    public Room(String name, String size) {
        this.name = name;
        this.size = size;
        this.free = true;
        this.bookingList = new ArrayList<Booking>();
    }
    
    /**
    * @return the name of the room. 
    */
	public String getName() {
		return name;
    }

    /**
    * @return the size of the room. 
    */
	public String getSize() {
		return size;
    }

    /**
    * @return whether the room is free.
    */
	public Boolean checkExist() {
		return free;
    }

    public void cancel(String id) {
        boolean free = false;
        for(Booking b : bookingList) {
            if(b.getId().equals(id)) {
                bookingList.remove(b);
                free = true;
            }
        }
        if(free) {
            this.free = true;
        }
    }

	public void findID(String id, LocalDate start, LocalDate end, String size, boolean find) {
        for(Booking b : bookingList) {
            if(b.getId().equals(id)) {
                start = b.getStartDate();
                end = b.getEndDate();
                find = true;
                break;
            }
        }
	}

	public boolean checkTime(LocalDate start, LocalDate end) {
        if(bookingList.size() == 0) {
            return true;
        }
        else {
            for(Booking b : bookingList) {
                if(b.getStartDate().isAfter(end) || b.getEndDate().isBefore(start)) {
                    return true;
                }
            }
        }
		return false;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		else if (obj.getClass() != this.getClass()) return false;
		else if (!(((Room) obj).getName().equals(this.getName()))) return false;
		return true;
	}

	public void register(String id, LocalDate start, LocalDate end) {
        this.free = false;
        Booking newbook = new Booking(id, start, end);
        bookingList.add(newbook);
    }
    
    public JSONArray list() {
		JSONArray result = new JSONArray();
        JSONObject temp = new JSONObject();
		for(Booking b : bookingList) {
			temp.put("id", b.getId());
			temp.put("start", b.getStartDate());
			temp.put("end", b.getEndDate());
			result.put(temp);
		}
        return result;
	}

}
