package blockchain;

import java.util.Random;

public class Block {
	
	private int index;	// index of the block inside the blockchain
	
	private String timestamp;	// date of creation of the block
	
	private String prehash;		// hash of the previous block
	
	Random random = new Random();
	
	private int numtransactions = random.nextInt(11);	// number of transactions
	
	Transaction transaction_list[] = new Transaction[numtransactions];	// list of transactions
	
	private String roothash;	// root hash of the merkle tree
	
	private String hash;	// hash of the current block
	
	private int nonce;	// a field whose value is set so that the hash of the block will contain a run of leading zeros
	
	
	
	public int getNonce() {
		return nonce;
	}
	
	public void setNonce(int value) {
		nonce = value;
	}
	
	public void setPrehash(String hash) {
		prehash = hash;
	}
	
	public String getPrehash() {
		return prehash;
	}
}