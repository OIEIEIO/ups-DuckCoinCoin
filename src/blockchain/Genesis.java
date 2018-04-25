package blockchain;

public class Genesis extends Block {
	
	private int numtransactions;
	
	public Genesis() {
		
		this.numtransactions = 1;
		super.prehash = "0";
	}
}
