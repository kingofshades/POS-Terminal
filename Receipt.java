public class Receipt {
    private int receiptId, salesId;
    private double amountPaid;

    public Receipt(int receiptId, int salesId, double amountPaid) {
        this.receiptId = receiptId;
        this.salesId = salesId;
        this.amountPaid = amountPaid;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
       this.receiptId = receiptId;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }


    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
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
