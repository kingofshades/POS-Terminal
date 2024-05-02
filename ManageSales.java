import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ManageSales {
    static int saleId, saleLineId;
    static ArrayList<Sale> sales;
    static ArrayList<SaleLineItem> saleLineItems;
    private LocalDateTime date;
    private String dateCreation;
    Scanner scanner;
    int Total;

    public static int getNextSaleId() {
        return ++saleId;
    }
    public static void setNextSaleId(int id) {
        saleId = id;
    }
    public static int getNextSaleLineId() {
        return ++saleLineId;
    }
    public static void setNextSaleLineId(int id) {
        saleLineId = id;
    }
    public ManageSales() {
        sales = new ArrayList<Sale>();
        saleLineItems = new ArrayList<SaleLineItem>();
        scanner = new Scanner(System.in);
        saleId = 0;
        saleLineId = 0;
        loadSales();
        loadSaleLineItems();
    }
    public String getDate() {
        date = LocalDateTime.now();
        dateCreation = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return dateCreation;
    }

    public void MakeSale(){
        System.out.println("Sale Date: " + getDate());
        System.out.print("Enter CustomerId: ");
        String line = scanner.nextLine().trim();
        int cid = Integer.parseInt(line);
        if(ManageCustomer.cExist(cid)){
        Sale sale = new Sale(getNextSaleId(),cid,dateCreation,"outstanding");
        sales.add(sale);
        addSaleItem(sale);
        saleMenu(sale);
        }
        else{
        } 
    }

    public void addSaleItem(Sale sale)
    {
        System.out.print("Item Id: ");
        String line = scanner.nextLine().trim();
        int iid = Integer.parseInt(line);
        if(ManageItems.iExist(iid)){
            Item item = ManageItems.getItem(iid);
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: " + item.getPrice());
            System.out.print("Quantity: ");
            line = scanner.nextLine().trim();
            int qty = Integer.parseInt(line);
            if(item.getQty() - qty < 0)
            {
                System.out.print("Invalid Quantity !"); 
            }
            else{
                double subTotal = qty * item.getPrice();
                System.out.println("Sub-Total: " + subTotal);
                SaleLineItem lineItem = new SaleLineItem(getNextSaleLineId(), sale.getSalesId(), item.getItemID(), item.getDescription(), qty, subTotal);
                saleLineItems.add(lineItem);
                sale.setTotal(sale.getTotal() + subTotal);
            }
        }
        else{
            System.out.print("Invalid ItemId !"); 
        }
    }

    public void endSale(Sale sale){
        Customer customer = ManageCustomer.getCustomer(sale.getCustomerId());
        System.out.println("SaleId: " +sale.getSalesId() +"\t\t\t\t\t" + "Customer Id: " + sale.getCustomerId());
        System.out.println("Sales Date: " +sale.getDate() +"\t\t\t\t" + "Name: " + customer.getName());
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Item ID\t\tDescription\tQuantity\tAmount");
        System.out.println("------------------------------------------------------------------");
        for(SaleLineItem s : saleLineItems)
        {
            if(s.getSaleId() == sale.getSalesId())
            {
                s.print();
            }

        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t\t\t\t\tTotal Sales: Rs." + sale.getTotal());
        System.out.println("------------------------------------------------------------------");
        if(sale.getTotal() > customer.getSalesLimit())
        {
            sales.remove(sale);
            for(SaleLineItem s : saleLineItems)
            {
             if(sale.getSalesId() == s.getSaleId())
             {
                saleLineItems.remove(s);
             }   
            }
            System.out.println("Sale not Saved: Limit Exceeded :/");
        }
        else{
            ManageCustomer.getCustomer(sale.getCustomerId()).setPayableAmount(ManageCustomer.getCustomer(sale.getCustomerId()).getPayableAmount()+sale.getTotal());
        }

        return;
    }
    public void removeSaleItem(int saleId){
        System.out.print("Item Id: ");
        String line = scanner.nextLine().trim();
        int iid = Integer.parseInt(line);
        for(SaleLineItem s : saleLineItems)
        {
            if(s.getSaleId() == saleId && s.getitemId() == iid){
             saleLineItems.remove(s);
             System.out.print("Item removed successfully :)) ");
             return;
            }
        }
        System.out.print("Item not found :/ ");
    }
    static public boolean sExist(int sid){
        for(Sale s : sales)
        {
            if(s.getSalesId() == sid)
            {
                return true;
            }
        }
        return false;
    }
    public static Sale getSale(int sid)
    {
        for(Sale s : sales)
        {
            if(s.getSalesId() == sid)
            {
                return s;
            }
        }
        return new Sale(-1,-1,"null","null");
    } 
    public void loadSales() {
        try (BufferedReader br = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                int salesId = Integer.parseInt(tokens[0]);
                int customerId = Integer.parseInt(tokens[1]);
                String date = tokens[2];
                String status = tokens[3];
                Sale s = new Sale(salesId, customerId, date, status);
                sales.add(s);
            }
            setNextSaleId(sales.get(sales.size() - 1).getSalesId());
            br.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    
    public void saveSales() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("sales.txt"))) {
            for (Sale s : sales) {
                pw.println(s);
            }
            pw.flush();
            pw.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    
    public void loadSaleLineItems() {
        try (BufferedReader br = new BufferedReader(new FileReader("saleLineItems.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                int lineId = Integer.parseInt(tokens[0]);
                int salesId = Integer.parseInt(tokens[1]);
                int itemId = Integer.parseInt(tokens[2]);
                int quantity = Integer.parseInt(tokens[3]);
                double amount = Double.parseDouble(tokens[4]);
                String description = ManageItems.getItem(itemId).getDescription();
                SaleLineItem s = new SaleLineItem(lineId, salesId, itemId, description, quantity, amount);
                saleLineItems.add(s);
            }
            setNextSaleLineId(saleLineItems.get(saleLineItems.size() - 1).getLineId());
            br.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    
    public void saveSaleLineItems() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("saleLineItems.txt"))) {
            for (SaleLineItem s : saleLineItems) {
                pw.println(s);
            }
            pw.flush();
            pw.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void saleMenu(Sale currentSale){
        int choice;

    while (true) {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Enter New Item");
        System.out.println("2. End Sale");
        System.out.println("3. Remove an existing Item from the current sale");
        System.out.println("4. Cancel Sale");

        try {
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addSaleItem(currentSale);
                    break;
                case 2:
                    endSale(currentSale);
                    System.out.println("Press any key to continue.....");
                    scanner.nextLine();
                    return;
                case 3:
                    removeSaleItem(currentSale.getSalesId());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number from 1 to 4.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number x.");
        }
    }
}

}
