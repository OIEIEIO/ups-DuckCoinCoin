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
		boolean readJSON = false; //only set readJSON to true if you are importing a JSON file
		
		Blockchain bc1;
		
		if (!readJSON) { //then create a random blockchain from scratch
			bc1 = Blockchain.createBlockchain(difficulty, numblocks);
		} else { //then import a blockchain from a JSON file
			bc1 = Json.BCJsonReader("blockchain.json");
		}
		
		//Print the blockchain
		bc1.printBlockchain(difficulty);
		
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

		boolean readJSON = false; //only set readJSON to true if you are importing a JSON file
		String outBlockchain = "";
		
		Blockchain bc1;
		
		if (!readJSON) { //then create a random blockchain from scratch
			bc1 = Blockchain.createBlockchain(difficulty, numblocks);
		} else { //then import a blockchain from a JSON file
			bc1 = Json.BCJsonReader("blockchain.json");
		}
		
		//Print the blockchain (return a String)
		outBlockchain = bc1.blockchainToString(difficulty);

		
		//While the blockchain is corrupted, recompute
		while ( !bc1.verifRoothash() || !bc1.verifChaining(difficulty) ) {
			
			bc1.recompute(difficulty);
			
			//Print the blockchain (return a String)
			outBlockchain += bc1.blockchainToString(difficulty);
		}
		outBlockchain += "Blockchain OK\n";
		
		
		//JSON write
		if (!readJSON) {
			Json.BCJsonWriter(bc1, "blockchain.json");
		}
		
		return outBlockchain;
	}
}
