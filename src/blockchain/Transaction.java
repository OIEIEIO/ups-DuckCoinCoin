package blockchain;

import java.util.concurrent.ThreadLocalRandom;

public class Transaction {

	public static final int MAX_VALUE = 63;
	
	// random number concatenated to the transaction identification (string)
	private int randomNum = ThreadLocalRandom.current().nextInt(1, MAX_VALUE+1);
	
	private int index;		// index of the transaction in the list
	
	private String timestamp;	// date of creation of the transaction
	
	private String source;		// hexadecimal address of the transmitter
	
	private String destination;		// hexadecimal address of the receiver
	
	private int sum;	// transaction sum
		
	private String signature_source;	// signature of the transmitter

	
	public String getTransaction(String source, String destination) {
		return source + "-" + destination + " :" + randomNum;
	}
}
