/**
 * Class which defines the unbalanced, sorted binary tree used in the HelpTickets program.
 * 
 * @author John Parsons
 *
 */
public class PriorityTree {
	
	private Node root;
	
	/**
	 * primary constructor for PriorityTree objects.
	 * @param data The first ticket added to the tree. Will be stored in the root node.
	 */
	public PriorityTree(Tickets data){
		if(data != null){
			this.root = new Node(data);
		} else {
			this.root = null;
		}
	}
	
	/**
	 * secondary constructor for PriorityTree objects. This should NOT be used in Project 2.
	 * @param priority The priority of the first ticket to add to the tree.
	 */
	public PriorityTree(int priority){
		this(new Tickets(priority));
	}
	
	/**
	 * tertiary constructor for PriorityTree objects.
	 */
	public PriorityTree(){
		this(null);
	}
	
	/**
	 * Inserts a ticket to the tree. Will return a boolean value representing the success of the operation.
	 * @param newTicket The ticket to add to the tree.
	 * @return true if the item was successfully inserted, false if there is already an item with the given priority.
	 */
	public boolean put(Tickets newTicket){
		if(this.root == null){
			root = new Node(newTicket);
			return true;
		} else {
			return root.putHelper(newTicket);
		}
	}
	
	/**
	 * Removes the ticket with the specified priority from the tree.
	 * @param targetPrio The priority of the ticket to remove.
	 * @return The removed ticket
	 */
	public Tickets remove(int targetPrio) {
		Tickets removedTick = null;
		if (root == null) {
			return null;
		} else if (root.data.getPriority() == targetPrio) {
			removedTick = root.data;
			if(root.right == null) {
				root = root.left;
			} else if(root.left == null) {
				root = root.right;
			} else {
				Node iterator = root.right;
				while(iterator.left.left != null) {
					iterator = iterator.left;
				}
				iterator.left.left = root.right.left;
				iterator.left.right = root.right.right;
				iterator.left.count = root.right.count;
				root.right = iterator.left;
				iterator.left = iterator.left.right;
			}
		} else {
			removedTick = root.removeHelper(targetPrio);
		}
		return removedTick;
	}
	
	/**
	 * Removes the ticket with the highest priority from the tree.
	 * @return The removed ticket
	 */
	public Tickets pop() {
		Tickets removedTick = null;
		if(root == null){
			return null;
		} else if (root.right == null) {
			removedTick = root.data;
			if (root.left != null) {
				root = root.left;
			} else {
				root = null;
			}
		} else {
			removedTick = root.popHelper();
		}
		return removedTick;
	}
	
	/**
	 * Finds a ticket with the given priority in the tree.
	 * @param targetPrio The priority of the ticket to find.
	 * @return The ticket with the specified priority.
	 */
	public Tickets find(int targetPrio) {
		return root.findHelper(targetPrio);
	}
	
	/**
	 * Gives the number of tickets in the tree.
	 * @return The number of tickets in the tree.
	 */
	public int size(){
		return root.count;
	}
	
	/**
	 * Finds the position in queue of a ticket with the given priority.
	 * @param targetPrio The priority of the ticket to find position of.
	 * @return The position of the ticket.
	 */
	public int position(int targetPrio) {
		int position = root.count;
		return root.positionHelper(targetPrio, position);
	}
	
	private void removeNode(Node parent, int targetPrio) {
		if(parent.right != null && parent.right.data.getPriority() == targetPrio) {
			if(parent.right.left == null) {
				parent.right = parent.right.right;
				return;
			} else if(parent.right.right == null) {
				parent.right = parent.right.left;
				return;
			} else {
				Node iterator = parent.right;
				while(iterator.left.left != null) {
					iterator = iterator.left;
				}
				iterator.left.left = parent.right.left;
				iterator.left.right = parent.right.right;
				iterator.left.count = parent.right.count;
				parent.right = iterator.left;
				iterator.left = iterator.left.right;
			}
		} else if(parent.left != null && parent.left.data.getPriority() == targetPrio) {
			if(parent.left.left == null) {
				parent.left = parent.left.right;
				return;
			} else if(parent.left.right == null) {
				parent.left = parent.left.left;
				return;
			} else {
				Node iterator = parent.left;
				while(iterator.left.left != null) {
					iterator = iterator.left;
				}
				iterator.left.left = parent.left.left;
				iterator.left.right = parent.left.right;
				iterator.left.count = parent.left.count;
				parent.left = iterator.left;
				iterator.left = iterator.left.right;
			}
		}
	}

	
	/**
	 * Supporting node class for the PriorityTree. Nodes remember the number of items in the subtree they are the root of.
	 * @author Rob
	 *
	 */
	public class Node {
		/** number of items in this subtree */
		private int count;
		/** left child */
		private Node left;
		/** right child */
		private Node right;
		/** the Ticket held at this node */
		private Tickets data;
		
