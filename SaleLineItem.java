
public class SaleLineItem {
 
    private int lineId, salesId, itemId, quantity;
    private double amount;
    private String description;

    public SaleLineItem(int lineId,int salesId, int itemId,String description, int quantity, double amount) {
        this.lineId = lineId;
        this.salesId = salesId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
        this.description = description;
    }
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getSaleId() {
        return salesId;
    }

    public void setSaleId(int orderId) {
        this.salesId = orderId;
    }

    public int getitemId() {
        return itemId;
    }

    public void setitemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String desc){
        this.description = desc;
    }

    public void print() {
        System.out.println(itemId + "\t\t" + description + "\t\t" + quantity + "\t\t" + amount);
    }

    @Override
    public String toString() {
        return lineId + ";" + salesId + ";" + itemId + ";" + quantity + ";" + amount;
    }
 

}
