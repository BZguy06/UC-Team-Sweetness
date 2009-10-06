package priorityqueue;

/**
 * class Event
 * 
 * Event()
 *     Default constructor
 * Event(int newPriority, String newInfo)
 *     Initializes variables 
 * public void setPriority(int newPriority)
 *     newPriority - integer from 0 to 200, anything outside bounds throws error
 *     Set the priority of the event
 * public int getPriority()
 *     Returns the priority of the event
 * public void setInfo(String newInfo)
 *     newInfo - New info string, over length of 5 throws error
 *     Set the event info
 * public String getInfo()
 *     Returns the info string for the event
 *     
 * @author Sean
 *
 */

public class Event {
	private int priority;
	private String info;
	
	public Event() {
		priority = 0;
		info = "";
	}
	
	public Event(int newPriority, String newInfo) {
		setPriority(newPriority);
		setInfo(newInfo);
	}
	
	public void setPriority(int newPriority) throws RuntimeException {
		if(newPriority < 0 || newPriority > 200) {
			throw new RuntimeException();
		}
		
		priority = newPriority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setInfo(String newInfo) throws RuntimeException {
		if (newInfo.length() > 5) {
			throw new RuntimeException();
		}
		
		info = newInfo;
	}
	
	public String getInfo() {
		return info;
	}
	
}
