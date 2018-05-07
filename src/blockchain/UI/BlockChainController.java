package blockchain.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import blockchain.App;

public class BlockChainController implements ActionListener {

	enum Etat {START, FINISH};
	private Etat etat;
	
	private BlockChainView view;
	
	public BlockChainController(BlockChainView view) {
		this.etat = Etat.START;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (etat == Etat.START) {
			int nbBlock = this.view.getNbBlock();
			int difficulty = this.view.getDifficulty();
			if (nbBlock != -1 && difficulty != -1) {
				
				//Create the blockchain and print it
				this.view.setResult(App.main(difficulty, nbBlock));

				this.etat = Etat.FINISH;
			}
		}
	}

}
