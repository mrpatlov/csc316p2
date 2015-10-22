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
	
	TreeMap<Tickets> idTree = new TreeMap();
	PriorityTree priority = new PriorityTree();

	private void processCommand(String command, int value) {
		if (command.equals("-")){
			//code to remove by id
		}
		if (command.equals("+")){
			Tickets myTicket = new Tickets(value);
			priority.add(myTicket);
			idTree.add(myTicket);
			System.out.println("     id = " + myTicket.getID());
			//code to add by priority
		}
		if (command.equals("?")){
			Tickets tick = idTree.find(value);
			
			//code to query position by id
		}
		if (command.equals("*")){
			//code to remove highest priority
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
