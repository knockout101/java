import java.util.LinkedList;

public class HashTable { // replaces BinarySearchTree.java and rename BSTProjectSum25 to HashTableProjectSum25 {
	
	private static final int SIZE = 191; // numKeys with duplicates * 2 â†’ nextPrime
	private Entry[] table;
	
	public HashTable() {
	    table = new Entry[SIZE];
	}
	
	private int hash(String key) {
	    int hash = 0;
	    for (char c : key.toCharArray()) {
	        hash += c;
	    }
	    
	    return hash % SIZE;
	}
	
	private int findSlotQuadraticProbing(String key) {
	    int index = hash(key);
	    int i = 1;
	    while (table[index] != null && !table[index].key.equals(key)) {
	        index = (index + i*i) % SIZE;
	        i++;
	        
	        if (i >= SIZE) throw new RuntimeException("Hash table is full!");
	    }
	    
	    return index;
	}
	
	public void put(String key, Article value) {
	    // Add the try catch block
	    int index = findSlotQuadraticProbing(key);
	    if (table[index] == null) {
	        table[index] = new Entry(key, value);
	    } else {
	        table[index].addValue(value);
	    }
	}
	
	public int get(String key) {
	    int index = findSlotQuadraticProbing(key);
	    if (table[index] != null && table[index].key.equals(key)) {
	        return index;
	    }
	    
	    return -1; // keyword does not exist
	}
	
	public void display(int index) {
	   System.out.print(index + ": ");
	   System.out.println(table[index].key);
	   for (Article node : table[index].values)
	       System.out.println(node);
	   System.out.println();
	}
	
	public void display() {
	    for (int i=0; i < SIZE; i++) {
	        System.out.print(i + ": ");
	        if (table[i] != null) {
	            System.out.println(table[i].key);
	            for (Article node : table[i].values)
	                System.out.println(node);
	            System.out.println();
	        } else {
	            System.out.println("null");
	        }
	        
	    }
	}
	
}

class Entry {
    String key;
    LinkedList<Article> values;
    
    public Entry(String key, Article value) {
        this.key = key;
        values = new LinkedList<Article>();
        values.addFirst(value);
    }
    
    void addValue(Article value) {
        values.addFirst(value);
    }
}