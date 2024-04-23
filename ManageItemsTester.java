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
                        System.out.println("Please specify atleast one of the following to find the item. Leave all fields blank to return to Customers Menu: ");
                        System.out.print("Enter Item ID: ");
                        String itemIdInput = scanner.nextLine();
                        int itemId = itemIdInput.isEmpty() ? -1 : Integer.parseInt(itemIdInput);
                        System.out.print("Enter Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        String priceInput = scanner.nextLine();
                        double price = priceInput.isEmpty() ? -1 : Double.parseDouble(priceInput);
                        System.out.print("Enter Quantity: ");
                        String qtyInput = scanner.nextLine();
                        int qty = qtyInput.isEmpty() ? -1 : Integer.parseInt(itemIdInput);
                        System.out.print("Enter Creation Date: ");
                        String date = scanner.nextLine();
                        manager.findItem(itemId, description, price,qty, date);
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
