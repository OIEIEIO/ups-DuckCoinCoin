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
    // Methods
    //================================================================================

	//Verify if the first block is the genesis block
	public boolean verifGenesis() {
		return (this.getBlockchain().get(0) instanceof Genesis);
	}
	
	//Verify that each block's hash is correct (by computing it again)
	//and verify that each block contains the hash of its previous block
	public boolean verifChaining(int difficulty) {

		for (int i = 1; i < this.getBlockchain().size(); i++) {
			
			this.getBlockchain().get(i).setNonce(0);

			if ( !this.getBlockchain().get(i).getHash().equals(this.getBlockchain().get(i).mining(difficulty)) ) {
				System.out.println( "Block " + this.getBlockchain().get(i).getIndex() + " hash is incorrect" );
				return false;
			}
			if (this.getBlockchain().get(i).getPrehash() != this.getBlockchain().get(i-1).getHash()) {
				System.out.println("Block " + this.getBlockchain().get(i).getIndex() + " is not linked to block " + this.getBlockchain().get(i-1).getIndex());
				return false;
			}
		}
		return true;
	}
	
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
		for (int i = 0; i < this.getNumblocks(); i++) {
			System.out.println("Block " + i);
			System.out.println("index: " + this.getBlockchain().get(i).getIndex());
			System.out.println("roothash: " + this.getBlockchain().get(i).getRoothash());
			System.out.println("hash: " + this.getBlockchain().get(i).getHash());
			System.out.println("nonce: " + this.getBlockchain().get(i).getNonce());
			System.out.println("timestamp: " + this.getBlockchain().get(i).getTimestamp());
			System.out.println("prehash: " + this.getBlockchain().get(i).getPrehash());
			System.out.println();
			System.out.println("Block " + i + " - Transactions");
			for (int j = 0; j < this.getBlockchain().get(i).getNumtransactions(); j++) {
				System.out.println(this.getBlockchain().get(i).getTransaction_list().get(j).getTransaction());
			}
			System.out.println();
		}
		System.out.println("Is the first block genesis? " + this.verifGenesis());
		System.out.println("Is this blockchain properly chained? " + this.verifChaining(difficulty));
	}
}
