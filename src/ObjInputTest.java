public class ObjInputTest {

    public static void main(String[] args) {
        SafeInputObj input = new SafeInputObj();

        String name = input.getNonZeroLenString("Enter your name");
        System.out.println("You entered: " + name);

        int age = input.getRangedInt("Enter your age", 1, 120);
        System.out.println("You entered: " + age);

        int favoriteNumber = input.getInt("Enter your favorite number");
        System.out.println("You entered: " + favoriteNumber);

        double price = input.getRangedDouble("Enter a price", 0, 1000);
        System.out.println("You entered: " + price);

        double temperature = input.getDouble("Enter the current temperature");
        System.out.println("You entered: " + temperature);

        boolean isStudent = input.getYNConfirm("Are you a student?");
        System.out.println("You entered: " + isStudent);

        String email = input.getRegExString("Enter your email", "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
        System.out.println("You entered: " + email);

        System.out.println("\nAll SafeInputObj methods tested successfully.");
    }
}
