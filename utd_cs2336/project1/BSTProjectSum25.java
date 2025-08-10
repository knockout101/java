import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BSTProjectSum25 {
    static BinarySearchTree<String> bst = new BinarySearchTree<>();
    public static void main(String[] args) {
        readBookRecords("data.txt");
        System.out.println("Welcome to the Information Retrieval System!");

        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. PreOrder Traversal of BST"); // print with articles
            System.out.println("2. InOrder Traversal of BST"); // print without articles
            System.out.println("3. PostOrder Traversal of BST"); // print without articles
            System.out.println("4. Search for a keyword");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice (1-5): ");

            choice = keyboard.nextInt();
            String keyword = "";
            keyboard.nextLine(); // Consume the newline character

            if(choice == 1) // PreOrder Traversal
                bst.preOrder(false);
            else if(choice == 2) // InOrder Traversal
                bst.inOrder(false);
            else if(choice == 3) // PostOrder Traversal
                bst.postOrder(false);
            else if(choice == 4) { // Search for a keyword
                System.out.print("Enter keyword to search: ");
                keyword = keyboard.nextLine();
                System.out.println("Searching for keyword: " + keyword);
                // Search logic will be implemented here
                // if the keyword is found, print the keyword and its articles
                TreeNode<String> searchResult = bst.search(bst.getRoot(), keyword);
                if(!searchResult.element.equals(keyword)) {
                    System.out.println("Keyword not found.");
                } else {
                    System.out.println("Keyword found: " + searchResult.element);
                    for (Article article : searchResult.head) {
                        System.out.println(article);
                    }
                }
                
            } else if(choice == 5) {
                System.out.println("Exiting the program. Goodbye!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        
        keyboard.close();
    }

    public static void readBookRecords(String fileName) {
        try(BufferedReader dataInput = new BufferedReader(new FileReader(fileName))) {
            while(dataInput.ready()) {
                int ID = Integer.parseInt(dataInput.readLine());
                String title = dataInput.readLine();
                String author = dataInput.readLine();
                int numKeys = Integer.parseInt(dataInput.readLine());
                
                Article article = new Article(ID, title, author);

                for(int i = 0; i < numKeys; i++) {
                    bst.insert(dataInput.readLine(), article);
                }
                dataInput.readLine(); // Skips the empty line after keywords
            }

        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }       
    }

}
