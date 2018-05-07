package blockchain;

public class App {
	
	private int difficulty;
	private int numblocks;
	
	public App(int difficulty, int numblocks) {
		this.difficulty = difficulty;
		this.numblocks = numblocks;
	}

	public void main(String[] args) {

		boolean readJSON = false;
		
		Blockchain bc1;
		if (!readJSON) {
			bc1 = Blockchain.createBlockchain(this.difficulty, this.numblocks);
		} else { //JSON read
			bc1 = Json.BCJsonReader("blockchain.json");
		}
		
		//Print the blockchain
		bc1.printBlockchain(this.difficulty);
		
		//Let's corrupt the blockchain
		bc1.getBlockchain().get(2).getTransaction_list().get(0).setReceiver("aaaaabb");
		
		//While the blockchain is corrupted, recompute
		while ( !bc1.verifRoothash() || !bc1.verifChaining(this.difficulty) ) {
			bc1.recompute(this.difficulty);
			bc1.printBlockchain(this.difficulty);
		}
		System.out.println("Blockchain OK");
		
		//JSON write
		if (!readJSON) {
			Json.BCJsonWriter(bc1, "blockchain.json");
		}
	}

}
