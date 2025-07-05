public class hw_6_32_test {
    static long[] valid_cc_numbers = {
        378282246310005L,
        371449635398431L,
        378734493671000L,
        5610591081018250L,
        30569309025904L,
        38520000023237L,
    };

    static long[] invalid_cc_numbers = {
        1234567890123456L,
        5555555555554443L,
        4000000000000000L,
        9999999999999999L,
        4532015112830367L,
        3782822463100051L,
        6011111111111118L
    };

    public static void main(String[] args) {      
        validTest();
        invalidTest();
    }

    static void validTest() {
        // Testing validation of valid numbers
        for(var number : valid_cc_numbers) {
            assert(hw_6_32.isValid(number));
        }
        // Valid test success
        System.out.println("Valid Test [SUCCESS]");
    }

    static void invalidTest() {
        // Testing validation of invalid numbers
        for(var number: invalid_cc_numbers) {
            assert(!hw_6_32.isValid(number));
        }
        // Invalid test success
        System.out.println("Invalid Test [SUCCESS]");
    }
}