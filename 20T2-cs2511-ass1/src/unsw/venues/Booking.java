package unsw.venues;

import java.time.LocalDate;

public class Booking {

    private String id;
	private LocalDate startDate;
    private LocalDate endDate;
 

    public Booking(String id, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.startDate = startDate;
        this.endDate = endDate;

    }
    
    /** 
	 * @return id.
	*/
	public String getId() {
		return id;
	}
	
	/** 
	 * @return startDate.
	*/
	public LocalDate getStartDate() {
		return startDate;
    }
    
	/** 
	 * @return endDate.
	*/
	public LocalDate getEndDate() {
		return endDate;
    }
    
}
