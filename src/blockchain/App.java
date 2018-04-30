package blockchain;

public class App {

	public static void main(String[] args) {
		Blockchain bc1 = Blockchain.createBlockchain(4, 10);
		bc1.printBlockchain();
	}

}
