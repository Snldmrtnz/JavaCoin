# JavaCoin Wallet Manager

JavaCoin is a simple wallet management system that allows users to deposit, withdraw, transfer funds, and manage multiple wallets. The GUI is built using Swing and provides an intuitive interface for managing wallets and transactions.

## Features

- **Add/Remove Wallets:** Create new wallets or remove existing ones.
- **Deposit Funds:** Deposit funds into the selected wallet.
- **Withdraw Funds:** Withdraw funds from the selected wallet.
- **Transfer Funds:** Transfer funds between different wallets.
- **Save/Load Account:** Save the current state of wallets to a file and load them upon startup.

## Requirements

- Java Development Kit (JDK) 8 or higher
- An IDE like IntelliJ IDEA, Eclipse, or NetBeans (optional, but recommended for easier development and execution)

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/yourusername/JavaCoin.git
cd JavaCoin

javac -d bin -sourcepath src src/GUI.java

java -cp bin GUI
