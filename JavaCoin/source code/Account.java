import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;


public class Account {
    private final String id;
    private String accountNumber;
    private final List<Wallet> wallets;
    String errorMessage = "Invalid file format: missing account number";

    public Account(String accountNumber) {
        this.id = generateRandomId();
        this.accountNumber = accountNumber;
        this.wallets = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void addWallet() {
        Wallet wallet = new Wallet(0);
        wallets.add(wallet);
    }

    public void removeWallet(int index) {
        if(!(index < 0 || index > wallets.size())){
            wallets.remove(index);
            JOptionPane.showMessageDialog(null, "Wallet Removed");
        }
        else{
            JOptionPane.showMessageDialog(null, "Wallet does not exist", "Error" ,JOptionPane.WARNING_MESSAGE);
        }

    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    private String generateRandomId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 6);
    }

     public void saveToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Account Number: " + accountNumber + "\n");
            writer.write("Wallets:\n");
            for (Wallet wallet : wallets) {
                writer.write("- ID: " + wallet.getId() + ", Balance: " + wallet.getBalance() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public void loadFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String accountNumber = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Account Number: ")) {
                    accountNumber = line.substring("Account Number: ".length());
                } else if (line.equals("Wallets:")) {
                    while ((line = reader.readLine()) != null && line.startsWith("- ID: ")) {
                        String walletId = line.substring("- ID: ".length(), line.indexOf(","));
                        double balance = Double.parseDouble(line.substring(line.indexOf(", Balance: ") + ", Balance: ".length()));
                        Wallet wallet = new Wallet(walletId, balance);
                        wallets.add(wallet);
                    }
                }
            }
            if (accountNumber != null) {
                this.accountNumber = accountNumber;
            } else {
                throw new IllegalArgumentException(errorMessage);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    
}
