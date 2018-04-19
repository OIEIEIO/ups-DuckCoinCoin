package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private int difficulty;		//mining difficulty
	private int numblocks;		//number of blocks
	private Block genesis;
	

	public Blockchain(int difficulty, int numblocks) {
		
		List<Block> blockchain = new ArrayList<Block>();	//list of blocks
		Genesis genesis = new Genesis();
		blockchain.add(genesis);
	}
	
	//Verify if the first block is the genesis block
	public boolean verifGenesis(List<Block> b) {
		return (b.get(0) instanceof Genesis);
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