		/**
		 * Primary constructor for nodes
		 * @param data The ticket to store in this node.
		 */
		private Node(Tickets data) {
			this.count = 1;
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		/**
		 * Helper method used in insert operations
		 * @param newTicket The ticket to insert
		 * @return True if the operation was a success.
		 */
		private boolean putHelper(Tickets newTicket) {
			int newPriority = newTicket.getPriority();
			int thisPriority = data.getPriority();
			if(newPriority < thisPriority) {
				count++;
				if(left == null) {
					left = new Node(newTicket);
					return true;
				} else {
					left.putHelper(newTicket);
				}
			} else if(newPriority > thisPriority) {
				count++;
				if(right == null) {
					right = new Node(newTicket);
					return true;
				} else {
					right.putHelper(newTicket);
				}
			}
			return false;
		}
		
		/**
		 * Helper method used in removal of highest priority operations
		 * @return The removed ticket
		 */
		private Tickets popHelper() {
			Tickets removedTick = null;
			if (right.right == null) {
				count--;
				removedTick = right.data;
				if(right.left != null) {
					right = right.left;
				} else {
					right = null;
				}
			} else {
				count--;
				removedTick = right.popHelper();
			}
			return removedTick;
		}
		
		/**
		 * Helper method used in find operations
		 * @param targetPrio The priority of the ticket to find.
		 * @return The ticket with the specified priority.
		 */
		private Tickets findHelper(int targetPrio) {
			int thisPriority = data.getPriority();
			if(targetPrio == thisPriority) {
				return data;
			} else if(targetPrio < thisPriority) {
				if(left != null) {
					return left.findHelper(targetPrio);
				}
			} else if(targetPrio > thisPriority) {
				if(right != null) {
					return right.findHelper(targetPrio);
				}
			}
			return null;
		}
		
		/**
		 * Helper method used in general removal operations
		 * @param targetPrio The priority of the ticket to remove.
		 * @return The removed Ticket
		 */
		private Tickets removeHelper(int targetPrio) {
			Tickets removedTick = null;
			int thisPriority = data.getPriority();
			if (targetPrio < thisPriority) {
				count--;
				if (left == null) return null;
				if (targetPrio == left.data.getPriority()) {
					removedTick = left.data;
					removeNode(this, targetPrio);
				} else {
					removedTick = left.removeHelper(targetPrio);
				}
			} else if (targetPrio > thisPriority){
				count--;
				if (right == null) return null;
				if (targetPrio == right.data.getPriority()) {
					removedTick = right.data;
					removeNode(this, targetPrio);
				} else {
					removedTick = right.removeHelper(targetPrio);
				}
			}
			return removedTick;
		}
		
		/**
		 * Helper method used in position location.
		 * @param targetPrio The priority of the ticket to find position of.
		 * @param position The maximum possible position in queue
		 * @return the position in queue
		 */
		private int positionHelper(int targetPrio, int position) {
			int thisPriority = data.getPriority();
			if(targetPrio == thisPriority) {
				if (left != null) {
					position -= left.count;
				}
				return position;
			} else if(targetPrio < thisPriority) {
				if (left != null) {
					return left.positionHelper(targetPrio, position);
				}
			} else if(targetPrio > thisPriority) {
				if(right != null) {
					if(left != null) {
						position = position - (left.count + 1);
					} else {
						position--;
					}
					return right.positionHelper(targetPrio, position);
				}
			}
			return position;
		}
	}
}