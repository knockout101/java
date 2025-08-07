public class test {

    public static void main(String[] args) {
        Base base = new Base();
        Inherit inherit = new Inherit();
        SecondInherit second = new SecondInherit();

        System.out.println(base);
        System.out.println(inherit);
        System.out.println(second);
    }

}

class Base {
    @Override
    public String toString() {
        return "I am " + this.getClass().toString().substring(6);
    }
}

class Inherit extends Base {}

class SecondInherit extends Inherit {}