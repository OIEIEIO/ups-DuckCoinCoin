package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	public static int difficulty; //mining difficulty
	private int numblocks; //number of blocks
	private Genesis genesis;
	List<Block> blockchain;	//list of blocks
	
	public Blockchain(int difficulty, int numblocks) {
		
		this.numblocks = numblocks;
		this.blockchain = new ArrayList<Block>();	//list of blocks
		genesis = new Genesis();
		this.blockchain.add(genesis);
	}
	
	public int getNumblocks() {
		return numblocks;
	}
	
	public int getDifficulty() {
		return difficulty;
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}

	//Verify if the first block is the genesis block
	public boolean verifGenesis(Blockchain bc) {
		return (bc.getBlockchain().get(0) instanceof Genesis);
	}
	
	//Verify if each block contains the previous hash
	public boolean verifHash(List<Block> l) {
		for (int i = 1; i < l.size(); i++) {
			if (l.get(i).getPrehash() != l.get(i-1).getHash())
				return false;
		}
		return true;
	}
	
	public static Blockchain createBlockchain(int difficulty, int numblocks) {
		
		Blockchain bc = new Blockchain(difficulty, numblocks);

		//Generate blocks
		for (int i = 1; i < numblocks; i++) {
			bc.blockchain.add( Block.createBlock(difficulty) );
			bc.getBlockchain().get(i).setPrehash(bc.getBlockchain().get(i-1).getHash());
		}
		return bc;
	}
	
	public void printBlockchain() {
		for (int i = 0; i < this.getNumblocks(); i++) {
			System.out.println("Block " + i);
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
	}
}
