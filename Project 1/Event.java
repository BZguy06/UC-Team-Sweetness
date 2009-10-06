package priorityqueue;

public class Event {
	private int p_priority;
	private String p_info;
	
	Event() {
		p_priority = 0;
		p_info = "";
	}
	
	void setPriority(int newPriority) throws RuntimeException {
		if(newPriority < 0 || newPriority > 200) {
			throw new RuntimeException("Priority is not between 0 and 200");
		}
		
		p_priority = newPriority;
	}
	
	int getPriority() {
		return p_priority;
	}
	
	void setInfo(String newInfo) throws RuntimeException {
		if (newInfo.length() > 5) {
			throw new RuntimeException("String must been less than 5 characters long");
		}
		
		p_info = newInfo;
	}
	
	String getInfo() {
		return p_info;
	}
	
}
