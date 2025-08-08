import java.util.LinkedList;

public class BinarySearchTree <E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public void insert(E keyword, Article article) {
        if(root == null) {
            root = new TreeNode<>(keyword);
            root.head.addFirst(article);
        } else {
            TreeNode<E> current = search(keyword);
            if(current != null) {
                if(keyword.compareTo(current.element) < 0) {
                    current.left = new TreeNode<>(keyword);
                    current.left.head.addFirst(article);
                }
                else if (keyword.compareTo(current.element) > 0) {
                    current.right = new TreeNode<>(keyword);
                    current.right.head.addFirst(article);
                } else {
                    current.head.addFirst(article); // Add article to the linked list
                }
            }
        }

    }

    public TreeNode<E> search(E keyword) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while(current != null) {
            int cmp = keyword.compareTo(current.element);
            if(cmp < 0) {
                parent = current;
                current = current.left;
            } else if(cmp > 0) {
                parent = current;
                current = current.right;
            } else {
                return current; // Found the node
            }
        }
        return parent; // Not found
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
            postOrder(node.left, prefix + (isLeft ? "│   " : "    "), true, printArticle);
            postOrder(node.right, prefix + (isLeft ? "    " : "│   "), false, printArticle);
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.element);
            if(printArticle) {
                for(Article article : node.head) 
                    System.out.println(article);
                System.out.println();
            }
        }
    }

    private void preOrder(TreeNode<E> node, String prefix, boolean isLeft, boolean printArticle) {
        if(node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.element);
            if(printArticle) {
                for(Article article : node.head) 
                    System.out.println(article);
                System.out.println();
            }
            preOrder(node.left, prefix + (isLeft ? "│   " : "    "), true, printArticle);
            preOrder(node.right, prefix + (isLeft ? "    " : "│   "), false, printArticle);
        }
    }

    private void inOrder(TreeNode<E> node, String prefix, boolean isLeft, boolean printArticle) {
        if(node != null) {
            inOrder(node.left, prefix + (isLeft ? "│   " : "    "), true, printArticle);
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.element);
            if(printArticle) {
                for(Article article : node.head) 
                    System.out.println(article);
                System.out.println();
            }
            inOrder(node.right, prefix + (isLeft ? "    " : "│   "), false, printArticle);
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