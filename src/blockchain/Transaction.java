package blockchain;

import java.util.Date;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime; 

public class Transaction {

	public static final int MAX_VALUE = 10;
	//random number concatenated to the transaction identification (string)
	Random random = new Random();
	private int index;		//index of the transaction in the list
	private String timestamp;	//date of creation of the transaction (String)
	private long longtime;	//date of creation of the transaction (long)
	private String source;		//hexadecimal address of the transmitter
	private String destination;		//hexadecimal address of the receiver
	private int sum;	//transaction sum
	private String signature_source;	//signature of the transmitter

	public Transaction(String source, String destination, int sum) {
		this.source = source;
		this.destination = destination;
		this.sum = sum;
		this.longtime = new Date().getTime();
		this.timestamp = convertTime(longtime);
	}
	
	public String getTransaction() {
		return source + "-" + destination + " :" + random.nextInt(MAX_VALUE);
	}
	
	public int getSum() {
		return sum;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public String convertTime(long time) {
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return format.format(date);
	}
}
