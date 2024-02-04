/**
* COMP 2631 Assignment 1 MailSystem class
* @author Abiola Raji
* Date: January 31, 2024
*/

package mail_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MailSystem
{
	private Message[] messages = new Message[500];
	private int messageCount = 0;
	
	public Message[] getMessages ()
	{
		return messages;
	}
	
	public int getMessageCount ()
	{
		return messageCount;
	}
	
	/**
	* Reads all messages in the mail system from a file and puts it into an array
	* @param MAIL_FILE the name of the mail file
	*/
	public void readMail (String MAIL_FILE)
	{
		try
		{
			File myFile = new File(MAIL_FILE);
			Scanner sc = new Scanner(myFile);
			
			for(int i = 0; sc.hasNextLine(); i++)
			{
				messages[i] = new Message();
		        messages[i].readMessage(sc, MAIL_FILE);
		        messageCount++;
			}
		    sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The file was not found");
			e.printStackTrace();
		}
	}
	
	/**
	* Writes all messages in the mail system into a file
	* @param MAIL_FILE the name of the mail file
	*/
	public void writeMail (String MAIL_FILE)
	{
		try
		{
	      FileWriter myFile = new FileWriter(MAIL_FILE);
	      for (int i = 0; i < messageCount; i++)
	      {
	    	  myFile.write(messages[i].getMessageNumber() + " " + messages[i].getMessageStatus() + "\n");
	    	  myFile.write(messages[i].getSender() + "\n");
	    	  myFile.write(messages[i].getRecipient() + "\n");
	    	  myFile.write(messages[i].getSubject() + "\n");
	    	  myFile.write(messages[i].getBody());
	    	  myFile.write("EOF\n");	    	  
	      }
	      myFile.close();   
		} 
		catch (IOException e)
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	/**
	* Gets all messages for a specific user and puts it into an array
	* @param  user the current user of the Mail System
	* @return 	   messages for user
	*/
	public Message[] getMessagesForUser (String user)
	{
		int count = 0;
		Message[] messagesRecipient = new Message[500];
		
		for (int i = 0; i < 500 && messages[i] != null; i++)
		{
			if (messages[i].getRecipient().equals(user))
			{
				messagesRecipient[count] = messages[i];
	            count++;
			}
		}
		Message[] result = new Message[count];
		System.arraycopy(messagesRecipient, 0, result, 0, count);
		
		return result;	
	}

	/**
	* Adds a new message to the mail system
	* @param user      the current user of the Mail System
	* @param recipient the recipient of the message
	* @param subject   the subject of the message
	* @param body      the body of the message
	*/
	public void addMessage (String user, String recipient, String subject, String body)
	{
		messageCount++;
		messages[messageCount - 1] = new Message(messageCount, 'N', user, recipient, subject, body);	
	}
}
