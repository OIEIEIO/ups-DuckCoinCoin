package blockchain;

import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {
	
	private int index;	//index of the block inside the blockchain
	private String timestamp;	//date of creation of the block (String)
	private long longtime;	//date of creation of the block (long)
	protected String prehash;		//hash of the previous block
	Random random = new Random();
	protected int numtransactions;	//number of transactions
	List<Transaction> transaction_list;	//list of transactions
	private String roothash;	//root hash of the merkle tree
	protected String hash;	//hash of the current block
	protected int nonce;	//a field whose value is set so that the hash of the block will contain a run of leading zeros
	
	public Block() {
		
		this.nonce = 0;
		this.longtime = new Date().getTime();
		this.timestamp = convertTime(longtime);
		this.numtransactions = random.nextInt(11);
		this.transaction_list = new ArrayList<Transaction>();	//list of transactions
		this.hash = computeHash(2);
	}
	
	public int getNonce() {
		return nonce;
	}
	
	public String getHash() {
		return hash;
	}
	
	public List<Transaction> getTransaction_list() {
		return transaction_list;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public void setNonce(int value) {
		nonce = value;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getNumtransactions() {
		return numtransactions;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setPrehash(String hash) {
		prehash = hash;
	}
	
	public String getPrehash() {
		return prehash;
	}
	
	public String getRoothash() {
		return roothash;
	}
	
	//Concatenate all the transactions of the block
	public String concatenateTransaction() {
		String outcome = "";
		List<Transaction> list = this.getTransaction_list();
		for (int i = 0; i < list.size(); i++) {
			outcome += list.get(i).getTransaction();
		}
		return outcome;
	}
	
	//Concatenate all the attributes of the block
	public String concatenateHash(int difficulty) {
		String numZero = "";
		for (int i = 0; i < difficulty; i++) {
			numZero += "0";
		}
		return (numZero + String.valueOf(this.getIndex()) + this.getTimestamp() + this.getPrehash()
		+ String.valueOf(this.getNumtransactions()) + concatenateTransaction() + this.getRoothash()
		+ this.getNonce());
	}
	
	//Return hash of block
	public String computeHash(int difficulty) {
		return Hash.applySha256(concatenateHash(difficulty));
	}

	public String convertTime(long time) {
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return format.format(date);
	}
}
