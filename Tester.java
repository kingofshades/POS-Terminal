import java.util.Scanner;

public class Tester {
public static void main(String[] args) {
    ManageItems itemsManager = new ManageItems();
    ManageCustomer customerManager = new ManageCustomer();
    ManageSales salesManager = new ManageSales();
    ManagePayments paymentsManager = new ManagePayments();

    while (true) {
        System.out.println("\nMain Menu:");
        System.out.println("1. Manage Items");
        System.out.println("2. Manage Customers");
        System.out.println("3. Make New Sale");
        System.out.println("4. Make Payment");
        System.out.println("5. Print Reports");
        System.out.println("6. Exit");
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    itemsManager.manageItemMenu();
                    break;
                case 2:
                    customerManager.manageCustomerMenu();
                    break;
                case 3:
                    salesManager.MakeSale();
                    break;
                case 4:
                    paymentsManager.managePayments();
                    break;
                case 5:
                    break;
                case 6:
                    scanner.close();
                    itemsManager.saveItems();
                    customerManager.saveCustomers();
                    salesManager.saveSales();
                    salesManager.saveSaleLineItems();
                    paymentsManager.savePayments();
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter a number from 1 to 6.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
}
