class Item {
    private int itemID;
    private String description;
    private double price;
    private int qty;
    private String dateCreation;

    public Item(int itemID, String description, double price, int qty, String dateCreation) {
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.dateCreation = dateCreation;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void print() {
        System.out.println(itemID + "\t\t" + description + "\t\t" + price + "\t\t" + qty);
    }

    @Override
    public String toString() {
        return itemID + ";" + description + ";" + price + ";" + qty + ";" + dateCreation;
    }

}
