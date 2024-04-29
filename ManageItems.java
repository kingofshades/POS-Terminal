import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class ManageItems {
    ArrayList<Item> items;
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
            System.out.println("Item Information successfully saved");
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
        System.out.println("Item ID\t\tDescription\t\tPrice\t\tQuantity");
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

                System.out.print("Enter new Creation Date (dd/MM/yyyy): ");
                String newDateCreation = scanner.nextLine().trim();
                if (!newDateCreation.isEmpty()) {
                    item.setDateCreation(newDateCreation);
                }

                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found.");
        }
    }

    // Incomplete
    public void removeItem(int itemID) {
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
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void saveItems() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("items.txt"))) {
            for (Item i : items) {
                pw.println(i);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}