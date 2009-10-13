package priorityqueue;

/**
 * class PriorityQueue
 * 
 * requires java.util.ArrayList
 * 
 * PriorityQueue()
 *     Default constructor
 * public void push(Event newEvent)
 *     newEvent - new event to add
 *     Adds the new event to the queue and restructures the priority queue
 * public Event pop()
 *     Returns the top event in the priority queue and removes it from the queue
 * private void reorderDown(int pos) {
 *     pos - position of event to reorder in the priority queue
 *     Reorders the event tree based on priority from the top to the bottom, starting at [pos]
 * private void reorderUp(int pos) {
 *     pos - position of event to reorder in the priority queue
 *     Reorders the event tree based on priority from the bottom to the top, starting at [pos]
 * private void swap(int pos_1, int pos_2) {
 *     pos_1 - position of one event in the queue to swap
 *     pos_2 - position of the other event in the queue to swap
 *     Swaps the events in the queue with positions given by [pos_1] and [pos_2]
 *     
 * @author Sean, edited 10/12/09 by Seth
 *
 */

import java.util.ArrayList;

public class PriorityQueue {
	private ArrayList<Event> queue;
        public boolean isEmpty = false;
	
	public PriorityQueue() {
		queue = new ArrayList<Event>();
	}
	
	public void push(Event newEvent) {
		queue.add(newEvent);
		int newIndex = queue.size() - 1;	// This is the index of the element just added
		reorderUp(newIndex);					// Test new element to make sure it's in the right spot in the queue
	}
	
	public Event pop() throws IndexOutOfBoundsException {
		if (queue.size() == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Event eventToReturn = queue.get(0);
			
			int lastElementIndex = queue.size() - 1;
			queue.set(0, queue.get(lastElementIndex));	// Replace first element with last element
			queue.remove(lastElementIndex);
			reorderDown(0);
                        if (queue.size() == 0)
                            isEmpty = true;
					
			return eventToReturn;
		}
	}
	
	private void reorderDown(int pos) {
		int queueLength = queue.size();
		
		int leftChild = 2 * pos + 1;
		int rightChild = 2 * pos + 2;
		
		if (leftChild >= queueLength && rightChild >= queueLength) { // element has no children
			return;
		} else if (leftChild < queueLength && rightChild >= queueLength) {	// element has left child
			int thisPriority = queue.get(pos).getPriority();
			int leftChildPriority = queue.get(leftChild).getPriority();
			
			if (thisPriority > leftChildPriority) {
				swap(pos, leftChild);
				reorderDown(leftChild);
			}
		} else {
			int thisPriority = queue.get(pos).getPriority();
			int leftChildPriority = queue.get(leftChild).getPriority();
			int rightChildPriority = queue.get(rightChild).getPriority();
			
			if (rightChildPriority < leftChildPriority) {
				if (thisPriority > rightChildPriority) {
					swap(pos, rightChild);
					reorderDown(rightChild);
				}
			} else {
				if (thisPriority > leftChildPriority) {
					swap(pos, leftChild);
					reorderDown(leftChild);
				}
			}
		}
	}
	
	private void reorderUp(int pos) {
		if (pos == 0) {
			return;						// It's in the right spot
		} else {
			int parent = (pos - 1) / 2;
			if (queue.get(pos).getPriority() < queue.get(parent).getPriority()) {
				swap(pos, parent);
				reorderUp(parent);
			}
		}
	}
	
	private void swap(int pos_1, int pos_2) throws RuntimeException {
		if (pos_1 >= queue.size() || pos_2 >= queue.size()) {
			throw new RuntimeException();
		}
		
		Event temp;
		temp = queue.get(pos_1);
		queue.set(pos_1, queue.get(pos_2));
		queue.set(pos_2, temp);
	}
	
	/* //Used for debugging purposes only
	public String dumpArray() {
		String returnStr = "";
		int queueSize = queue.size();
		for (int x = 0; x < queueSize; ++x) {
			returnStr += queue.get(x).getPriority() + ",";
		}
		return returnStr;
	}
	*/
}
