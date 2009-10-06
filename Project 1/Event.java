package priorityqueue;

/**
 * class Event
 * 
 * void setPriority(int newPriority)
 *     newPriority - integer from 0 to 200, anything outside bounds throws error
 *     Set the priority of the event
 * int getPriority()
 *     Returns the priority of the event
 * void setInfo(String newInfo)
 *     newInfo - New info string, over length of 5 throws error
 *     Set the event info
 * String getInfo()
 *     Returns the info string for the event
 *     
 * @author Sean
 *
 */

public class Event {
	private int p_priority;
	private String p_info;
	
	Event() {
		p_priority = 0;
		p_info = "";
	}
	
	void setPriority(int newPriority) throws RuntimeException {
		if(newPriority < 0 || newPriority > 200) {
			throw new RuntimeException();
		}
		
		p_priority = newPriority;
	}
	
	int getPriority() {
		return p_priority;
	}
	
	void setInfo(String newInfo) throws RuntimeException {
		if (newInfo.length() > 5) {
			throw new RuntimeException();
		}
		
		p_info = newInfo;
	}
	
	String getInfo() {
		return p_info;
	}
	
	String toString() {
		return p_info+" ";
	}
	
}
