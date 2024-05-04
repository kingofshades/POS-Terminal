import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class ManageReports {
    Scanner scanner;
    String line;
    
    public ManageReports()
    {
        scanner = new Scanner(System.in);
    }

    public void stockInHand(){
        LocalDate date = LocalDate.now();
        System.out.println("\nDate: " + date);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-8s %-25s %-12s %-8s%n", "Item ID", "Description", "Price", "Quantity");
        System.out.println("------------------------------------------------------------------");
        for(Item i : ManageItems.items)
        {
            i.print();
        }
    }

    public void customerBalance(){
        System.out.print("Enter Customer ID: ");
        line = scanner.nextLine();
        int cid = Integer.parseInt(line);
        if(ManageCustomer.cExist(cid)){
            Customer customer = ManageCustomer.getCustomer(cid);
            
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Phone: " + customer.getPhoneNo());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Balance: -" + customer.getPayableAmount());
        }
        else{
            System.out.println("\nCustomer does not exist :(");
            return;
        }
    }
    
    public void salesReport(){
        System.out.print("Enter Sales ID: ");
        line = scanner.nextLine();
        int sid = Integer.parseInt(line);
        if(ManageSales.sExist(sid)){
            Sale sale = ManageSales.getSale(sid);
            System.out.println("\nSale Date: " + sale.getDate());
            System.out.println("\n------------------------------------------------------------------");
            System.out.printf("%-8s %-20s %-13s %-12s%n", "Item ID", "Description", "Qty Sold", "Amount");
            System.out.println("------------------------------------------------------------------");
            for(SaleLineItem sli : ManageSales.saleLineItems)
            {
                if(sli.getSaleId() == sale.getSalesId())
                {
                    sli.print();
                }
            }
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-35s %-50s%n"," ", "Total Sales: Rs." + sale.getTotal());
            System.out.println("------------------------------------------------------------------");
            return;
        }
        else{
            System.out.println("\nSale does not exist :(");
            return;
        }
    }

    public void outstandingSaleReport(){
        System.out.print("Enter Date (dd/mm/yyyy):");
        line = scanner.nextLine().trim();
        System.out.println("\n------------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-16s %-16s%n", "Sale ID", "Customer Name", "Total Amount", "Remaining Amount");
        System.out.println("------------------------------------------------------------------");
        double totalRemaining = 0;
        for(Sale s : ManageSales.sales)
        {
            if(s.getDate().equals(line) && s.getStatus().equals("outstanding"))
            {
                Customer customer = ManageCustomer.getCustomer(s.getCustomerId());
                double amountpaid = 0;
                double remainingAmount = 0;
                for(Receipt r : ManagePayments.receipts)
                {
                    if(r.getSalesId() == s.getSalesId())
                    {
                        amountpaid += r.getAmountPaid();
                    }
                }
                remainingAmount = s.getTotal() - amountpaid;
                System.out.printf("%-8d %-20s %-16.2f %-16.2f%n", s.getSalesId(), customer.getName(), s.getTotal(), remainingAmount);
                totalRemaining += remainingAmount;
            }
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%50s Rs.%.2f%n", "Total Remaining Amount:", totalRemaining);
        System.out.println("------------------------------------------------------------------");
        return;
        
    }

    public void manageReportsMenu(){
        int choice;
    
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Stock in Hand");
            System.out.println("2. Customer Balance");
            System.out.println("3. Sales Report");
            System.out.println("4. Outstandinding Sales (by Date)");
            System.out.println("5. Back to Main Menu");
    
            try {
                choice = Integer.parseInt(scanner.nextLine());
    
                switch (choice) {
                    case 1:
                        stockInHand();
                        break;
                    case 2:
                        customerBalance();
                        break;
    
                    case 3:
                        salesReport();
                        break;
    
                    case 4:
                        outstandingSaleReport();
                        break;
                    case 5:
                        return;
                    default:    
                        System.out.println("\nInvalid input. Please enter a number from 1 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
            }
        }

    }
    
}
