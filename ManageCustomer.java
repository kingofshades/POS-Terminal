import java.util.*;
import java.io.*;

public class ManageCustomer {
    static ArrayList<Customer> customers;
    private static int customerID;
    Scanner scanner;

    public ManageCustomer() {
        customerID = 0;
        customers = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadCustomers();
    }

    public static int getNextCustomerId() {
        return ++customerID;
    }
    public static void setNextCustomerId(int id){
        customerID = id;
    }
    public void addCustomer() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Sales Limit: ");
        double salesLimit = Double.parseDouble(scanner.nextLine());
        System.out.println("\nEnter 1 to add the Customer\tEnter 2 to Cancel");
        int ch = Integer.parseInt(scanner.nextLine());
        if (ch == 1) {
            Customer newCustomer = new Customer(getNextCustomerId(), name, address, phone, email, salesLimit);
            customers.add(newCustomer);
            System.out.println("Customer Information successfully saved");
        } else if (ch == 2) {
            return;
        } else {
            System.out.println("Wrong Input");
        }
    }

    public void modifyCustomer() {
        System.out.print("Enter Customer ID: ");
        String line = scanner.nextLine();
        int customerID = Integer.parseInt(line);
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID) {
                System.out.println("Customer found. Enter new details. Leave blank for unchanged values.");

                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    customer.setName(name);
                }

                System.out.print("Enter Address: ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) {
                    customer.setAddress(address);
                }

                System.out.print("Enter Phone: ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) {
                    customer.setPhoneNo(phone);
                }

                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    customer.setEmail(email);
                }

                System.out.print("Enter Sales Limit: ");
                String salesLimit = scanner.nextLine();
                if (!salesLimit.isEmpty()) {
                    customer.setSalesLimit(Double.parseDouble(salesLimit));
                }

                System.out.println("Customer Information successfully saved.");
                return;
            }
        }

        System.out.println("Customer not found.");
    }

    public void findCustomer() {
        System.out.println("Please specify at least one of the following to find the customer. Leave all fields blank to return to Customers Menu:");
        System.out.print("Customer ID: ");
        String customerIDInput = scanner.nextLine();
        int ID = customerIDInput.isEmpty() ? -1 : Integer.parseInt(customerIDInput);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Sales Limit: ");
        String salesLimitInput = scanner.nextLine();
        double salesLimit = salesLimitInput.isEmpty() ? -1 : Double.parseDouble(salesLimitInput);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-20s %-20s %-12s %-12s%n", "Customer ID", "Name", "Email", "Phone", "Sales Limit");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Customer customer : customers) {
            if (customer.getCustomerID() == ID ||
                customer.getName().equalsIgnoreCase(name) ||
                customer.getEmail().equalsIgnoreCase(email) ||
                customer.getPhoneNo().equalsIgnoreCase(phone) ||
                customer.getSalesLimit() == salesLimit) {
                customer.print();
            }
        }
    }

    public void removeCustomer() {
        System.out.print("Enter Customer ID to remove: ");
        int ID = Integer.parseInt(scanner.nextLine());
        if(ManageSales.cExist(ID)){
            System.out.println("Customer Sale found: Cannot be deleted :/");
        }
        else{
        for (Customer customer : customers) {
            if (customer.getCustomerID() == ID) {
                System.out.println("\nEnter 1 to remove the Customer\tEnter 2 to Cancel");
                String input = scanner.nextLine();
                int ch = Integer.parseInt(input);

                if (ch == 1) {
                    customers.remove(customer);
                    System.out.println("Customer removed successfully.");
                } else if(ch == 2) {
                    System.out.println("Removal cancelled.");
                    return;
                }
                else{
                    System.out.println("Wrong Input.");
                }
                return;
            }
        }

        System.out.println("Customer not found.");
    }
    }

    public void loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                int customerID = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String address = tokens[2];
                String phoneNo = tokens[3];
                String email = tokens[4];
                double payableAmount = Double.parseDouble(tokens[5]);
                double salesLimit = Double.parseDouble(tokens[6]);
                Customer customer = new Customer(customerID, name, address, phoneNo, email, salesLimit);
                customer.setPayableAmount(payableAmount);
                customers.add(customer);
            }
            setNextCustomerId(customers.get(customers.size() - 1).getCustomerID());
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void saveCustomers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("customers.txt"))) {
            for (Customer customer : customers) {
                pw.println(customer);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    static public boolean cExist(int cid){
        for(Customer c : customers)
        {
            if(c.getCustomerID() == cid)
            {
                return true;
            }
        }
        return false;
    }
    public static Customer getCustomer(int cid)
    {
        for(Customer c : customers)
        {
            if(c.getCustomerID() == cid)
            {
                return c;
            }
        }
        return new Customer(-1,"null","null","null","null",-1);
    } 

    public void manageCustomerMenu() {
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
                        addCustomer();
                        break;
                    case 2:
                        modifyCustomer();
                        break;
                    case 3:
                        findCustomer();
                        break;
                    case 4:
                        removeCustomer();
                        break;
                    case 5:
                        saveCustomers();
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}