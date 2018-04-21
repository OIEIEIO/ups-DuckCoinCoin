package blockchain;

public class Genesis extends Block {
	
	private int numtransactions = 1;
	public Genesis() {
		super.prehash = "0";
	}
}
