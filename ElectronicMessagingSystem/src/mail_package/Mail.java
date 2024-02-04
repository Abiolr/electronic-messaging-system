/**
* COMP 2631 Assignment 1 Mail class
* @author Abiola Raji
* Date: January 31, 2024
*/

package mail_package;

import java.util.Scanner;

public class Mail
{
	public static final String MAIL_FILE = "mail.txt";
	
	public static Scanner keyboard = new Scanner (System.in);
	
	MailSystem system = new MailSystem ();

	public static void main (String [] args)
	{
		Mail program = new Mail ();
		
		program.run ();
	}
	
	/**
	* runs the entire mail system program
	*/
	public void run ()
	{
		System.out.println ("Welcome to the Secure Corp. messaging system.");
		System.out.println ();

		System.out.print ("Reading mail from \"" + MAIL_FILE + "\"...");
		system.readMail (MAIL_FILE);
		System.out.println (" done.");
		System.out.println ();
	
		String user = switchUser ();
	
		char choice = getValidChoice ();
		while (choice != 'q'& choice != 'Q')
		{
			switch (choice)
			{
				case 'l':
				case 'L':
					listMessages (user);
					break;
				case 'v':
				case 'V':
					viewMessage (user);
					break;
				case 'c':
				case 'C':
					composeMessage (user);
					break;
				case 's':
				case 'S':
					user = switchUser ();
					break;
			}
		
			choice = getValidChoice ();
		}
	
		System.out.println ("Thanks for using the Secure Corp. messaging system.");
		System.out.println ();

		System.out.print ("Writing mail to \"" + MAIL_FILE + "\"...");
		system.writeMail (MAIL_FILE);
		System.out.println (" done.");
		System.out.println ();
	}

	/**
	* checks the user choice until given an acceptable choice
	* @return choice
	*/
	char getValidChoice ()
	{
		char choice = getChoice();
		while(!(acceptable(choice))) {
			System.out.println("Invalid choice");
			choice = getChoice();
		}
		
		return choice;
	}

	/**
	* gets the choice from the user
	* @return choice
	*/
	char getChoice ()
	{
		System.out.println ("Available Options"); 
		System.out.println ("  L ‐ List your messages");
		System.out.println ("  V ‐ View a message");
		System.out.println ("  C ‐ Compose a message");
		System.out.println ("  S ‐ Switch users");
		System.out.println ("  Q ‐ Quit");
		System.out.println ();
		System.out.print ("Please enter your choice: ");
	
		char choice;
		choice = keyboard.next ().charAt (0);
		// clear out newline in case next input is full line.
		keyboard.nextLine ();
	
		return choice;
	}
	
	/**
	* checks if user choice is acceptable
	* @param  choice the choice of the user
	* @return        true if choice is acceptable
	*/
	boolean acceptable (char choice)
	{
		boolean isValid;
		if (choice == 'Q' || choice == 'q' ||
			choice == 'L' || choice == 'l' ||
			choice == 'V' || choice == 'v' ||
			choice == 'C' || choice == 'c' ||
			choice == 'S' || choice == 's') {
			isValid = true;
		}
		else {
			isValid = false;
		}
		
		return isValid;
		
	}

	/**
	* lists all messages for the specific user
	* @param user the current user of the Mail System
	*/
	void listMessages (String user)
	{
		Message[] messages;
	
		messages = system.getMessagesForUser (user);
	
		System.out.println ("### S From            Subject");
		System.out.println ("‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐---");
		for (int i = messages.length - 1; i >= 0; i--){
			messages[i].printAsLine(System.out);
		}
	
		System.out.println ();
	}

	/**
	* ask user what message the want to view and prints it,
	* prints an error message if message is not found
	* @param user the current user of the Mail System
	*/
	void viewMessage (String user)
	{
		Message[] messages;
		
		messages = system.getMessagesForUser (user);
		
		System.out.print("Which message would you like to view: ");
		int messageNumber = keyboard.nextInt();
		boolean messageFound = false;
		for (int i = 0; i < messages.length; i++) {
			if (messages[i].getMessageNumber() == messageNumber) {
				messages[i].printMessage(System.out);
				messages[i].setMessageStatus('R');
				messageFound = true;
			} 
		}
		if (messageFound == false)
			System.out.println("I'm sorry; message #" + messageNumber + " is not addressed to you or does not exist.");
	}
		
	/**
	* Lets the user write a message to another user
	* @param user the current user of the Mail System
	*/
	void composeMessage (String user)
	{
		System.out.print("Recipient: ");
		String recipient = keyboard.nextLine();
		System.out.print("Subject: ");
		String subject = keyboard.nextLine();
		System.out.print("Body (enter EOF to end body): ");
		String line = keyboard.nextLine();
		String body = " ";
		
		while (!(line.equals("EOF"))) {
			body = body.concat(line);
			body = body.concat("\n");
			line = keyboard.nextLine();
		}
		
		system.addMessage(user, recipient, subject, body);
		
		int messageCount  = system.getMessageCount();
		System.out.println("Message #" + messageCount + " sent to " + recipient);
	
	}

	/**
	* lets a different user log into the system
	* @return user
	*/
	String switchUser ()
	{
		System.out.print("Enter your name (be honest): ");
		String user = keyboard.next();
		
		return user;
	}
}
