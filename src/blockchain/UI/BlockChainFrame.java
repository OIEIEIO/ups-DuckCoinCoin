package blockchain.UI;

import javax.swing.JFrame;

public class BlockChainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public BlockChainFrame() {
		this.setTitle("DuckCoinCoin - Blockchain generator");
		this.setContentPane(new BlockChainView());
		this.pack();
		
//		this.setSize(300, 200);
	
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setAlwaysOnTop(true);
		
		this.setVisible(true);
	}
}
