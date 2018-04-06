package blockchain;

public class Blockchain {
	
	private int difficulty;		// mining difficulty
	
	private int numblocks;		// number of blocks
	
	Block block_list[] = new Block[100];	// list of blocks (including genesis)
}
