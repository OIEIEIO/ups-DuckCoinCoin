package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	public int difficulty; //mining difficulty
	private int numblocks; //number of blocks
	private Block genesis;
	List<Block> blockchain;	//list of blocks
	
	public Blockchain(int difficulty, int numblocks) {
		
		this.numblocks = numblocks;
		this.blockchain = new ArrayList<Block>();	//list of blocks
		Genesis genesis = new Genesis();
		this.blockchain.add(genesis);
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
}
