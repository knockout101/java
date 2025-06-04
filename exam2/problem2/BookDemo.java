public class BookDemo {
    public static void main(String[] args) {
        Book[] books = new Book[30];
        books[0] = new Book("Dragon Slayer", new Author("Mikael", "Rob"), 10.00);
        System.out.println("Author of the first book: " + books[0].getAuthor());
        System.out.println("Title of the book: " + books[0].getTitle());    
    }
}