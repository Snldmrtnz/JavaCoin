import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import CommonlyUsedClasses.*;

public class GUI extends JFrame{
    int width = 1180, height = 700; 

    GJLabel projectTitle, balanceLabel, balance;
    JPanel titlePanel, contentPanel;

    File saveFile = new File("account.txt");

    ////////////////////////////
    JPanel leftButtonPanel;
    GJButton accountButton, depositButton, withdrawButton, saveButton, transferButton;
    //////////////////////////
    JPanel rightButtonPanel;
    JPanel amountPanel;
    GJLabel amountLabel;
    JTextField amountField;
    double amountDeposited, amountWithdrawn, amountTransferred;
    GJButton transferToWalletIndexIncrementButton, transferToWalletIndexDecrementButton;
    GJButton currentWalletIndexDecrementButton, currentWalletIndexIncrementButton;
    GJLabel TransferToWalletIndexLabel, currentWalletIndexLabel, walletNumber;

    GJLabel walletNumberLabel;
    GJLabel walletNumberTransferLabel;
    int currentWalletIndex = 0;
    int TransferToWalletIndex = 0;

    /////////////////////////////
    JPanel contentPanelExtra;
    JPanel transferBalancePanel, currentBalanceAndWalletPanel;
    GJLabel transferWalletNumber, transferWalletNumberLabel, balanceToTransfer, balanceToTransferLabel;
    double transferBalance;

    /////////////////////////////

    JPanel currentWalletJPanel, transferPanel;

    //Label Variables
    Font labelFont = new Font("Consolas", Font.BOLD, 25);
    Color labelBackground = new Color(0x2C2A32);
    Color labelForeground = Color.WHITE;
    //

    // Button Variables
    Color buttonBackground = null;
    Color buttonForeground = Color.WHITE;
    Font buttonFont = new Font("Consolas", Font.PLAIN, 20);
    //

    public int incrementNumber(int number, List<Wallet> list) {
        int size = list.size();
        return (number + 1) % size;
    }

    public int decrementNumber(int number, List<Wallet> list) {
        int size = list.size();
        return (number - 1 + size) % size;
    }

