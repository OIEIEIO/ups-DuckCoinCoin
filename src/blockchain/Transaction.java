package blockchain;

import java.util.Random;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class Transaction {

	public static final int MAX_VALUE = 63;
	
	// random number concatenated to the transaction identification (string)
	Random random = new Random();
	
	private int index;		// index of the transaction in the list
	
	private String timestamp;	// date of creation of the transaction
	
	private String source;		// hexadecimal address of the transmitter
	
	private String destination;		// hexadecimal address of the receiver
	
	private int sum;	// transaction sum
		
	private String signature_source;	// signature of the transmitter

	public Transaction(String destination, int sum) {
		timestamp = "2018/04/14 13:16:46";
	}
	
	public String getTransaction(String source, String destination) {
		return source + "-" + destination + " :" + random.nextInt(MAX_VALUE);
	}
}
