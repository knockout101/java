import java.util.ArrayList;
import java.util.Collections;

public class Phonebook {
    public Phonebook() {
        this.contacts = new ArrayList<Contact>();
    }

    public boolean addContact(String name, String phoneNum) throws IllegalArgumentException {
        try {
            Contact newContact = new Contact(name, phoneNum);
            contacts.add(newContact);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(var contact : contacts)
            sb.append(contact.toString() + "\n");
        return sb.toString();
    }
    
    // Look up method
    // For each finding algo O(n)
    public Contact lookUp(String name) {
        for(var contact: contacts) {
            if(contact.getName().equals(name))
                return contact;
        }
        return null;
    }
    // Update method
    public boolean update(Contact contact) {
        for(var contact: contacts) {
            
        }
    }

    // Persist data method

    private ArrayList<Contact> contacts;
}