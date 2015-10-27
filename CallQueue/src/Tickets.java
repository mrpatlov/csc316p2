public class Tickets implements Comparable<Tickets>{

	int id;
	static int nextID = 1;
	int priority;
	int count;
	PriorityTree.Node location;
	
	/**
	 * Constructor for tickets will make new ticket with a unquie id
	 * with id's starting and 1 and incrementing for every new ticket
	 * @param priority priority for new ticket
	 */
	public Tickets (int priority){
		this.id = nextID;
		nextID++;
		this.priority = priority;
	}
	
	/**
	 * gets the Priority for ticket
	 * @return this tickets priority
	 */
	public int getPriority(){
		return priority;
	}
	
	/**
	 * Sets the node that holds this ticket in the Priority Tree
	 * @param location Node holding this ticket
	 */
	public void setNode(PriorityTree.Node location){
		this.location = location;
	}
	
	/**
	 * returns the node in the priority tree holding this ticket
	 * @return node in the priority tree holding this ticket
	 */
	public PriorityTree.Node getNode(){
		return this.location;
	}
	
	/**
	 * this tickets ID
	 * @return this tickets ID
	 */
	public int getID(){
		return this.id;
	}


	@Override
	/**
	 * Comparator for Tickets based on ID for ID Tree sorting
	 */
	public int compareTo(Tickets other) {
		if (other.id > this.id){
			return 1;
		}
		if(other.id < this.id){
			return -1;
		}
		return 0;
	}

}
