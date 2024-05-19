public class UserInput {
    public static void main(String[] args)
    {
        String currentYear = "2022";
        int currentYearInt = Integer.parseInt(currentYear);
        String usersDateOfBirth = "1999";
        int dateOfBirth = Integer.parseInt(usersDateOfBirth);

        System.out.println("Age = " + (currentYearInt + usersDateOfBirth));
        System.out.println("Age = " + (currentYearInt - dateOfBirth));

        String usersAgeWithPartialYear = "22.5";
        double ageWithPartialYear = Double.parseDouble(usersAgeWithPartialYear);
        System.out.println("The user says he's " + ageWithPartialYear);

        System.out.println(getInputFromConsole(currentYearInt));
        System.out.println(getInputFromScanner(currentYearInt));

    }

    public static String getInputFromConsole(int currentYear)
    {
        String name = System.console().readLine("Hi, what's your name?");
        System.out.println("Hi " + name + ", thanks!");

        return "";
    }

    public static String getInputFromScanner(int currentYear)
    {
        return "";
    }
}
