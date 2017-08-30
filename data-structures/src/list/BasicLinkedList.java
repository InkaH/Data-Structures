package list;

/**
 * Home-made Linked List data structure. List's Big O: add = O(1) remove = O(1)
 * insert = O(n) removeAt = O(n) find = O(n) get = O(n)
 */
public class BasicLinkedList<X> {
	private Node first;
	private Node last;
	private int nodeCount;

	public BasicLinkedList() {
		first = null;
		last = null;
		nodeCount = 0;
	}

	public int size() {
		return nodeCount;
	}

	public void add(X item) {
		// first?
		if (first == null) {
			first = new Node(item);
			last = first;
		} else {
			Node newLastNode = new Node(item);
			last.setNextNode(newLastNode);
			last = newLastNode;
		}
		nodeCount++;
	}

	public X remove() {
		if (first == null) {
			throw new IllegalStateException(
					"List is empty.");
		}
		X oldFirst = first.getNodeItem();
		first = first.getNextNode();
		nodeCount--;
		return oldFirst;
	}

	public void insert(X item, int position) {
		if (size() < position) {
			throw new IllegalStateException(
					"Position is greater than list size.");
		}
		// start traversing from first Node
		Node currentNode = first;
		for (int i = 1; i < position && currentNode != null; i++) {
			currentNode = currentNode.getNextNode();
		}
		Node newNode = new Node(item);
		Node nextNode = currentNode.getNextNode();
		currentNode.setNextNode(newNode);
		newNode.setNextNode(nextNode);
		nodeCount++;
	}

	public X removeAt(int position) {
		if (first == null || size() < position) {
			throw new IllegalStateException(
					"List is empty or position is greater than list size.");
		}

		Node currentNode = first;
		Node prevNode = first;

		for (int i = 1; i < position && currentNode != null; i++) {
			prevNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		prevNode.setNextNode(currentNode.getNextNode());
		nodeCount--;
		return currentNode.getNodeItem();
	}

	public int find(X item) {
		if (first == null) {
			throw new IllegalStateException("List is empty.");
		}
		Node currentNode = first;
		for (int i = 1; i < size() && currentNode != null; i++) {
			if (currentNode.getNodeItem().equals(item)) {
				return i;
			}
			currentNode = currentNode.getNextNode();
		}
		return -1;
	}

	public X get(int position) {
		if (size() < position || first == null) {
			throw new IllegalStateException(
					"Position is greater than list size or list is empty.");
		}
		Node currentNode = first;
		for (int i = 1; i <= size() && currentNode != null; i++) {
			if (i == position) {
				return currentNode.getNodeItem();
			}
			currentNode = currentNode.getNextNode();
		}
		return null;
	}

	public String toString() {
		StringBuffer contents = new StringBuffer();
		Node curNode = first;

		while (curNode != null) {
			contents.append(curNode.getNodeItem());
			curNode = curNode.getNextNode();

			if (curNode != null) {
				contents.append(", ");
			}
		}
		return contents.toString();
	}

	private class Node {
		private Node nextNode;
		private X nodeItem;

		public Node(X item) {
			this.nextNode = null;
			this.nodeItem = item;
		}

		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}

		public Node getNextNode() {
			return nextNode;
		}

		public X getNodeItem() {
			return nodeItem;
		}
	}
}
