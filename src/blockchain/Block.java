package blockchain;

import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {
	
	//================================================================================
	// Properties
	//================================================================================
	
	private int index; //index of the block inside the blockchain
	private String timestamp; //date of creation of the block (String)
	private long longtime; //date of creation of the block (long)
	protected String prehash; //hash of the previous block
	Random random = new Random();
	protected int numtransactions; //number of transactions
	List<Transaction> transaction_list; //list of transactions
	protected String roothash; //root hash of the merkle tree
	protected String hash; //hash of the current block
	protected int nonce; //a field whose value is set so that the hash of the block will contain a run of leading zeros
	static final String DICT = "abcdefghijklmnopqrstuvwxyz"; //Dictionary of possible characters for randomized string
	
	//================================================================================
	// Constructors
	//================================================================================
	
	public Block() {
		
		this.nonce = 0;
		this.longtime = new Date().getTime();
		this.timestamp = convertTime(longtime);
		this.numtransactions = random.nextInt(10) + 1;
		this.transaction_list = new ArrayList<Transaction>(); //list of transactions
	}
	
	//================================================================================
	// Accessors
	//================================================================================
	
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

	public void setTransaction_list(List<Transaction> transaction_list) {
		this.transaction_list = transaction_list;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
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
	
	public void setRoothash(String roothash) {
		this.roothash = roothash;
	}
	
	//================================================================================
	// Methods
	//================================================================================
	
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
		
		return (String.valueOf(this.getIndex())
				+ this.getTimestamp()
				+ this.getPrehash()
				+ String.valueOf(this.getNumtransactions())
				+ this.concatenateBlockTransaction()
				+ this.getRoothash()
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
		String hash;
		String numZero = "";
		
		//Set number of zeros equal to difficulty
		for (int i = 0; i < difficulty; i++) {
			numZero += "0";
		}
		
		//Hash the block until the hash starts with 'difficulty' zeros
		do {
			this.setNonce(this.getNonce() + 1);
			hash = Hash.applySha256(this.concatenateHash());
		} while (!verifyDifficulty(hash, difficulty, numZero));
		return hash;
	}
	
	private List<String> getNewNodeList(List<String> tempNodeList) {
		
		List<String> newNodeList = new ArrayList<String>();
		int indexList = 0;
		String left;
		String right;
		
		while (indexList < tempNodeList.size()) {
			//left
			left = tempNodeList.get(indexList);
			++indexList;
			
			//right
			right = "";
			if (indexList != tempNodeList.size()) {
				right = tempNodeList.get(indexList);
			}
			
			//hash left and right
			newNodeList.add( Hash.applySha256(left + right) );
			++indexList;
		}
		
		return newNodeList;
	}
	
	public String merkleTree() {
		
		List<String> tempNodeList = new ArrayList<String>();
		
		for (int i = 0; i < this.getTransaction_list().size(); i++) {
			tempNodeList.add( this.getTransaction_list().get(i).concatenateTransaction() );
		}
		
		if (this.getNumtransactions() % 2 == 1) {
			tempNodeList.add( tempNodeList.get(tempNodeList.size() - 1) );
		}

		List<String> newNodeList = getNewNodeList(tempNodeList);
		while (newNodeList.size() != 1) {
			newNodeList = getNewNodeList(newNodeList);
		}
		
		return newNodeList.get(0);
	}
	
	/**
	 * Generate random string
	 * @param len: desired length of the string
	 * @return random string of length 'len'
	 */
	public String randomString(int len) {
		   StringBuilder sb = new StringBuilder(len);
		   for(int i = 0; i < len; i++)
		      sb.append(DICT.charAt(random.nextInt(DICT.length())));
		   return sb.toString();
		}
	
	public void generateTransactions() {
		for (int i = 0; i < this.getNumtransactions(); i++) {
			Transaction t = new Transaction();
			t.setIndex(i);
			t.setSender(randomString(5));
			t.setReceiver(randomString(5));
			this.transaction_list.add(t);
		}
	}
}
