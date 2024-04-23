public class Customer {
    private int customerID;
    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private double salesLimit;
    private double payableAmount;

    public Customer(int customerID, String name, String address, String phoneNo, String email, double salesLimit) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.salesLimit = salesLimit;
        this.payableAmount = 0;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalesLimit() {
        return salesLimit;
    }

    public void setSalesLimit(double salesLimit) {
        this.salesLimit = salesLimit;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public void print() {
        System.out.println(customerID + "\t\t" + name + "\t\t" + email + "\t\t" + phoneNo + "\t\t" + salesLimit);
    }

    @Override
    public String toString() {
        return customerID + ";" + name + ";" + address + ";" + phoneNo + ";" + email + ";" + payableAmount + ";"
                + salesLimit;
    }
}
