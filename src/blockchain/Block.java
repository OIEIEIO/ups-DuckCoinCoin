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
	static final String DICT = "abcdefghijklmnopqrstuvwxyz";	//Dictionary of possible characters for randomized string
	
	public Block() {
		
		this.nonce = 0;
		this.longtime = new Date().getTime();
		this.timestamp = convertTime(longtime);
		this.numtransactions = random.nextInt(11);
		this.transaction_list = new ArrayList<Transaction>(); //list of transactions
	}
	
	public int getNonce() {
		return nonce;
	}
	
	public void setNonce(int value) {
		nonce = value;
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
	public String concatenateBlockTransaction() {
		String outcome = "";
		List<Transaction> list = this.getTransaction_list();
		for (int i = 0; i < list.size(); i++) {
			outcome += list.get(i).getTransaction();
		}
		return outcome;
	}
	
	//Concatenate all the attributes of the block
	public String concatenateHash() {
		return (String.valueOf(this.getIndex()) + this.getTimestamp() + this.getPrehash()
		+ String.valueOf(this.getNumtransactions()) + concatenateBlockTransaction() + this.getRoothash()
		+ this.getNonce());
	}

	public String convertTime(long time) {
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return format.format(date);
	}
	
	public boolean verifyDifficulty(String s, int difficulty, String numZero) {
		if (numZero.matches(s.substring(0, difficulty))) {
			return true;
		}
		return false;
	}
	
	public String mining(int difficulty) {
		String outcome;
		String numZero = "";
		
		//Set number of zeros equal to difficulty
		for (int i = 0; i < difficulty; i++) {
			numZero += "0";
		}
		
		//Hash the block until the hash starts with 'difficulty' zeros
		do {
			++nonce;
			outcome = Hash.applySha256(concatenateHash());
		} while (!verifyDifficulty(outcome, difficulty, numZero));
		return outcome;
	}
	
	//TODO
	public String merkleTree() {
		if (this.numtransactions % 2 == 0) {
			for (int i = 0; i < this.getTransaction_list().size(); i++) {
				//Hash.applySha256();
			}
		} else {
			for (int i = 0; i < this.getTransaction_list().size(); i++) {
				//Hash.applySha256();
			}
		}
		return null;
	}
	
	public String randomString(int len) {
		   StringBuilder sb = new StringBuilder(len);
		   for(int i = 0; i < len; i++)
		      sb.append(DICT.charAt(random.nextInt(DICT.length())));
		   return sb.toString();
		}
	
	public void generateTransactions() {
		for (int i = 0; i < this.getNumtransactions(); i++) {
			Transaction t = new Transaction();
			t.setSender(randomString(5));
			t.setReceiver(randomString(5));
			this.transaction_list.add(t);
		}
	}
	
	public static Block createBlock(int difficulty) {
		Block b = new Block();
		b.generateTransactions();
		b.setHash(b.mining(difficulty));
		return b;
	}
}
