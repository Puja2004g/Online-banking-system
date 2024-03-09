import java.util.HashMap;
import java.util.Scanner;

class user {
  String username;
  String password;
  double balance;

  public user(String username, String password) {
    this.username = username;
    this.password = password;
    this.balance = 0;
  }
}

class bankingSystem {
  private HashMap<String, user> map = new HashMap<String, user>();

  public bankingSystem() {
    this.map = new HashMap<String, user>();
  }

  public void addUser(String username, String password) {
    if (map.containsKey(username)) {
      System.out.println("Username already exists");
    } else {
      user newUser = new user(username, password);
      map.put(username, newUser);
      System.out.println("User added successfully");
    }
  }

  private void deposit(String username) {
    if (map.containsKey(username)) {
      user get_user = map.get(username);
      System.out.println("Enter the amount you want to deposit");
      Scanner sc = new Scanner(System.in);
      double amount = sc.nextDouble();
      get_user.balance += amount;
      System.out.println("Deposit successful");
    } else {
      System.out.println("User not found");
    }
  }

  private void withdraw(String username) {
    System.out.println("Amount withdrawn");
    if (map.containsKey(username)) {
      user get_user = map.get(username);
      System.out.println("Enter the amount you want to withdraw");
      Scanner sc = new Scanner(System.in);
      double amount = sc.nextDouble();
      if (amount > get_user.balance) {
        System.out.println("Insufficient balance");
      } else {
        get_user.balance -= amount;
        System.out.println("Withdrawal successful");
      }
    }
    System.out.println("Amount withdrawn");
  }

  private boolean check_transfer(String username, double amt) {
    if (map.containsKey(username)) {
      user get_user = map.get(username);
      double new_amount = get_user.balance + amt;
      if (get_user.balance == new_amount) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  private void transfer(String Username) {
    System.out.println("Enter the username to which you want to transfer: ");
    Scanner sc = new Scanner(System.in);
    String name = sc.nextLine();

    if (map.containsKey(name)) {
      user get_user = map.get(Username);
      user get_transfer_user = map.get(name);
      System.out.println("Enter the amount: ");
      double amt = sc.nextDouble();
      if (amt > get_user.balance) {
        System.out.println("Insufficient balance");
      } else {
        get_user.balance -= amt;
        get_transfer_user.balance += amt;

        boolean bool = check_transfer(name, amt);
        if (bool == true) {
          System.out.println("Transfer successful");
        } else {
          System.out.println("Transfer not done");
        }
      }
    } else {
      System.out.println("User doesn't exist");
    }
  }

  private void get_balance(String username) {
    System.out.println("Balance: " + map.get(username).balance);
  }

  private void displayUser() {
    // string representation of a user object. It's not the actual user data but a
    // reference to the object's memory location.
    System.out.println(this.map);
  }

  public void login(String username, String password) {
    if (map.containsKey(username)) {
      user get_user = map.get(username);
      if (get_user.password.equals(password)) {
        System.out.println("Login successful");
        System.out.println("Welcome " + username + " What do you want to do?");
        System.out.println();

        // beginning of while loop
        while (true) {
          System.out.println("1. Display Account balance");
          System.out.println("2. Deposit money");
          System.out.println("3. Withdraw money");
          System.out.println(
              "4. Transfer money to another user (You need to enter the username of the user you want to transfer to)");
          System.out.println("5. Exit");

          System.out.println();
          int choice;
          Scanner sc = new Scanner(System.in);
          choice = sc.nextInt();

          switch (choice) {
            case 1:
              get_balance(username);
              break;

            case 2:
              deposit(username);
              break;

            case 3:
              withdraw(username);
              break;

            case 4:
              transfer(username);
              break;

            case 5:
              System.out.println("Do you wish to enter a new user?");
              Scanner s = new Scanner(System.in);
              String ch = sc.nextLine();

              if (ch.equals("yes")) {
                System.out.println("Enter the username: ");
                String user = s.nextLine();
                System.out.println("Enter the password: ");
                String pass = s.nextLine();
                addUser(user, pass);
              }

            case 6:
              System.out.println("Thank you for using our banking system");
              return;
          }
        } // end of loop

      } else {
        System.out.println("Incorrect password");
      }
    } else {
      System.out.println("User not found");
    }
  }
}

public class Main {
  public static void main(String[] args) {

    bankingSystem bank = new bankingSystem();
    bank.addUser("nobita", "mapping");
    bank.addUser("doremon", "nobita");

    Scanner sc = new Scanner(System.in);
    String choice = sc.nextLine();

    if (choice.equalsIgnoreCase("yes")) {
      System.out.println("Enter the username: ");
      String newUser = sc.nextLine();
      System.out.println("Enter the password: ");
      String newPassword = sc.nextLine();
      bank.addUser(newUser, newPassword);
    }

    // Continue to the login process
    System.out.println("Enter your username: ");
    String username = sc.nextLine();
    System.out.println("Enter your password: ");
    String password = sc.nextLine();
    bank.login(username, password);

    sc.close();
  }
}
