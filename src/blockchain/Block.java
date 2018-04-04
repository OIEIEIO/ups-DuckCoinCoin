package blockchain;

import java.util.concurrent.ThreadLocalRandom;

public class Block {
	
	private int index;	// index of the block inside the blockchain
	
	private String timestamp;	// date of creation of the block
	
	private String prehash;		// hash of the previous block
	
	private int numtransactions = ThreadLocalRandom.current().nextInt(1, 11);	// number of transactions
	
	Transaction transaction_list[] = new Transaction[numtransactions];	// list of transactions
	
	private String roothash;	// root hash of the merkle tree
	
	private String hashblock;	// hash of the current block
	
	private int nonce;	// a field whose value is set so that the hash of the block will contain a run of leading zeros
}

// data structure arraylist