    //CONSTRUCTOR FOR GUI INITIALIZATION//
    GUI(){
        Account b = new Account("123456");
        b.addWallet();

        Account curr = new Account("000000");
        curr.loadFromFile(saveFile);

        // * SETS LAYOUT * //

        this.pack();
        this.setSize(width, height);
        this.setTitle("JavaCoin");
        this.setBackground(new Color(0x2C2A32));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(new BorderLayout());

        // * Top most part of the app
        projectTitle = new GJLabel("JavaCoin", labelFont, labelBackground, labelForeground);
        projectTitle.setFont(new Font("Consolas", Font.BOLD, 40));
        projectTitle.setHorizontalAlignment(JLabel.LEFT);
        projectTitle.setVerticalAlignment(JLabel.CENTER);
        projectTitle.setForeground(Color.white);

        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0x1a1e24)); //#1a1e24 , 99AAB5
        titlePanel.setBounds(0, 0, 1000, 150);
        titlePanel.setVisible(true);
        titlePanel.setOpaque(true);
        titlePanel.add(projectTitle, JLabel.LEFT_ALIGNMENT);

        balance = new GJLabel("Balance: ", labelFont, labelBackground, labelForeground);
        balance.setBorder(new EmptyBorder(5, 15, 5, 0));
        balance.setHorizontalAlignment(JLabel.CENTER);
        balance.setVerticalAlignment(JLabel.CENTER);

        balanceLabel = new GJLabel("0", labelFont, labelBackground, labelForeground);
        double num = curr.getWallets().get(currentWalletIndex).getBalance();
        balanceLabel.setText("$" + Double.toString(num));
        balanceLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        balanceLabel.setHorizontalAlignment(JLabel.LEFT);
        balanceLabel.setVerticalAlignment(JLabel.CENTER);

        walletNumber = new GJLabel("Current Wallet No: ", labelFont, labelBackground, labelForeground);
        walletNumber.setBorder(new EmptyBorder(5, 15, 5, 15));
        walletNumber.setHorizontalAlignment(JLabel.LEFT);
        walletNumber.setVerticalAlignment(JLabel.CENTER);

        walletNumberLabel = new GJLabel("0", labelFont, labelBackground, labelForeground);
        walletNumberLabel.setText(Integer.toString(currentWalletIndex));
        walletNumberLabel.setBorder(new EmptyBorder(5, 15, 5, 15));
        walletNumberLabel.setHorizontalAlignment(JLabel.LEFT);
        walletNumberLabel.setVerticalAlignment(JLabel.CENTER);

        /////////////////CONTENT PANEL///////////////////
        
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x2C2A32));
        contentPanel.setLayout(new GridLayout(3, 3, 1, 1));
        contentPanel.setVisible(true);
        contentPanel.setOpaque(true);

        contentPanelExtra = new JPanel(new GridLayout(1, 2));
        contentPanelExtra.setBackground(labelBackground);
        currentBalanceAndWalletPanel = new JPanel(new GridBagLayout()); //current Balance and wallet Panel
        GridBagConstraints cgb = new GridBagConstraints();

        cgb.gridx = 2;
        cgb.gridy = 0;
        currentBalanceAndWalletPanel.add(walletNumber, cgb);

        cgb.gridx = 2;
        cgb.gridy = 1;
        currentBalanceAndWalletPanel.add(walletNumberLabel, cgb);

        cgb.gridx = 2;
        cgb.gridy = 3;
        currentBalanceAndWalletPanel.add(balance, cgb);

        cgb.gridx = 2;
        cgb.gridy = 4;
        currentBalanceAndWalletPanel.add(balanceLabel, cgb);
        currentBalanceAndWalletPanel.setBackground(null);
        
        transferBalancePanel = new JPanel(new GridBagLayout()); //current Transfer
        transferWalletNumber = new GJLabel("Transfer to:", labelFont, labelBackground, labelForeground);
        transferWalletNumberLabel = new GJLabel("0", labelFont, labelBackground, labelForeground);
        //transferWalletNumberLabel.setText(Integer.toString(TransferToWalletIndex));
        balanceToTransfer = new GJLabel("Balance: ", labelFont, labelBackground, labelForeground);
        balanceToTransferLabel = new GJLabel("0", labelFont, labelBackground, labelForeground);
        transferBalance = curr.getWallets().get(currentWalletIndex).getBalance();
        balanceToTransferLabel.setText("$" + Double.toString(transferBalance));
        balanceToTransferLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        cgb.gridx = 2;
        cgb.gridy = 0;
        transferBalancePanel.add(transferWalletNumber,cgb);

        cgb.gridx = 2;
        cgb.gridy = 1;
        transferBalancePanel.add(transferWalletNumberLabel,cgb);

        cgb.gridx = 2;
        cgb.gridy = 3;
        transferBalancePanel.add(balanceToTransfer,cgb);

        cgb.gridx = 2;
        cgb.gridy = 4;
        transferBalancePanel.add(balanceToTransferLabel,cgb);
        transferBalancePanel.setBackground(null);


        contentPanelExtra.add(currentBalanceAndWalletPanel);
        contentPanelExtra.add(transferBalancePanel);

        cgb.gridx = 0;
        cgb.gridy = 3;
        contentPanel.add(contentPanelExtra, cgb);

        /////////////////BUTTONS AREA////////////////////////

        Color buttonColor = new Color(52, 152, 219); // Example color

        int borderRadius = 30; 
        Border border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(buttonColor, 3), 
            BorderFactory.createEmptyBorder(25, 30, 25, 30) 
        );

        accountButton = new GJButton("Account ", buttonBackground, buttonForeground, buttonFont, "Account Options", 300, 100);
        accountButton.setBackground(buttonColor);
        accountButton.setBorder(border);
        accountButton.setToolTipText("Click here to deposit funds into your current wallet.");

        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = {"Add Wallet", "Remove Wallet", "Sign Out"};
                int choice = JOptionPane.showOptionDialog(null, "What do you want to do?", "Account",  JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
                if(choice == 0) {
                    curr.addWallet();
                    curr.saveToFile(saveFile);
                    JOptionPane.showMessageDialog(null, "Wallet Added");
                }else if(choice == 1){
                    String input = JOptionPane.showInputDialog("Remove Wallet");

                    curr.removeWallet(Integer.parseInt(input));
                    curr.saveToFile(saveFile);

                }else {
                    int choice2 = JOptionPane.showOptionDialog(null, "Have you saved your Wallets?", null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 1);
                    if(choice2 == 0){
                        dispose();
                        new LoginUI();
                        JOptionPane.showMessageDialog(null, "Signed Out");
                    }
                }
            }
        });
           

        depositButton = new GJButton("Deposit ", null, Color.WHITE, buttonFont, "Deposit your funds here", 300, 100);
        depositButton.setBackground(buttonColor);
        depositButton.setBorder(border);
        depositButton.setToolTipText("Click here to deposit funds into your current wallet.");

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    amountDeposited = Double.parseDouble(amountField.getText().replace("$", ""));
                    if (amountDeposited <= 0) {
                        JOptionPane.showMessageDialog(null, "Amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                   
                    curr.getWallets().get(currentWalletIndex).deposit(amountDeposited);
                    double balance = curr.getWallets().get(currentWalletIndex).getBalance();
                    balanceLabel.setText("$" + Double.toString(balance));
        
                    JOptionPane.showMessageDialog(null, "Deposited " + amountDeposited + ".", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        withdrawButton = new GJButton("Withdraw", null, Color.WHITE, buttonFont, "Withdraw your funds here", 300, 100);
        withdrawButton.setBackground(buttonColor);
        withdrawButton.setBorder(border);
        withdrawButton.setToolTipText("Click here to withdraw funds into your current wallet.");

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    amountWithdrawn = Double.parseDouble(amountField.getText());
                    if (amountWithdrawn < 0) {
                        JOptionPane.showMessageDialog(null, "Amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (amountWithdrawn > curr.getWallets().get(currentWalletIndex).getBalance()) {
                        JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    curr.getWallets().get(currentWalletIndex).withdraw(amountWithdrawn);
                    double balance = curr.getWallets().get(currentWalletIndex).getBalance();
                    balanceLabel.setText("$" + Double.toString(balance));
                    JOptionPane.showMessageDialog(null, "Withdrawn " + amountWithdrawn + ".", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        


        transferButton = new GJButton("Transfer",null,Color.WHITE,buttonFont, "Transfer your funds here", 300, 100);
        transferButton.setBackground(buttonColor);
        transferButton.setBorder(border);
        transferButton.setToolTipText("Click here to transfer funds into another wallet.");

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    amountTransferred = Double.parseDouble(amountField.getText());

                    if (amountTransferred < 0) {
                        JOptionPane.showMessageDialog(null, "Amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (amountTransferred > curr.getWallets().get(currentWalletIndex).getBalance()) {
                        JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    curr.getWallets().get(currentWalletIndex).withdraw(amountTransferred);
                    curr.getWallets().get(TransferToWalletIndex).deposit(amountTransferred);
                    double balance = curr.getWallets().get(currentWalletIndex).getBalance();
                    balanceLabel.setText("$" + Double.toString(balance));


                    balance = curr.getWallets().get(TransferToWalletIndex).getBalance();
                    balanceToTransferLabel.setText("$" + Double.toString(balance));


                    JOptionPane.showMessageDialog(null, "Withdrawn " + amountTransferred + ".", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveButton = new GJButton("Save    ",buttonBackground, Color.WHITE, buttonFont, "Save your progress here", 300, 100);
        saveButton.setBackground(buttonColor);
        saveButton.setBorder(border);
        saveButton.setToolTipText("Click here to save.");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curr.saveToFile(saveFile);
                JOptionPane.showMessageDialog(null, "Saved", "Message", 0);
            }
        });

        currentWalletIndexIncrementButton = new GJButton(">",buttonBackground, buttonForeground, buttonFont, "Next Wallet", 5, 5);
        currentWalletIndexIncrementButton.setBackground(buttonColor);
        currentWalletIndexIncrementButton.setBorder(border);
        currentWalletIndexIncrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentWalletIndex = incrementNumber(currentWalletIndex, curr.getWallets());
                currentWalletIndexLabel.setText(Integer.toString(currentWalletIndex));
                walletNumberLabel.setText(Integer.toString(currentWalletIndex));
                double balance = curr.getWallets().get(currentWalletIndex).getBalance();
                balanceLabel.setText("$" + Double.toString(balance));


            }
        });
        currentWalletIndexDecrementButton = new GJButton("<",buttonBackground, buttonForeground, buttonFont, "Previous Wallet", 5, 5);
        currentWalletIndexDecrementButton.setBackground(buttonColor);
        currentWalletIndexDecrementButton.setBorder(border);
        currentWalletIndexDecrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                currentWalletIndex = decrementNumber(currentWalletIndex, curr.getWallets());
                currentWalletIndexLabel.setText(Integer.toString(currentWalletIndex));
                walletNumberLabel.setText(Integer.toString(currentWalletIndex));
                double balance = curr.getWallets().get(currentWalletIndex).getBalance();
                balanceLabel.setText("$" + Double.toString(balance));
            }
        });

        transferToWalletIndexIncrementButton = new GJButton(">", buttonBackground, buttonForeground, buttonFont, "Next Wallet", 5, 5);
        transferToWalletIndexIncrementButton.setBackground(buttonColor);
        transferToWalletIndexIncrementButton.setBorder(border);

        transferToWalletIndexIncrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransferToWalletIndex = incrementNumber(TransferToWalletIndex, curr.getWallets());
                TransferToWalletIndexLabel.setText(Integer.toString(TransferToWalletIndex));
                transferWalletNumberLabel.setText(Integer.toString(TransferToWalletIndex));

                double balance = curr.getWallets().get(TransferToWalletIndex).getBalance();
                balanceToTransferLabel.setText("$" + Double.toString(balance));
            }
        });
        transferToWalletIndexDecrementButton = new GJButton("<", buttonBackground, buttonForeground, buttonFont, "Previous Wallet", 5, 5);
        transferToWalletIndexDecrementButton.setBackground(buttonColor);
        transferToWalletIndexDecrementButton.setBorder(border);

        transferToWalletIndexDecrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TransferToWalletIndex = decrementNumber(TransferToWalletIndex, curr.getWallets());
                TransferToWalletIndexLabel.setText(Integer.toString(TransferToWalletIndex));
                transferWalletNumberLabel.setText(Integer.toString(TransferToWalletIndex));
                
                double balance = curr.getWallets().get(TransferToWalletIndex).getBalance();
                balanceToTransferLabel.setText("$" + Double.toString(balance));
            }
        });

        /////////////////BUTTONS AREA END////////////////////////

        currentWalletJPanel = new JPanel(new GridBagLayout());
        currentWalletJPanel.setBackground(labelBackground);

        walletNumberTransferLabel = new GJLabel("Current Wallet No:", new Font("Consolas", Font.CENTER_BASELINE, 20), labelBackground, labelForeground);
        cgb.gridx = 2;
        cgb.gridy = 0;
        currentWalletJPanel.add(walletNumberTransferLabel,cgb);
        currentWalletIndexLabel = new GJLabel(Integer.toString(currentWalletIndex), new Font("Consolas", Font.CENTER_BASELINE, 20), labelBackground, labelForeground);

        cgb.gridx = 1;
        cgb.gridy = 1;
        currentWalletJPanel.add(currentWalletIndexDecrementButton,cgb);
        cgb.gridx = 2;
        cgb.gridy = 1;
        currentWalletJPanel.add(currentWalletIndexLabel,cgb);
        cgb.gridx = 3;
        cgb.gridy = 1;
        currentWalletJPanel.add(currentWalletIndexIncrementButton,cgb);


        transferPanel = new JPanel(new GridBagLayout());
        transferPanel.setBackground(labelBackground);

        walletNumberTransferLabel = new GJLabel("Transfer to Wallet No:", new Font("Calibri", Font.CENTER_BASELINE, 20), labelBackground, labelForeground);
        cgb.gridx = 2;
        cgb.gridy = 0;
        transferPanel.add(walletNumberTransferLabel,cgb);
        TransferToWalletIndexLabel = new GJLabel(Integer.toString(TransferToWalletIndex), new Font("Calibri", Font.CENTER_BASELINE, 20), labelBackground, labelForeground);

        cgb.gridx = 1;
        cgb.gridy = 1;
        transferPanel.add(transferToWalletIndexDecrementButton, cgb);
        cgb.gridx = 2;
        cgb.gridy = 1;
        transferPanel.add(TransferToWalletIndexLabel,cgb);
        cgb.gridx = 3;
        cgb.gridy = 1;
        transferPanel.add(transferToWalletIndexIncrementButton, cgb);


        amountPanel = new JPanel(new GridBagLayout());
        amountPanel.setBackground(labelBackground);

        amountLabel = new GJLabel("Amount", new Font("Calibri", Font.PLAIN, 30), labelBackground, labelForeground);
        cgb.gridx = 2;
        cgb.gridy = 0;
        amountPanel.add(amountLabel,cgb);

        amountField = new JTextField(10);
        cgb.gridx = 2;
        cgb.gridy = 3;
        amountPanel.add(amountField,cgb);

        leftButtonPanel = new JPanel();
        leftButtonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x99AAB5)));
        leftButtonPanel.setBackground(Color.DARK_GRAY);
        leftButtonPanel.setVisible(true);
        leftButtonPanel.setOpaque(true);

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(30));
        box.add(accountButton);
        box.add(Box.createVerticalStrut(30));
        box.add(depositButton);
        box.add(Box.createVerticalStrut(30));
        box.add(withdrawButton);
        box.add(Box.createVerticalStrut(30));
        box.add(saveButton);
        box.add(Box.createVerticalStrut(30));
        box.add(transferButton);


        leftButtonPanel.add(box);

        rightButtonPanel = new JPanel();
        rightButtonPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x99AAB5)));

        rightButtonPanel.setBackground(Color.DARK_GRAY);
        rightButtonPanel.setVisible(true);
        rightButtonPanel.setOpaque(true);

        Box box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalStrut(30));
        box2.add(currentWalletJPanel);
        box2.add(Box.createVerticalStrut(30));
        box2.add(transferPanel);
        box2.add(Box.createVerticalStrut(30));
        box2.add(amountPanel);

        rightButtonPanel.add(box2);


        // * ADDS TO FRAME *//
        this.getContentPane().add(titlePanel, BorderLayout.NORTH);
        this.getContentPane().add(leftButtonPanel, BorderLayout.WEST);
        this.getContentPane().add(rightButtonPanel, BorderLayout.EAST);
        this.getContentPane().add(contentPanel, BorderLayout.CENTER);
        this.setVisible(true);

        
    }
}
