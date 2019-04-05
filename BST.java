public class BST <T extends Comparable <T>>{
    private BSTNode root;

    private class BSTNode{
        public Comparable data;
        public BSTNode left;
        public BSTNode right;

        private BSTNode(T data){
            this.data = data;
        }
    }


    public boolean find(T  value){
        return find(root, value);
    }

    private boolean find(BSTNode node, T value){
        if (node == null){ //if the value does not exist; tree is empty
            return false;
        }
        if (node.data.compareTo(value) == 0){
            return true;
        } else if (node.data.compareTo(value) > 0){
            return find(node.left, value);
        } else {
            return find(node.right, value);
        }

    }

    public void insert(T value){
       root = insert(root, value);
    }

    private BSTNode insert(BSTNode node, T value){
        if (node == null){ //empty tree contains no elements
            BSTNode newNode = new BSTNode(value); //create a new tree which contains a single element
            return newNode;
        } else if (node.data.compareTo(value) > 0){ //if value to be inserted is larger than root
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }


    public void print(){
        print(root);
    }

    private void print(BSTNode node){ //in-order traversal
        if (node != null){
            print(node.left);
            System.out.println(node.data);
            print(node.right);
        }
    }

    public void delete(T value){
        root = delete(root, value);
    }

    private BSTNode delete(BSTNode node, T value){
        if (node == null){ //empty tree contains no elements
            return null; //so nothing to delete
        }
        if (node.data.compareTo(value) == 0) { //if value stored in root appears to be the value I want to delete
            if (node.left == null) { //if the node is a leaf (0 children)
                return node.right;
            } else if (node.right == null) { //deleting a node with 1 child
                return node.left;
            } else {
                if (node.right.left == null) { //deleting a node with 2 children
                    node.data = node.right.data;
                    node.right = node.right.right;
                    return node;
                } else {
                    node.data = removeSmallest(node.right);
                    return node;
                }
            }
        } else if (node.data.compareTo(value) < 0){ //if value stored in root is less than the value I want to delete
            node.right = delete(node.right, value);
            return node;
        } else {
            node.left = delete(node.left, value);
            return node;
        }
    }

    private Comparable removeSmallest(BSTNode node){
        if (node.left.left == null){
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        } else {
            return removeSmallest(node.left);
        }
    }


}





