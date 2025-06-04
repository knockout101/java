public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.addContact("Tristan", "2142634155");
        phonebook.addContact("Mike", "2148023155");

        System.out.print(phonebook);

        Contact foundContact = phonebook.lookUp("Tristan");
        System.out.println("Found " + (foundContact != null ? foundContact : "None"));
    }
}
