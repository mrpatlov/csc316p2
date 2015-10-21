import java.io.IOException;

import javax.activation.CommandObject;
import javax.activation.DataHandler;

public class Tickets implements Comparable<Tickets>, CommandObject {

	int id;
	int priority;
	int count;
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

}
