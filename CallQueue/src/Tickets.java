import java.io.IOException;

import javax.activation.CommandObject;
import javax.activation.DataHandler;

public class Tickets implements Comparable<Tickets>{

	int id;
	static int nextID = 1;
	int priority;
	int count;
	Node location;
	
	public Tickets (int priority){
		this.id = nextID;
		nextID++;
		this.priority = priority;
	}
	
	
	public int getPriority(){
		return priority;
	}
	
	public void setNode(Node location){
		this.location = location;
	}
	
	public Node getNode(){
		return this.location;
	}
	
	public int getID(){
		return this.id;
	}


	@Override
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
