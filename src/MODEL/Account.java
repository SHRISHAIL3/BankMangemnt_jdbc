package MODEL;

public class Account {
    private int id;
    private String name;
    private String phone;
    private String password;
    private double balance;

    public Account() {}

    public Account(int id, String name, String phone, String password, double balance) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.balance = balance;
    }

    public Account(String name, String phone, String password, double balance) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.balance = balance;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
