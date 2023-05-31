package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Account {	
	private String _UUID;
	private String _username;
	private String _password;
	private String _fullname;
	private String _number;
	private String _address;
	private String _email;
	
	
	// static account lists
	public static List<Account> accountList;
	
	// initializes the template account (the admin account cannot be removed)
	public static void initAccounts() {
		accountList = new ArrayList<>();
		accountList.add(new Account("admin", "admin", "", "", "", ""));
		accountList.add(new Account("user", "user", "", "", "", ""));
		System.out.println(Account.accountList);
		
	}
	
	// adds an account to the list
	public static void addAccount(String username, String password, String fullname, String number, String address, String email) {
		addAccount(new Account(username, password, fullname, number, address, email));
	}
	public static void addAccount(Account account) {
		accountList.add(account);
	}

	// deletes account
	public static void deleteAccount(String find, int what) {
		// if want to find the UUID
		if (what == 0) {
			for (Account account : accountList) {
				if (account._UUID.equals(find)) {
					// remove the specific account
					accountList.remove(account);
					return;
				}
			}
		// if want to find the username
		} else {
			for (Account account : accountList) {
				if (account._username.equals(find)) {
					// remove the specific account
					accountList.remove(account);
					return;
				}
			}
		}
	}
	public static void deleteAccount(Account account) {
		accountList.remove(account);
	}
	
	// modifies the account
	public static void modifyAccount(String UUID, String username, String password, String fullname, String number, String address, String email) {
		for (Account acct : accountList) {
			if (acct._UUID.equals(UUID)) {
				// find UUID first then modify the account
				acct.modify(username, password, fullname, number, address, email);
				return;
			}
		}
	}
	
	// gets the account based on  specific info to be found
	public static Account getAccount(String find, int what) {
		// if UUID
		if (what == 0) {
			for (Account account : accountList) {
				if (account._UUID.equals(find)) {
					return account;
				}
			}
		// if username
		} else {
			for (Account account : accountList) {
				if (account._username.equals(find)) {
					return account;
				}
			}
		}
		return null;
	}
	
	// checks if the username and passwords mathced, if not found or not equals, this will return null
	public static Account checkAccount(String username, String password) {
		var acct = getAccount(username, 1);
		
		if (acct == null)
			return null;
		
		if (acct.getPassword().equals(password)) 
			return acct;
		else
			return null;	
	}
	
	
	
	public Account(String username, String password, String fullname, String number, String address, String email) {
		_UUID = UUID.randomUUID().toString().substring(0, 23);
		_username = username;
		_password = password;
		_fullname = fullname;
		_number = number;
		_address = address;
		_email = email;
		
		
	}
	
	// mofifiest the account class
	public void modify(String username, String password, String fullname, String number, String address, String email) {
		_username = username;
		_password = password;
		_fullname = fullname;
		_number = number;
		_address = address;
		_email = email;
	}

	public String getUUID() {
		return _UUID;
	}
	
	public String getUsername() {
		return _username;
	}

	public String getPassword() {
		return _password;
	}
	
	public String getFullname() {
		return _fullname;
	}
	
	public String getNumber() {
		return _number;
	}
	
	public String getAddress() {
		return _address;
	}

	public String getEmail() {
		return _email;
	}

	public void setUsername(String username) {
		_username = username;
	}

	public void setPassword(String password) {
		_password = password;
	}
	
	public void setFullname(String fullname) {
		_fullname = fullname;
	}
	
	public void setNumber(String number) {
		_number = number;
	}
	
	public void setAddress(String address) {
		_address = address;
	}

	public void setEmail(String email) {
		_email = email;
	}

    public void updateBalance(double d) {
    }

    public void clearCart() {
    }
}