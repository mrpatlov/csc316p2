import java.io.IOException;

import javax.activation.CommandObject;
import javax.activation.DataHandler;

public class Tickets implements Comparable<Tickets>, CommandObject {

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
	
	@Override
	public void setCommandContext(String arg0, DataHandler arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Tickets arg0) {
		if (arg0.id > this.id){
			return 1;
		}
		if (arg0.id < this.id){
			return -1;
		}
		return 0;
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

}
