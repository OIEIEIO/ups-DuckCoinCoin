package blockchain;

public class Genesis extends Block {
		
	public Genesis() {
		
		this.numtransactions = 1;
		this.prehash = "0";
		this.hash = "0";
	}
}
