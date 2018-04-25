package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	public int difficulty; //mining difficulty
	private int numblocks; //number of blocks
	private Block genesis;
	List<Block> blockchain;	//list of blocks
	
	public int getDifficulty() {
		return difficulty;
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}

	public Blockchain(int difficulty, int numblocks) {
		
		this.blockchain = new ArrayList<Block>();	//list of blocks
		Genesis genesis = new Genesis();
		this.blockchain.add(genesis);
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
	
	//Count how many "0" in a row at the beginning of hash
	public int countZero(Block b) {
		int count = 0, i = 0, j = 1;
		String h = b.getHash();
		do {
			++count;
		} while (h.substring(++i, ++j) == "0");
		return count;
	}
	
	//Compute the hash again if it doesn't pass the test
	public void mining(Blockchain b) {
		int n; //nonce
		List<Block> bl = b.getBlockchain();
		for (int i = 0; i < bl.size(); i++) {
			n = bl.get(i).getNonce();
			if (countZero(bl.get(i)) < b.getDifficulty()) {
				do {
					bl.get(i).setNonce(++n);
					// TODO recommencer le calcul du hash
				} while (countZero(bl.get(i)) < b.getDifficulty());
			}
		}

	}
}
