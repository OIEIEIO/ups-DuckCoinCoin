package blockchain.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				//
				// Ici tu mets le code qui lance la blockchain etc...
				// Puis quand ça a fini de mouliner tu appelles cette fonction pour écrire dans la zone de texte
				// this.view.setResult(text); // text c'est la grosse String avec tout le texte en console
				//
				this.etat = Etat.FINISH;
			}
		}
	}

}
