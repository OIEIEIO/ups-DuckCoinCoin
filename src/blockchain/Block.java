package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {
	
	private int index;	// index of the block inside the blockchain
	
	private String timestamp;	// date of creation of the block
	
	protected String prehash;		// hash of the previous block
	
	Random random = new Random();
	
	private int numtransactions;	// number of transactions
	
	List<Transaction> transaction_list = new ArrayList<Transaction>();	// list of transactions
	
	private String roothash;	// root hash of the merkle tree
	
	protected String hash;	// hash of the current block
	
	protected int nonce;	// a field whose value is set so that the hash of the block will contain a run of leading zeros
	
	public Block() {
		
		timestamp = "2018/04/14 13:16:46";
		numtransactions = random.nextInt(11);
		Transaction transaction_list[] = new Transaction[numtransactions];
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
	public String concatenateTransaction(Block b) {
		String outcome = "";
		List<Transaction> list = b.getTransaction_list();
		for (int i = 0; i < list.size(); i++) {
			outcome += list.get(i).getTransaction();
		}
		return outcome;
	}
	
	//Concatenate all the attributes of the block to hash it
	public String concatenateHash(Block b) {
		return (String.valueOf(b.getIndex()) + b.getTimestamp() + b.getPrehash() + String.valueOf(b.getNumtransactions()) + concatenateTransaction(b) + b.getRoothash() + b.getNonce());
	}
	
	public void computeHash(Block b) {
		b.setHash(Hash.applySha256(concatenateHash(b)));
	}


}
