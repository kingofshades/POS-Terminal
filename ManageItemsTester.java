import java.util.Scanner;

public class ManageItemsTester {
    public static void main(String[] args) {
        ManageItems manager = new ManageItems();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add a new item");
            System.out.println("2. Update Item details");
            System.out.println("3. Find an item");
            System.out.println("4. Remove Existing Item");
            System.out.println("5. Back to Main Menu");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        manager.addItem();
                        break;
                    case 2:
                        System.out.print("Enter Item ID: ");
                        String i = scanner.nextLine();
                        manager.modifyItem(Integer.parseInt(i));
                        break;

                    case 3:
                        manager.findItem();
                        break;

                    case 4:
                        System.out.print("Enter Item ID: ");
                        String id = scanner.nextLine();
                        manager.removeItem(Integer.parseInt(id));
                        break;
                    case 5:
                        manager.saveItems();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
