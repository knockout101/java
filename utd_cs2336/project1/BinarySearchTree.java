import java.util.LinkedList;


public class BinarySearchTree <E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public TreeNode<E> getRoot() {
        return root;
    }

    public void insert(E keyword, Article article) {
        // Insert keyword and article into the BST recursively
        if(root == null) {
            root = new TreeNode<E>(keyword);
            root.head.addFirst(article);
        } else {
            TreeNode<E> current = search(root, keyword); // recursive search
            if(!current.element.equals(keyword)) {
                if (keyword.compareTo(current.element) < 0) {
                    current.left = new TreeNode<>(keyword);
                    current.left.head.addFirst(article);
                } else if (keyword.compareTo(current.element) > 0) {
                    current.right = new TreeNode<>(keyword);
                    current.right.head.addFirst(article);
                } 
            }
            else {
                current.head.addFirst(article); // Add article to the linked list
            }
        }
    }

    public TreeNode<E> search(TreeNode<E> node, E keyword) {
        // Search for the keyword in the BST recursively
        if(node.element.equals(keyword)) {
            return node;
        }
        if(keyword.compareTo(node.element) < 0) {
            if(node.left == null) {
                return node;
            }
            return search(node.left, keyword);
        } else {
            if(node.right == null) {
                return node; 
            }
            return search(node.right, keyword);
        }
    }

    public void inOrder(boolean printArticle) {
        System.out.println("\n===================================");
        System.out.println("InOrder Traversal of BST:");
        inOrder(root, "", true, printArticle);
    }

    public void preOrder(boolean printArticle) {
        System.out.println("\n===================================");
        System.out.println("PreOrder Traversal of BST:");
        preOrder(root, "", true, printArticle);
    }

    public void postOrder(boolean printArticle) {
        System.out.println("\n===================================");
        System.out.println("PostOrder Traversal of BST:");
        postOrder(root, "", true, printArticle);
    }

    private void postOrder(TreeNode<E> node, String prefix, boolean isLeft, boolean printArticle) {
        if(node != null) {
            postOrder(node.left, prefix, true, printArticle);
            postOrder(node.right, prefix, false, printArticle);
            System.out.println("\t" + prefix + node.element);
            if(printArticle) {
                for(Article article : node.head) 
                    System.out.println(article);
                System.out.println();
            }
        }
    }

    private void preOrder(TreeNode<E> node, String prefix, boolean isLeft, boolean printArticle) {
        if(node != null) {
            System.out.println("\t" + prefix + node.element);
            if(printArticle) {
                for(Article article : node.head) 
                    System.out.println(article);
                System.out.println();
            }
            preOrder(node.left, prefix, true, printArticle);
            preOrder(node.right, prefix, false, printArticle);
        }
    }

    private void inOrder(TreeNode<E> node, String prefix, boolean isLeft, boolean printArticle) {
        if(node != null) {
            inOrder(node.left, prefix, true, printArticle);
            System.out.println("\t" + prefix + node.element);
            if(printArticle) {
                for(Article article : node.head) 
                    System.out.println(article);
                System.out.println();
            }
            inOrder(node.right, prefix, false, printArticle);
        }
    }
}

class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;
    protected LinkedList<Article> head;

    public TreeNode(E element) {
        this.element = element;
        head = new LinkedList<Article>();
    }
    public TreeNode() {}
}