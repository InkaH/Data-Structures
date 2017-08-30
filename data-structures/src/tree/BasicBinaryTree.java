package tree;

public class BasicBinaryTree<X extends Comparable<X>> {

	private Node root;
	private int size;

	public BasicBinaryTree() {
		this.root = null;
	}

	public int size() {
		return size;
	}

	public void add(X item) {
		Node node = new Node(item);
		// first item in tree?
		if (root == null) {
			this.root = node;
			this.size++;
		} else {
			insert(this.root, node);
		}
	}

	// helper method for add
	private void insert(Node parent, Node child) {
		// if child is less than parent, it goes to left side
		if (child.getItem().compareTo(parent.getItem()) < 0) {
			// if left node is null, insert child there
			if (parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				this.size++;
			}
			// otherwise call insert again
			else {
				insert(parent.getLeft(), child);
			}
		}
		// if child is greater than parent, it goes to right side
		else if (child.getItem().compareTo(parent.getItem()) > 0) {
			if (parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				this.size++;
			} else {
				insert(parent.getRight(), child);
			}
		}

		// if parent and child are equal, do nothing - data in binary tree is
		// assumed to be unique
	}

	public boolean contains(X item) {
		Node currentNode = getNode(item);
		if (currentNode == null) {
			return false;
		} else
			return true;
	}

	// helper method for contains
	private Node getNode(X item) {
		Node currentNode = this.root;

		while (currentNode != null) {
			int val = item.compareTo(currentNode.getItem());
			// match! return.
			if (val == 0) {
				return currentNode;
			}
			// smaller. go left.
			else if (val < 0) {
				currentNode = currentNode.getLeft();
			}
			// it was bigger! move right.
			else {
				currentNode = currentNode.getRight();
			}
		}
		return null;
	}

	public boolean delete(X item) {
		boolean deleted = false;

		if (this.root == null) {
			return false;
		}
		// find node to delete
		Node currentNode = getNode(item);
		if (currentNode != null) {
			// if the node to delete doesn't have kids, just delete
			if (currentNode.getLeft() == null && currentNode.getRight() == null) {
				unlink(currentNode, null);
				deleted = true;
			}
			// if the node to delete only has a right child, remove it in the
			// hierarchy
			else if (currentNode.getLeft() == null
					&& currentNode.getRight() != null) {
				unlink(currentNode, currentNode.getRight());
				deleted = true;
				// if the node to delete only has a left child, remove it in the
				// hierarchy
			} else if (currentNode.getLeft() != null
					&& currentNode.getRight() == null) {
				unlink(currentNode, currentNode.getLeft());
				deleted = true;
			}
			// it has both children, do a node swap to delete
			else {
				// swap node with right most leaf node on the left
				Node child = currentNode.getLeft();
				while (child.getRight() != null && child.getLeft() != null) {
					child = child.getRight();
				}
				// child is now the to be deleted node's right most leaf node.
				// Replace the current node with this node.
				child.getParent().setRight(null); // remove the leaf node from
													// its current position
				child.setLeft(currentNode.getLeft()); // attach child to the to
														// be deleted node's
														// left child
				child.setRight(currentNode.getRight()); // same to right side
				// replace the deleted node with child that is now linked to the
				// deleted node's children
				unlink(currentNode, child);
				deleted = true;
			}
		}

		if (deleted) {
			this.size--;
		}
		return deleted;
	}

	// helper method for delete
	private void unlink(Node currentNode, Node newNode) {
		// is it the root?
		if (currentNode == this.root) {
			newNode.setLeft(currentNode.getLeft());
			newNode.setRight(currentNode.getRight());
			this.root = newNode;
			// nope. is it on the right?
		} else if (currentNode.getParent().getRight() == currentNode) {
			currentNode.getParent().setRight(newNode);
			// nah. so it's on the left.
		} else {
			currentNode.getParent().setLeft(newNode);
		}
	}

	private class Node {
		private Node left;
		private Node right;
		private Node parent;
		private X item;

		public Node(X item) {
			this.item = item;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public X getItem() {
			return item;
		}

		public void setItem(X item) {
			this.item = item;
		}
	}
}
