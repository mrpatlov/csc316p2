// BalancedPriorityTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * Took contributors names out for identity purposes
 * source url = http://users.cis.fiu.edu/~weiss/dsaajava3/code/BalancedPriorityTree.java
 */
public class BalancedPriorityTree
{
    /**
     * Construct the tree.
     */
    public BalancedPriorityTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void put( Tickets x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( Tickets x )
    {
        root = remove( x, root );
    }

       
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode remove( Tickets x, AvlNode t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.getPriority() - t.element.getPriority();
            
        if( compareResult < 0 ){
        	t.count--;
            t.left = remove( x, t.left );
        }
        else if( compareResult > 0 ){
        	t.count--;
            t.right = remove( x, t.right );
        }
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }
    
    public Tickets pop(){
    	Tickets removedTick = findMax();
    	remove(removedTick);
    	return removedTick;
    }
    
    public Tickets findMax( )
    {
        return findMax( root ).element;
    }
    
    private AvlNode findMax( AvlNode t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( Tickets x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    private AvlNode balance( AvlNode t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    private int checkBalance( AvlNode t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode insert( Tickets x, AvlNode t )
    {
        if( t == null )
            return new AvlNode( x, null, null );
        
        int compareResult = x.getPriority() - t.element.getPriority();
        
        if( compareResult < 0 ){
        	t.count++;
            t.left = insert( x, t.left );
        }
        else if( compareResult > 0 ){
        	t.count++;
            t.right = insert( x, t.right );
        }
        else
            ;  // Duplicate; do nothing
        return balance( t );
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode findMin( AvlNode t )
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( Tickets x, AvlNode t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode rotateWithLeftChild( AvlNode k2 )
    {
        AvlNode k1 = k2.left;
        if (k1.left != null) {
        	k2.count -= k1.left.count + 1;
        } else {
        	k2.count--;
        }
        if (k2.right != null) {
        	k1.count += k2.right.count + 1;
        } else {
        	k1.count++;
        }
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode rotateWithRightChild( AvlNode k1 )
    {
        AvlNode k2 = k1.right;
        if (k2.right != null) {
        	k1.count -= k2.right.count + 1;
        } else {
        	k1.count--;
        }
        if (k1.left != null) {
        	k2.count += k1.left.count + 1;
        } else {
        	k2.count++;
        }
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode doubleWithLeftChild( AvlNode k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode doubleWithRightChild( AvlNode k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    
    public int position(int targetPrio) {
		int position = root.count;
		return root.positionHelper(targetPrio, position);
	}
    
    public Tickets find(int targetPrio) {
		if (root == null) return null;
		return root.findHelper(targetPrio);
	}

    private static class AvlNode
    {
            @SuppressWarnings("unused")
			// Constructors
        AvlNode( Tickets theElement )
        {
            this( theElement, null, null );
        }

        AvlNode( Tickets theElement, AvlNode lt, AvlNode rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
            count    = 1;
        }
        
        private int positionHelper(int targetPrio, int position) {
			int thisPriority = element.getPriority();
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
        
        private Tickets findHelper(int targetPrio) {
			int thisPriority = element.getPriority();
			if(targetPrio == thisPriority) {
				return element;
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

        Tickets  element;      // The element in the node
        AvlNode  left;         // Left child
        AvlNode  right;        // Right child
        int      height;       // Height
        int      count;        // The number of items in the subtree
    }

      /** The tree root. */
    private AvlNode root;

}
