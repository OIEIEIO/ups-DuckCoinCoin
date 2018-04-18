package blockchain;

public class Genesis extends Block {
	
	private int numtransactions = 1;
	public Genesis() {
		super.nonce = 0;
		super.prehash = "0";
	}
}
