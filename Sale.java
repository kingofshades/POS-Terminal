public class Sale {
        
 private String status;
 private int salesId,customerId;
 private String date;
 private double total;

public Sale(int salesId, int customerId, String date, String status) {
    this.salesId = salesId;
    this.customerId = customerId;
    this.date = date;
    this.status = status;
}

public int getSalesId() {
    return salesId;
}


public int getCustomerId() {
    return customerId;
}

public void setCustomerId(int customerId) {
    this.customerId = customerId;
}

public String getDate() {
    return date;
}

public void setDate(String date) {
    this.date = date;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
public void setTotal(double total){
    this.total = total;
}
public double getTotal(){
    return total;
}

public void print() {
    System.out.println(salesId + "\t\t" + customerId + "\t\t" + total);
}

@Override
public String toString() {
    return salesId + ";" + customerId + ";" + date + ";" + status;
}
    
}
