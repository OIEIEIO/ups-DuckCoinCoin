package blockchain;

public class App {

	/**
	 * Standalone main method
	 * No arguments
	 * No UI
	 */
	public static void main(String[] args) {
		int difficulty = 4;
		int numblocks = 10;
		boolean readJSON = false;
		
		Blockchain bc1;
		if (!readJSON) {
			bc1 = Blockchain.createBlockchain(difficulty, numblocks);
		} else { //JSON read
			bc1 = Json.BCJsonReader("blockchain.json");
		}
		
		//Print the blockchain
		bc1.printBlockchain(difficulty);
		
		//Let's corrupt the blockchain
		bc1.getBlockchain().get(2).getTransaction_list().get(0).setReceiver("aaaaabb");
		
		//While the blockchain is corrupted, recompute
		while ( !bc1.verifRoothash() || !bc1.verifChaining(difficulty) ) {
			bc1.recompute(difficulty);
			bc1.printBlockchain(difficulty);
		}
		System.out.println("Blockchain OK");
		
		//JSON write
		if (!readJSON) {
			Json.BCJsonWriter(bc1, "blockchain.json");
		}
	}

	/**
	 * Main method with UI
	 * @param difficulty
	 * @param numblocks
	 */
	public static String main(int difficulty, int numblocks) {

		boolean readJSON = false;
		
		Blockchain bc1;
		if (!readJSON) {
			bc1 = Blockchain.createBlockchain(difficulty, numblocks);
		} else { //JSON read
			bc1 = Json.BCJsonReader("blockchain.json");
		}
		
		//Print the blockchain (return a String)
		bc1.printBlockchain(difficulty);
		
		
		//Let's corrupt the blockchain
		bc1.getBlockchain().get(2).getTransaction_list().get(0).setReceiver("aaaaabb");
		
		//While the blockchain is corrupted, recompute
		while ( !bc1.verifRoothash() || !bc1.verifChaining(difficulty) ) {
			
			bc1.recompute(difficulty);
			
			//Print the blockchain (return a String)
			bc1.printBlockchain(difficulty);
		}
		System.out.println("Blockchain OK");
		
		
		//JSON write
		if (!readJSON) {
			Json.BCJsonWriter(bc1, "blockchain.json");
		}
		
		return bc1.blockchainToString(difficulty);
	}
}
