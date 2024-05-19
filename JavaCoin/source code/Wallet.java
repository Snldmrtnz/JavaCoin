import java.util.UUID;

public class Wallet {
    private String id;
    private double balance;

    public Wallet() {
        this.id = generateRandomId();
        this.balance = 0.0;
    }
    public Wallet(String id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public Wallet(double initialBalance) {
        this.id = generateRandomId();
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    private String generateRandomId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 6);
    }
}
