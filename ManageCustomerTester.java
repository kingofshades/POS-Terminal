import java.util.Scanner;

public class ManageCustomerTester {
    public static void main(String[] args) {
        ManageCustomer manager = new ManageCustomer();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nCustomers Menu:");
            System.out.println("1. Add a new customer");
            System.out.println("2. Update Customer details");
            System.out.println("3. Find a customer");
            System.out.println("4. Remove Existing Customer");
            System.out.println("5. Back to Main Menu");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        manager.addCustomer();
                        break;
                    case 2:

                        manager.modifyCustomer();
                        break;
                    case 3:
                        manager.findCustomer();
                        break;
                    case 4:
                        manager.removeCustomer();
                        break;
                    case 5:
                        manager.saveCustomers();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}