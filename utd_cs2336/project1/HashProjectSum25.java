import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HashProjectSum25 {
    // Create HashTable Instance
    private static HashTable hashTable = new HashTable();
    public static void main(String[] args) {
        
        // Read book records from the file and populate the hash table
        HashProjectSum25.readBookRecords("data.txt");
        
        System.out.println("Welcome to the Information Retrieval System!");

        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Display HashTable Data"); // display hash table
            System.out.println("2. Search keyword"); // search hash table
            System.out.println("3. Exit the System"); // Exit       
            System.out.print("Please enter your choice (1-3): ");

            choice = keyboard.nextInt();
            String keyword = "";
            keyboard.nextLine(); // Consume the newline character

            if(choice == 1) // display hash table 
                hashTable.display();
            else if(choice == 2) {// search keyword
                System.out.print("Enter keyword to search: ");
                keyword = keyboard.nextLine();
                System.out.println("Searching for keyword: " + keyword);
                int index = hashTable.get(keyword);
                if(index != -1) {
                    System.out.println("Keyword found at index: " + index);
                    hashTable.display(index);
                } else {
                    System.out.println("Keyword not found.");
                }
            }
            else if(choice == 3) // Exit the System
                System.out.println("Exiting the program. Goodbye!");
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        
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
                    hashTable.put(dataInput.readLine(), article);
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