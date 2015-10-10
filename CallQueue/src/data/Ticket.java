/**
 * 
 */
package data;

/**
 * @author Jeremy
 *
 */
public class Ticket implements Comparable<Ticket> {
	
	private int priority;
	private int id;
	
	Ticket (int priority, int id){
		this.priority = priority;
		this.id = id;
		
	}

	@Override
	public int compareTo(Ticket tick) {
		if(this.priority == tick.priority){
			return 0;
		}
		if(this.priority > tick.priority){
			return 1;
		}
		return -1;
	}

}
