package blockchain;

public class App {

	public static void main(String[] args) {
		int difficulty = 4;
		
		Blockchain bc1 = Blockchain.createBlockchain(difficulty, 10);
		bc1.printBlockchain(difficulty);
		
		bc1.getBlockchain().get(2).getTransaction_list().get(3).setReceiver("aaaaabb");
		
		while ( !bc1.verifRoothash() || !bc1.verifChaining(difficulty) ) {
			bc1.recompute(difficulty);
			bc1.printBlockchain(difficulty);
		}
		System.out.println("Blockchain OK");
	}

}
