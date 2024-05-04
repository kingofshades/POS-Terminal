import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class ManageItems {
    static ArrayList<Item> items;
    private static int itemID;
    public LocalDateTime date;
    public String dateCreation;
    Scanner scanner;

    public ManageItems() {
        itemID = 0;
        items = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadItems();
    }

    public static int getNextItemId() {
        return ++itemID;
    }
    public static void setNextItemId(int id) {
        itemID = id;
    }

    public String getDate() {
        date = LocalDateTime.now();
        dateCreation = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return dateCreation;
    }

    public void addItem() {
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        String price = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        String qty = scanner.nextLine();
        System.out.println("\nEnter 1 to add the item\tEnter 2 to Cancel");
        int ch = Integer.parseInt(scanner.nextLine());
        if (ch == 1) {
            Item i = new Item(getNextItemId(), description, Double.parseDouble(price), Integer.parseInt(qty),
                    getDate());
            items.add(i);
            System.out.println("Item Information successfully saved :)");
        } else if (ch == 2) {
            return;
        } else {
            System.out.println("Wrong Input");
        }
    }

    public void findItem() {
        System.out.println("Please specify at least one of the following to find the item. Leave all fields blank to return to Customers Menu:");
        System.out.print("Enter Item ID: ");
        String itemIDInput = scanner.nextLine();
        int ID = itemIDInput.isEmpty() ? -1 : Integer.parseInt(itemIDInput);
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        String priceInput = scanner.nextLine();
        double price = priceInput.isEmpty() ? -1 : Double.parseDouble(priceInput);
        System.out.print("Enter Quantity: ");
        String qtyInput = scanner.nextLine();
        int qty = qtyInput.isEmpty() ? -1 : Integer.parseInt(qtyInput);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-8s %-25s %-12s %-8s%n", "Item ID", "Description", "Price", "Quantity");
        System.out.println("------------------------------------------------------------------");
        for (Item item : items) {
            if ((item.getItemID() == ID) || item.getDescription().equals(description) || item.getPrice() == price || item.getQty() == qty) {
                item.print();
            }
        }
    }

    public void modifyItem(int itemID) {
        boolean itemFound = false;
        for (Item item : items) {
            if (itemID == item.getItemID()) {
                System.out.println("Enter new item details. Leave blank for unchanged values.");

                System.out.print("Enter new Description: ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) {
                    item.setDescription(newDescription);
                }

                System.out.print("Enter new Price: ");
                String newPrice = scanner.nextLine();
                if (!newPrice.isEmpty()) {
                    item.setPrice(Double.parseDouble(newPrice));
                }

                System.out.print("Enter new Quantity: ");
                String newQty = scanner.nextLine();
                if (!newQty.isEmpty()) {
                    item.setQty(Integer.parseInt(newQty));
                }
                item.setDateCreation(getDate());
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found.");
        }
    }
    public void removeItem(int itemID) {
        if(ManageSales.sliExist(itemID)){
        System.out.println("Item Sale found: Cannot be deleted :/");
        }
        else{
        for (Item i : items) {
            if (itemID == i.getItemID()) {
                System.out.println("\nEnter 1 to remove the item\tEnter 2 to Cancel");
                String input = scanner.nextLine();
                int ch = Integer.parseInt(input);
                if (ch == 1) {
                    items.remove(i);
                    System.out.println("Item removed.");
                    return;
                } else if (ch == 2) {
                    System.out.println("Removal cancelled.");
                    return;
                } else {
                    System.out.println("Wrong Input.");
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }
    }

    public void loadItems() {
        try (BufferedReader br = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                int itemID = Integer.parseInt(tokens[0]);
                String description = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                int qty = Integer.parseInt(tokens[3]);
                String date = tokens[4];
                Item i = new Item(itemID, description, price, qty, date);
                items.add(i);
            }
            setNextItemId(items.get(items.size() - 1).getItemID());
            br.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void saveItems() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("items.txt"))) {
            for (Item i : items) {
                pw.println(i);
            }
            pw.flush();
            pw.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public static boolean iExist(int iid){
        for(Item i : items)
        {
            if(i.getItemID() == iid)
            {
                return true;
            }
        }
        return false;
    }
    public static Item getItem(int iid)
    {
        for(Item i : items)
        {
            if(i.getItemID() == iid)
            {
                return i;
            }
        }
        return new Item(-1,"null",-1,-1,"null");
    }
    public void manageItemMenu() {
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
                        addItem();
                        break;
                    case 2:
                        System.out.print("Enter Item ID: ");
                        String i = scanner.nextLine();
                        modifyItem(Integer.parseInt(i));
                        break;
    
                    case 3:
                        findItem();
                        break;
    
                    case 4:
                        System.out.print("Enter Item ID: ");
                        String id = scanner.nextLine();
                        removeItem(Integer.parseInt(id));
                        break;
                    case 5:
                        saveItems();
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
     
}