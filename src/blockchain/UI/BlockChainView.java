package blockchain.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class BlockChainView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField nbBlock;
	private JTextField difficulty;
	private JButton start;
	private JTextArea result;
	
	public BlockChainView() {
		
		BlockChainController controller = new BlockChainController(this);
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(3, 1));
		this.add(top, BorderLayout.NORTH);
		
		JPanel nbBlockField = new JPanel();
		nbBlockField.setLayout(new GridLayout(1, 2));
		this.nbBlock = new JTextField();
		nbBlockField.add(new JLabel("Number of blocks"));
		nbBlockField.add(this.nbBlock);
		top.add(nbBlockField);
		
		JPanel difficultyField = new JPanel();
		difficultyField.setLayout(new GridLayout(1, 2));
		this.difficulty = new JTextField();
		difficultyField.add(new JLabel("Difficulty"));
		difficultyField.add(this.difficulty);
		top.add(difficultyField);
		
		this.start = new JButton("Create blockchain");
		start.addActionListener(controller);
		top.add(this.start);
		
		this.result = new JTextArea(50, 100);
		this.result.setEditable(false);
		this.result.setFont(new Font("Helvetica", Font.PLAIN, 14));
		//this.result.setText(blockchainToString(int difficulty));

		this.add(this.result, BorderLayout.SOUTH);
	}
	
	public boolean isNumeric(String str) {  
		try {  
	    	Integer.parseInt(str);  
		} catch(Exception e) {  
			return false;  
		}
		return true;  
	}
	
	public int getNbBlock() {
		if (isNumeric(this.nbBlock.getText().toString())) {
			return Integer.parseInt(this.nbBlock.getText().toString());
		}
		return -1;
	}
	
	public int getDifficulty() {
		if (isNumeric(this.difficulty.getText().toString())) {
			return Integer.parseInt(this.difficulty.getText().toString());
		}
		return -1;
	}
	
	public void setResult(String text) {
		this.result.setText(text);
		JScrollPane scroll = new JScrollPane(this.result);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.remove(this.result);
		this.add(scroll, BorderLayout.SOUTH);
	}
}
