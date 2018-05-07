package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	//================================================================================
	// Properties
	//================================================================================
	
	public static int difficulty; //mining difficulty
	private int numblocks; //number of blocks
	private Genesis genesis;
	List<Block> blockchain;	//list of blocks
	
	//================================================================================
	// Constructors
	//================================================================================
	
	public Blockchain(int difficulty, int numblocks) {
		
		this.numblocks = numblocks;
		this.blockchain = new ArrayList<Block>();	//list of blocks
		genesis = new Genesis();
		this.blockchain.add(genesis);
	}
	
	//================================================================================
	// Accessors
	//================================================================================
	
	public int getNumblocks() {
		return numblocks;
	}
	
	public int getDifficulty() {
		return difficulty;
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}
	
	//================================================================================
	// Verify (Level 1)
	//================================================================================
	
	//Verify that the first block is the genesis block
	public boolean verifGenesis() {
		return (this.getBlockchain().get(0) instanceof Genesis);
	}
	
	//Verify that each block's root hash is correct (by computing it again)
	public boolean verifRoothash() {
		
		for (int i = 1; i < this.getBlockchain().size(); i++) {
			
			if ( !this.getBlockchain().get(i).getRoothash().equals(this.getBlockchain().get(i).merkleTree()) ) {
				System.out.println("Block " + this.getBlockchain().get(i).getIndex() + ": incorrect root hash");
				return false;
			}
		}
		return true;
	}
	
	//Verify that each block's hash is correct (by computing it again)
	//and verify that each block contains the hash of its previous block
	public boolean verifChaining(int difficulty) {

		for (int i = 1; i < this.getBlockchain().size(); i++) {
			
			this.getBlockchain().get(i).setNonce(0);
			
			if ( !this.getBlockchain().get(i).getHash().equals(this.getBlockchain().get(i).mining(difficulty)) ) {
				System.out.println( "Block " + this.getBlockchain().get(i).getIndex() + ": incorrect hash" );
				return false;
			}
			if (this.getBlockchain().get(i).getPrehash() != this.getBlockchain().get(i-1).getHash()) {
				System.out.println("Block " + this.getBlockchain().get(i).getIndex() + " is not linked to block " + this.getBlockchain().get(i-1).getIndex());
				return false;
			}
		}
		return true;
	}
	
	/**
	 * If a block is deleted or modified:
	 * Recompute merkle root and hash
	 * Chain blocks again
	 * @param difficulty
	 */
	public void recompute(int difficulty) {
		
		for (int i = 1; i < this.getBlockchain().size(); i++) {
			
			this.getBlockchain().get(i).setNonce(0);
			
			//Chain blocks again
			this.getBlockchain().get(i).setPrehash(this.getBlockchain().get(i - 1).getHash());
			
			//Recompute merkle root
			this.getBlockchain().get(i).setRoothash(this.getBlockchain().get(i).merkleTree());
			
			//Recompute hash
			this.getBlockchain().get(i).setHash(this.getBlockchain().get(i).mining(difficulty));
		}
	}
		
	//================================================================================
	// Methods
	//================================================================================

	public static Blockchain createBlockchain(int difficulty, int numblocks) {
		
		Blockchain bc = new Blockchain(difficulty, numblocks);

		//Generate blocks
		for (int i = 1; i < numblocks; i++) {
			
			Block b = new Block();
			b.setIndex(i);
			b.setPrehash(bc.getBlockchain().get(i - 1).getHash());
			b.generateTransactions();
			b.setRoothash(b.merkleTree());
			b.setHash(b.mining(difficulty));
			bc.blockchain.add(b);
		}
		return bc;
	}
	
	public void printBlockchain(int difficulty) {
		System.out.println();
		for (int i = 0; i < this.getNumblocks(); i++) {
			System.out.println("================================================================================");
			System.out.println("Block " + this.getBlockchain().get(i).getIndex());
			System.out.println("================================================================================");
			System.out.println("index: " + this.getBlockchain().get(i).getIndex());
			System.out.println("roothash: " + this.getBlockchain().get(i).getRoothash());
			System.out.println("hash: " + this.getBlockchain().get(i).getHash());
			System.out.println("nonce: " + this.getBlockchain().get(i).getNonce());
			System.out.println("timestamp: " + this.getBlockchain().get(i).getTimestamp());
			System.out.println("prehash: " + this.getBlockchain().get(i).getPrehash());
			System.out.println();
			System.out.println("Transactions");
			System.out.println();
			for (int j = 0; j < this.getBlockchain().get(i).getNumtransactions(); j++) {
				System.out.println(this.getBlockchain().get(i).getTransaction_list().get(j).getTransaction());
			}
			System.out.println();
		}
		System.out.println("Is the first block genesis? " + this.verifGenesis());
		System.out.println("Is the root hash of each block correct? " + this.verifRoothash());
		System.out.println("Is this blockchain properly chained? " + this.verifChaining(difficulty));
		System.out.println();
	}
	
	public String blockchainToString(int difficulty) {
		
		String bc2string = "\n";
		
		for (int i = 0; i < this.getNumblocks(); i++) {
			bc2string += "================================================================================\n";
			bc2string += ("Block " + this.getBlockchain().get(i).getIndex() + "\n");
			bc2string += "================================================================================\n";
			bc2string += ("index: " + this.getBlockchain().get(i).getIndex() + "\n");
			bc2string += ("roothash: " + this.getBlockchain().get(i).getRoothash() + "\n");
			bc2string += ("hash: " + this.getBlockchain().get(i).getHash() + "\n");
			bc2string += ("nonce: " + this.getBlockchain().get(i).getNonce() + "\n");
			bc2string += ("timestamp: " + this.getBlockchain().get(i).getTimestamp() + "\n");
			bc2string += ("prehash: " + this.getBlockchain().get(i).getPrehash() + "\n");
			bc2string += "\n";
			bc2string += ("Transactions\n\n");
			for (int j = 0; j < this.getBlockchain().get(i).getNumtransactions(); j++) {
				bc2string += (this.getBlockchain().get(i).getTransaction_list().get(j).getTransaction() + "\n");
			}
			bc2string += "\n";
		}
		bc2string += ("Is the first block genesis? " + this.verifGenesis() + "\n");
		bc2string += ("Is the root hash of each block correct? " + this.verifRoothash() + "\n");
		bc2string += ("Is this blockchain properly chained? " + this.verifChaining(difficulty) + "\n");
		bc2string += "\n";
		
		return bc2string;
	}
}
