package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Venue {
    
    private String name;
    private ArrayList<Room> roomList;

    /** 
	 * @param name, name of the venue. 
	*/
    public Venue(String name) {
        this.name = name;
        this.roomList = new ArrayList<Room>();
    }

    /**
    * @return the name of the venue. 
    */
	public String getName() {
		return name;
    }

	/**
	 * @return the roomList.
	 */
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	
	/**
	 * @return the number of rooms.
	 */
	public int getRoomNumber() {
		return roomList.size();
    }
    
    /**
	 * @param size  The size of the new room.
	 * @param roomName The id of the new room.
	 */
	public void addRoom(String roomName, String size) {
		Room newRoom = new Room(size, roomName);
		boolean can = true;
		for(Room v: roomList) {
			if(v.equals(newRoom)) {
				can = false;
			}
		}
		if(can && newRoom.checkExist()) {
			roomList.add(newRoom);
		}
	}

	public int getExistRoomNumber(String RoomSize, LocalDate startDate, LocalDate endDate) {
		int n = 0;
		for(Room temp : roomList) {
			if(RoomSize.equals(temp.getSize())) {

				n++;
			}
		}
		return n;
	}

	public void cancel(String id) {
        for(Room r : roomList) {
			r.cancel(id);
        }
    }

	public void findID(String id, LocalDate start, LocalDate end, String size, boolean find) {
		for(Room r : roomList) {
			r.findID(id, start, end, size, find);
			if(find) size = r.getSize();
			break;
        }
	}

	public ArrayList<Room> findRQ(LocalDate start, LocalDate end, String size, int n) {
		ArrayList<Room> result = new ArrayList<Room>();
		int t = n;
		for(Room r : roomList) {
			if(size.equals(r.getSize()) && r.checkTime(start, end)) {
				result.add(r);
				t--;
				if(t == 0) {
					break;
				}
			}
		}
		return result;
	}
	
	public void register(ArrayList<Room> rqSmall, ArrayList<Room> rqMedium, ArrayList<Room> rqLarge, String id, LocalDate start, LocalDate end) {
		for(Room r : rqSmall) {
			for(Room a : this.roomList) {
				if(r.equals(a)){
					a.register(id, start, end);
				}
			}
		}
		for(Room r : rqMedium) {
			for(Room a : this.roomList) {
				if(r.equals(a)){
					a.register(id, start, end);
				}
			}
		}
		for(Room r : rqLarge) {
			for(Room a : this.roomList) {
				if(r.equals(a)){
					a.register(id, start, end);
				}
			}
		}
	}

	public void list(JSONArray list) {
		JSONObject result = new JSONObject();
		for(Room r : roomList) {
			result.put("room", r.getName());	
			result.put("reservations", r.list());   
			list.put(result);
		}
	}

	public JSONArray list() {
		return null;
	}

}


