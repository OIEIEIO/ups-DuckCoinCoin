package blockchain;

public class Genesis extends Block {

	public Genesis() {
		super.nonce = 0;
		super.hash = "0";
	}
}
