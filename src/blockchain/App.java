package blockchain;

public class App {

	public static void main(String[] args) {
		int difficulty = 4;
		
		Blockchain bc1 = Blockchain.createBlockchain(difficulty, 10);
		bc1.printBlockchain(difficulty);
	}

}
