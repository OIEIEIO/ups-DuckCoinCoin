package blockchain.UI;

import blockchain.App;

public class BlockChainMain {

	public static void main(String[] args) {
		BlockChainView view = new BlockChainView();
		new BlockChainFrame();
		App app = new App(view.getDifficulty(), view.getNbBlock());
	}

}
