import java.util.Scanner;

public class ManageReports {
    Scanner scanner;
    String line;
    public ManageReports()
    {
        scanner = new Scanner(System.in);
    }

    public void stockInHand(){
/*         int start,end;
        System.out.print("Starting Item ID: ");
        line = scanner.nextLine();
        start = Integer.parseInt(line);
        System.out.print("Ending Item ID: ");
        line = scanner.nextLine();
        end = Integer.parseInt(line); */
        System.out.println("------------------------------------------------------------------");
        System.out.println("Item ID\t\tDescription\t\tPrice\t\tQuantity in Hand");
        System.out.println("------------------------------------------------------------------");
        for(Item i : ManageItems.items)
        {
            /* if(i.getItemID()>= start || i.getItemID() <= end)
            {
                i.print();
            } */
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
            System.out.println("Balance: " + customer.getPayableAmount());
        }
        else{
            System.out.println("Customer does not exist :(");
            return;
        }
    }
    
    public void salesReport(){
        System.out.print("Enter Sales ID: ");
        line = scanner.nextLine();
        int sid = Integer.parseInt(line);
        if(ManageSales.sExist(sid)){
            Sale sale = ManageSales.getSale(sid);
            System.out.println("Sale Date: " + sale.getDate());
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Item ID\t\tDescription\tQuantity Sold\tAmount");
            System.out.println("------------------------------------------------------------------");
            for(SaleLineItem sli : ManageSales.saleLineItems)
            {
                if(sli.getSaleId() == sale.getSalesId())
                {
                    sli.print();
                }
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("\t\t\t\t\tTotal Sales: Rs." + sale.getTotal());
            System.out.println("------------------------------------------------------------------");
            return;
        }
        else{
            System.out.println("Sale does not exist :(");
            return;
        }
    }

    public void outstandingSaleReport(){
        System.out.print("Enter Date (dd/mm/yyyy):");
        line = scanner.nextLine().trim();
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Sale ID\t\tCustomer Name\tTotal Amount\tRemaining Amount");
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
                    if(r.getsalesId() == s.getSalesId())
                    {
                        amountpaid += r.getamountPaid();
                    }
                }
                remainingAmount = s.getTotal() - amountpaid;
                System.out.println(s.getSalesId()+"\t\t"+customer.getName()+"\t\t"+s.getTotal()+"\t\t"+remainingAmount);
                totalRemaining += remainingAmount;
            }
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t\t\t\t\tTotal Remaining Amount: Rs." + totalRemaining );
        System.out.println("------------------------------------------------------------------");
        return;
        
    }

    public void manageReportsMenu(){
        int choice;
    
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Stock in Hand");
            System.out.println("2. Customer Balance");
            System.out.println("3. Sales Report (by date)");
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
                        System.out.println("Invalid input. Please enter a number from 1 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

    }
    
}
