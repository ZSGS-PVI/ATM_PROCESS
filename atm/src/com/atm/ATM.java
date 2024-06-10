package com.atm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
	Scanner in=new Scanner(System.in);
	ArrayList<Customer> customer=new ArrayList<>();
	private LoadCash cash;
	

	public static void main(String[] args) {
		
           ATM atm=new ATM();
           atm.init();
	}
	
	public void init() {
		System.out.println("------WELCOME TO DIGITALBANK-------\n");
		
		System.out.println(" 1.Load cash to atm \n 2.customer info \n 3.ATM process \n 4.exit");
		
		System.out.println("Enter the number");
		int choice=  getValidatedChoice();
		
		switch(choice) {
		case 1:
			loadCash();
			break;
			
		case 2:
			viewCustomerinfo();
			break;
		
		case 3:
			atmprocess();
			break;
			
		case 4:
			System.out.println("Thank you Have a nice day!");
			System.exit(0);
			
		 default:
	            System.out.println("Invalid choice! Please try again.");
		}
		
		
		
	}
	
    private int getValidatedChoice() {
        int choice = -1;
        while (true) {
            try {
                choice = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next(); // Clear the invalid input
            }
        }
        return choice;
    }
	
	// Load  amount to the ATM
	
	private void loadCash() {
		int loadAmount;
		System.out.println("Enter the Amount to load the ATM:");
		loadAmount=in.nextInt();
		 cash=new LoadCash(loadAmount);
		cash.load();
		init();
		
	}

	// view customer information
	
	private void viewCustomerinfo() {
		
		if(customer.isEmpty()) {
			addCustomerInfo();
		}
		System.out.println("---------------------------------------------------");
		System.out.printf("%-10s %-15s %-10s %-15s\n", "Acc No", "Account Holder", "Pin Number", "Account Balance");
		System.out.println("---------------------------------------------------");
		
		for(Customer customers:customer) {
			System.out.printf("%-10d %-15s %-10d %-10s₹\n",customers.getAccountNumber(),customers.getAccountHolder(),customers.getPinNumber(),customers.getAccountBalance());
		}
		System.out.println("---------------------------------------------------\n");
		init();
	}
		
		private void addCustomerInfo() {
		
	customer.add(new Customer(101,"Suresh",2343,25243));
	customer.add(new Customer(102,"Ganesh",5432,34123));
	customer.add(new Customer(103,"Magesh",7854,26100));
	customer.add(new Customer(104,"Naresh",2345,80000));
	customer.add(new Customer(105,"Harish",1907,103400));
		
	
	}
		
		// ATM process
	
   private void atmprocess() {
	   
	   System.out.println(" 1.Check Balance \n 2.Withdraw Money \n 3.Transfer Money \n 4.Mini Statement \n 5.Exit");
	   System.out.println("Enter the number:");
	   int option=in.nextInt();
	   
	   switch(option) {
	   case 1:
		   checkBalance();
		   break;
	   case 2:
		   withdrawMoney();
		   break;
	   case 3:
		   transferMoney();
		   break;
	   case 4:
		   miniStatement();
		   break;
	   case 5:
		   init();
		
	   default:
           System.out.println("Invalid choice! Please try again.");
	   }
		
		
	}
   
// check the balance for individual person
   private void checkBalance() {
	    int accNo;
	    int pinNo;

	    System.out.println("Enter Your Account Number:");
	    accNo = in.nextInt();
	    
	    boolean found = false;
	    for(Customer c : customer) {
	        if(accNo == c.getAccountNumber()) {
	            System.out.println("Enter Your Pin Number:");
	            pinNo = in.nextInt();
	            
	            if(pinNo == c.getPinNumber()) {
	                System.out.println("Account Balance: "+ c.getAccountBalance() + " ₹");
	            } else {
	                System.out.println("Wrong Pin Entered!!!!");
	            }
	            found = true;
	            init();
	            break;
	        }
	    }
	    if(!found) {
	        System.out.println("Account Not Found!!!!");
	    }
	}
   

 
// withdraw amount
   
