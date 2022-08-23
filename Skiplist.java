package project2;
import java.util.LinkedList;

class Skiplist {
		private Node head;
		
	public Skiplist(){
		head = new Node(-1, null, null);
	}
	public boolean findElement (int target ) {
		boolean result = false;
		Node node = head;
		while (node!=null) {
			while (node.value < target && node!=null){
				node = node.right;
				if (node.value ==target&& node!=null)
					return true;
			}
			node = node.down;
		}
		return result;
	}
	
	public boolean removeElement ( int target) {
		boolean result = false ;
		Node node = head;
		Node start = null;
		while (node!=null) {
			while(node.value < target && node!=null) {
				start = node.right;
				while(node.right.value==target&& node.right!=null) {
					node = node.right;
				}
			}
			start.right=node.right;
			result =true;
			node = node.down;
		}
		return result;	
	}
	
	public LinkedList<Node> cloestKeyAfter(int target){
		LinkedList<Node> result = new LinkedList<>();
		Node node = head;
		while(node!=null) {
			while((node.value<target||node.value==target) && node!=null ) {
				node = node.right;
			}
			result.push(node);
			node= node.down;
		}
		return result;
	}
	public void insertElement (int target) {
		LinkedList<Node> candidcate = cloestKeyAfter(target);
		boolean insert= true;
		Node down = head;
		while (insert && !candidcate.isEmpty()) {
			Node node =candidcate.pop();
			Node insertNode = new Node(target,node,down);
			insertNode.right =node;
			down = insertNode;	
			insert= Math.random()>0.5;
		}
		if (insert);
			head = new Node(-1,null,down);
			
		
	}
	private class Node{
			public Node(int target, Node node, Node down2) {
				this.value=target;
				this.right=node;
				this.down=down2;
		}
			int value;
			Node right;
			Node down;
				
		}
	public static void main (String arg[]) {
		Skiplist test = new Skiplist();
		test.insertElement(2);
		test.insertElement(1);
		test.insertElement(3);
		boolean test1 =test.findElement(0);
		System.out.println(test1);
		test.insertElement(4);
		boolean test2 =test.findElement(1);
		System.out.println(test2);
		boolean test3 =test.removeElement(0);
		System.out.println(test3);
		boolean test4 =test.removeElement(1);
		System.out.println(test4);
		boolean test5 =test.findElement(1);
		System.out.println(test5);
	
	}
	
}

