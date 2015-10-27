import java.util.Scanner;
import java.util.TreeMap;
public class HelpTickets {

	public static void main(String[] args) {
		
		HelpTickets myTickets = new HelpTickets();
		Scanner console = new Scanner(System.in);
		Scanner line;
		while (console.hasNextLine()){
			try{
				String myLine = console.nextLine();
				System.out.println(myLine);
				line = new Scanner(myLine);
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

	/**
	 * Process commands that have been validated
	 * @param command The command to be executed
	 * @param value the value associated with the command, this may be priority or id
	 * @throws Warnings if attempting to do something that won't work
	 */
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
			int targetPrio = tick.getPriority();
			int position = priority.position(targetPrio);
			System.out.println("     pos = " + position);
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
/**
 * Validates the input to insure valid command entered
 * calls processCommand if input valid
 * @param line a scanner containing an input line
 * @throws Warnings
 */
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
