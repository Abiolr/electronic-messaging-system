/**
* COMP 2631 Assignment 1 Message class
* @author Abiola Raji
* Date: January 31, 2024
*/

package mail_package;

import java.io.PrintStream;
import java.util.Scanner;


public class Message
{
	private int messageNumber;
	private char messageStatus;
	private String sender;
	private String recipient;
	private String subject;
	private String body;
	
	public Message ()
	{
	}
	
	public Message (int messageNumber, char messageStatus, 
					String sender, String recipient, 
					String subject, String body)
	{
		this.messageNumber = messageNumber;
		this.messageStatus = messageStatus;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
	}
	
	public int getMessageNumber ()
	{
		return messageNumber;
	}
	
	public char getMessageStatus ()
	{
		return messageStatus;
	}
	
	public String getSender ()
	{
		return sender;
	}
	
	public String getRecipient ()
	{
		return recipient;
	}
	
	public String getSubject ()
	{
		return subject;
	}
	
	public String getBody ()
	{
		return body;
	}
	
	/**
	 * Sets the message status
	 * @param messageStatus the status of the message
	 */
	public void setMessageStatus (char messageStatus)
	{
		this.messageStatus = messageStatus;
	}
	
	/**
	* Prints a single message's number, status, sender, and subject on a line
	* @param out the output
	*/
	public void printAsLine (PrintStream out)
	{
		out.print(messageNumber);
		if (messageNumber < 100)
		{
			if (messageNumber < 10)
				out.print("  ");
			else
				out.print(" ");
		}
		
		out.print(" ");
		out.print(messageStatus);
		out.print(" ");
		
		if (sender.length() > 15)
		{
			String cutSender = sender.substring(0, 15);
			out.print(String.format("%-15s", cutSender) + " ");
		}
		else
			out.print(String.format("%-15s", sender) + " ");
		
		if (subject.length() > 30)
		{
			String cutSubject = subject.substring(0, 30);
			out.print(String.format("%-30s", cutSubject) + " ");
		}
		else
			out.print(String.format("%-30s", subject) + " ");
		
		out.println();
	}
			
	/**
	* Prints a message's contents onto the screen
	* @param out the output
	*/
	public void printMessage (PrintStream out)
	{	
		if (messageStatus == 'N')
		{
			out.print("Message # " + messageNumber);
			out.println(" (New)");
		} else
			out.println("Message # " + messageNumber);
		
		out.println("From: " + sender);
		out.println("Subject: " + subject);
		out.println();
		out.println ("‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐---");
		out.println(body);
		out.println();
		out.println ("‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐---");
		
	}
	
	/**
	* Reads a single message from a file
	* @param sc        the scanner
	* @param MAIL_FILE the name of the mail file
	*/
	public void readMessage (Scanner sc, String MAIL_FILE)
	{
		messageNumber = sc.nextInt();
		String withWhitespace = sc.nextLine();
        messageStatus = (withWhitespace.trim()).charAt(0);
        sender = sc.nextLine();
        recipient = sc.nextLine();
        subject = sc.nextLine();
        body = "";
        String line;
        
        while (!(line = sc.nextLine()).equals("EOF"))
        {
        	body = body.concat(line);
        	body = body.concat("\n");	
	    }	        
	}
}
