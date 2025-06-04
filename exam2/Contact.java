public class Contact {
    public Contact(String name, String phoneNum) {
        if(name.isBlank() || phoneNum.length() != 10)
            throw new IllegalArgumentException("Contact cannot be created because of invalid information");
        this.name = name;
        this.phoneNum = phoneNum;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + phoneNum;
    }

    private String name;
    private String phoneNum;
}