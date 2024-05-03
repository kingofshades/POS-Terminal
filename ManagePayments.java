import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ManagePayments {
    static ArrayList<Receipt> receipts;
    Scanner scanner;
    private static int receiptId;
    ManagePayments(){
        receipts = new ArrayList<>();
        receiptId = 0;
        scanner = new Scanner(System.in);
        loadPayments();
    }
 
    public static int getNextReceiptId() {
        return ++receiptId;
    }
    public static void setNextReceiptId(int id) {
        receiptId = id;
    }   

    public void managePayments(){
        System.out.print("Sale ID: ");
        String line = scanner.nextLine();
        int sid = Integer.parseInt(line);
        if(ManageSales.sExist(sid))
        {
            Sale sale = ManageSales.getSale(sid);
            Customer customer = ManageCustomer.getCustomer(sale.getCustomerId());
            System.out.println("Customer Name: "+ customer.getName());
            System.out.println("Total Sales Amount: "+ sale.getTotal());
            double amountPaid = 0;
            for(Receipt r : receipts)
            {
                if(r.getsalesId() == sale.getSalesId()){
                    amountPaid += r.getamountPaid();
                }
            }
            System.out.println("Amount Paid: "+ amountPaid);
            double remainingAmount = sale.getTotal() - amountPaid;  
            System.out.println("Remaing Amount: "+ remainingAmount);
            
            System.out.print("Amount to be Paid: ");
            line = scanner.nextLine();
            double amountToBePaid = Integer.parseInt(line);
            if(remainingAmount - amountToBePaid < 0 || remainingAmount - amountToBePaid > remainingAmount )
            {
                System.out.println("Payment Failed: invalid amount :( ");
                return;
            }
            else{
                remainingAmount = remainingAmount - amountToBePaid;
                Receipt receipt = new Receipt(getNextReceiptId(), sale.getSalesId(), amountToBePaid);
                receipts.add(receipt);
                customer.setPayableAmount(customer.getPayableAmount()-amountPaid);  
                if(remainingAmount == 0)
                {
                    sale.setStatus("Paid");
                }
                savePayments();
                System.out.println("Payment Recorded: Press any key to continue.... ");
                scanner.nextLine();
                return;
            }
            
            
        
        }
        else{
            System.out.println("Sale doesn't Exist :(");
            return;
        }

    }


    public void loadPayments() {
    try (BufferedReader br = new BufferedReader(new FileReader("receipts.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(";");
            int receiptId = Integer.parseInt(tokens[0]);
            int salesId = Integer.parseInt(tokens[1]);
            double amountPaid = Double.parseDouble(tokens[2]);
            Receipt receipt = new Receipt(receiptId, salesId, amountPaid);
            receipts.add(receipt);
        }
        br.close();
    } catch (IOException ioEx) {
        ioEx.printStackTrace();
    }
}

public void savePayments() {
    try (PrintWriter pw = new PrintWriter(new FileWriter("receipts.txt"))) {
        for (Receipt receipt : receipts) {
            pw.println(receipt);
        }
        pw.flush();
        pw.close();
    } catch (IOException ioEx) {
        ioEx.printStackTrace();
    }
}
}