private void withdrawMoney() {
	
	  int accNo;
	    int pinNo;
	    String accountamt;

	    System.out.println("Enter Your Account Number:");
	    accNo = in.nextInt();
	    
	    boolean found = false;
	    for(Customer c : customer) {
	        if(accNo == c.getAccountNumber()) {
	            System.out.println("Enter Your Pin Number:");
	            pinNo = in.nextInt();
	            
	            if(pinNo == c.getPinNumber()) {
	            	//call the method to get action
	            	 System.out.println("Your current balance is ₹" + c.getAccountBalance());

	                 int withdrawAmount;
	                 System.out.println("Enter the amount to withdraw:");
	                 withdrawAmount = in.nextInt();
	              // Check for sufficient funds and process withdrawal
	                    if (withdraw(c, withdrawAmount)) {
	                        if (withdrawAmount <= 10000 && withdrawAmount > 100) {
	                            processWithdrawal(c, withdrawAmount);
	                        } else {
	                            System.out.println("Enter a valid amount (more than ₹100 and less than ₹10000).");
	                        }
	                    } else {
	                        System.out.println("Insufficient funds or ATM balance. Please try a lower amount.");
	                    }
	                } else {
	                    System.out.println("Wrong Pin Entered!!!!");
	                }
	                found = true;
	                init();
	                break;
	            }
	        }
	        if (!found) {
	            System.out.println("Account Not Found!!!!");
	        }
	    }

	    private boolean withdraw(Customer c, int withdrawAmount) {
	        return c.getAccountBalance() >= withdrawAmount && cash.getTotalAmount() >= withdrawAmount;
	    }

	   // process the withdrawal  
	   private void processWithdrawal(Customer c, int withdrawAmount) {
	        int balance = c.getAccountBalance();
	        if (withdrawAmount <= 5000) {
	            int num1000 = withdrawAmount >= 1000 ? 1 : 0;
	            int num500 = Math.min(6, (withdrawAmount - num1000 * 1000) / 500);
	            int num100 = Math.min(10, (withdrawAmount - num1000 * 1000 - num500 * 500) / 100);

	            withdrawAmount -= (num1000 * 1000 + num500 * 500 + num100 * 100);

	            if (withdrawAmount == 0) {
	                updateBalances(c, balance, num1000, num500, num100);
	            } else {
	                System.out.println("Cannot dispense the exact amount with available denominations.");
	            }
	        } else {
	            int num1000 = 3;
	            int num500 = Math.min(2, (withdrawAmount - num1000 * 1000) / 500);
	            int num100 = Math.min(10, (withdrawAmount - num1000 * 1000 - num500 * 500) / 100);

	            withdrawAmount -= (num1000 * 1000 + num500 * 500 + num100 * 100);

	            if (withdrawAmount == 0) {
	                updateBalances(c, balance, num1000, num500, num100);
	            } else {
	                System.out.println("Cannot dispense the exact amount with available denominations.");
	            }
	        }
	    }

	    private void updateBalances(Customer c, int balance, int num1000, int num500, int num100) {
	        int withdrawAmount = num1000 * 1000 + num500 * 500 + num100 * 100;
	        c.setAccountBalance(balance - withdrawAmount);
	        cash.setTotalAmount(cash.getTotalAmount() - withdrawAmount);
	        cash.setNum1000(cash.getNum1000() - num1000);
	        cash.setNum1000(cash.getNum1000() - num500);
	        cash.setNum1000(cash.getNum1000() - num100);

	        System.out.println("Withdrawal successful! New balance: ₹" + c.getAccountBalance());
	        
	        int newBalance = c.getAccountBalance();
	        c.addTransaction(new Transaction("Withdrawal", withdrawAmount, newBalance));
	    }
	    

	    private void init1() {
	        // Reinitialize or reset if needed
	    }

	    
	    // transfer money

private void transferMoney() {
	
	 int accNo;
	    int pinNo;

	    System.out.println("Enter Your Account Number:");
	    accNo = in.nextInt();
	    
	    boolean found = false;
	    for(Customer c : customer) {
	        if(accNo == c.getAccountNumber()) {
	            System.out.println("Enter Your Pin Number:");
	            pinNo = in.nextInt();
	            
	            if(pinNo == c.getPinNumber()) {
	                System.out.println("Account Balance: "+ c.getAccountBalance() + " ₹");
	                transferProcess(c);
	            } else {
	                System.out.println("Wrong Pin Entered!!!!");
	            }
	            found = true;
	            init();
	            break;
	        }
	    }
	    if(!found) {
	        System.out.println("Account Not Found!!!!");
	    }
	}

//transfer process

private void transferProcess(Customer sender) {
    int receiverAccNo;
    int transferAmount;

    System.out.println("Enter Receiver Account Number:");
    receiverAccNo = in.nextInt();

    Customer receiver = findCustomerByAccountNumber(receiverAccNo);
    if (receiver == null) {
        System.out.println("Receiver Account Not Found!!!!");
        return;
    }

    System.out.println("Enter the amount to transfer:");
    transferAmount = in.nextInt();

    if (transferAmount <= 10000 && transferAmount > 1000) {
        if (transferCheck(sender, transferAmount)) {
            sender.setAccountBalance(sender.getAccountBalance() - transferAmount);
            receiver.setAccountBalance(receiver.getAccountBalance() + transferAmount);

            sender.addTransaction(new Transaction("Transfer Sent", transferAmount, sender.getAccountBalance()));
            receiver.addTransaction(new Transaction("Transfer Received", transferAmount, receiver.getAccountBalance()));

            System.out.println("Transfer successful! Your new balance: ₹" + sender.getAccountBalance());
        } else {
            System.out.println("Insufficient funds. Please try a lower amount.");
        }
    } else {
        System.out.println("Enter a valid amount (more than ₹1000 and less than ₹10000).");
    }

  
    init();
}

private Customer findCustomerByAccountNumber(int accNo) {
    for (Customer c : customer) {
        if (c.getAccountNumber() == accNo) {
            return c;
        }
    }
    return null;
}

private boolean transferCheck(Customer sender, int transferAmount) {
    return sender.getAccountBalance() >= transferAmount;
}

// miniStatement to display the last transactions list
private void miniStatement() {
	 int accNo;
	    int pinNo;

	    System.out.println("Enter Your Account Number:");
	    accNo = in.nextInt();

	    boolean found = false;
	    for (Customer c : customer) {
	        if (accNo == c.getAccountNumber()) {
	            System.out.println("Enter Your Pin Number:");
	            pinNo = in.nextInt();

	            if (pinNo == c.getPinNumber()) {
	                System.out.println("Mini Statement for Account Number: " + accNo);
	                for (Transaction t : c.getTransactions()) {
	                    System.out.println(t);
	                }
	                System.out.println("Current Balance: " + c.getAccountBalance() + " ₹");
	            } else {
	                System.out.println("Wrong Pin Entered!!!!");
	            }
	            found = true;
	            init();
	            break;
	        }
	    }
	    if (!found) {
	        System.out.println("Account Not Found!!!!");
	    }
	}





}
