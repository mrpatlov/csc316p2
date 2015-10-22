import java.util.*;
public class HelpTickets {

	public static void main(String[] args) {
		
		HelpTickets myTickets = new HelpTickets();
		Scanner console = new Scanner(System.in);
		Scanner line;
		while (console.hasNextLine()){
			try{
				line = new Scanner(console.nextLine());
				System.out.println(line);
				myTickets.validateLine(line);
			}
			catch(Warnings c){
				System.out.println("Warning:" + c.getMessage());
			}
			
		}
		console.close();
	}
	
	TreeMap<Integer, Tickets> idTree = new TreeMap<Integer, Tickets>();
	PriorityTree priority = new PriorityTree();

	private void processCommand(String command, int value) throws Warnings{
		if (command.equals("-")){
			Tickets tick = idTree.remove(value);
			priority.remove(tick.getPriority());
			System.out.println("     " + tick.getPriority() + ", pos = position");
		}
		if (command.equals("+")){
			Tickets myTicket = new Tickets(value);
			priority.put(myTicket);
			idTree.put(myTicket.getID(), myTicket);
			System.out.println("     id = " + myTicket.getID());
			//code to add by priority
		}
		if (command.equals("?")){
			Tickets tick = idTree.get(value);
			if (tick == null){
				throw new Warnings ("there is no ticket with id = " + value + " in the queue");
			}
			
			//need to get position somehow
		}
		if (command.equals("*")){
			if (idTree.isEmpty()){
				throw new Warnings ("removal attempted when queue is empty");
			}
			Tickets tick = priority.pop();
			idTree.remove(tick.getID());
			System.out.println("     id = " + tick.getID() + ", " + tick.getPriority());
		}
		
	}

	private void validateLine(Scanner line) throws Warnings{
		String command = line.next();
		int value;
		if (!(command.equals("+")||command.equals("-")||command.equals("*") || command.equals("?"))){
			throw new Warnings("invalid command " + command);
		}
		if (line.hasNextInt()){
			value = line.nextInt();
		}
		else {
			if (command.equals("+")){
				throw new Warnings("priority " + line.next() + " is not an integer");
			}
			if (command.equals("-")){
				throw new Warnings("id " + line.next() + " is not an integer");
			}
			value = -1;
		}
		processCommand(command, value);
	}

}
