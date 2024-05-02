public class Receipt {
    private int receiptId, salesId;
    private double amountPaid;

    public Receipt(int receiptId, int salesId, double amountPaid) {
        this.receiptId = receiptId;
        this.salesId = salesId;
        this.amountPaid = amountPaid;
    }

    public int getreceiptId() {
        return receiptId;
    }

    public void setreceiptId(int receiptId) {
       this.receiptId = receiptId;
    }

    public int getsalesId() {
        return salesId;
    }

    public void setsalesId(int salesId) {
        this.salesId = salesId;
    }


    public double getamountPaid() {
        return amountPaid;
    }

    public void setamountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
    public void print() {
        System.out.println(receiptId + "\t\t" + salesId + "\t\t" + amountPaid);
    }
    
    @Override
    public String toString() {
        return receiptId + ";" + salesId + ";" + amountPaid;
    }

}
