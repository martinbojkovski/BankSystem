# BankSystem

## Overview
BankSystem is a Java SpringBoot Restfull application designed to simulate the transactions that are happening inside a bank system.
## Features
- **BankSystem Database**: The database has 3 tables. Account which represents the customers at the bank, Bank which represents the banks and Transaction which represents the transactions by accounts.
- **Transaction Types**: The transactions are split into 3 different types: Transactions between two accounts, Deposit to your account and Withdrawl from your account.

## Installation
Follow these steps to get BankSystem up and running on your local machine:

1. Ensure you have Java, Maven and Docker Desktop installed.
2. Clone this repository to your local machine.
3. Navigate to the project directory and run `mvn spring-boot:run` to start the application.
4. Run the docker-compose.yaml file so the postgresql database will create as a container in Docker and the database will be available.
5. The application will be available on `localhost:8080`.

## Usage
1. Open your Postman(or another app for testing)
2. To List the tables: <br>
   **Bank**: GET: [http://localhost:8080/api/banks](http://localhost:8080/api/banks) <br>
   **Account**: GET: [http://localhost:8080/api/accounts](http://localhost:8080/api/banks) <br>
   **Transactions**: GET: [http://localhost:8080/api/transactions](http://localhost:8080/api/transactions)
3. Firstly to add a bank use the POST: [http://localhost:8080/api/banks/add](http://localhost:8080/api/banks/add)   <br>
   The json file should look like in this example:
   ```json 
   {
        "name": "NLB Tutunska Banka",
        "bankCustomers": [],
        "totalTransactionFeeAmount": 0.0,
        "totalTransferAmount": 0.0,
        "percentFeeAmount": 12, ---> Here you can enter the percentage fee that you like (12 means 12%).
        "flatFeeAmount": 35  ---> Here you can enter the flatFee amount in $.
    }
4. After adding a bank, to add some customers use the POST: [http://localhost:8080/api/accounts/add](http://localhost:8080/api/accounts/add)   <br>
   The json file should look like in this example:
   ```json
   {
        "username": "Martin",
        "totalBalance": 223.23,
        "bank": 1  ---> This is the id of the bank that we have created
    }
5. And Lastly to do a transactions use the POST: [http://localhost:8080/api/transactions/add](http://localhost:8080/api/transactions/add) <br>
   **For Transfer**: The json file should look like this example:
     ```json
     {
          "amount": 250.0,
          "originatingAccountId": 1,
          "resultingAccountId": 2, ---> if is DEPOSIT OR WITHDRAW needs to be the same as originatingAccountId.
          "reason": "Gift",
          "type": "TRANSACTION"  ---> if can change here for DEPOSIT or WITHDRAW (Needs to be in all Capital letters).
      }
6. For Different Filtering: <br>
   **List transactions for any account**: GET: [http://localhost:8080/api/transactions/{id}](http://localhost:8080/api/transactions/{id}) ---> This shows the transactions where the account with the {id} is a sender or reciever. <br>
   **Check account balance**": GET: [http://localhost:8080/api/accounts/balance/{id}](http://localhost:8080/api/accounts/balance/{id}) <br>
   **Check bank total transaction fee amount**: GET: [http://localhost:8080/api/banks/transaction_fees/{id}](http://localhost:8080/api/banks/transaction_fees/{id}) <br>
   **Check bank total transfer amount**: GET: [http://localhost:8080/api/banks/transfer_amount/{id}](http://localhost:8080/api/banks/transfer_amount/{id})



