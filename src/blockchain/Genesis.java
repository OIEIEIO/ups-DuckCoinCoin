package blockchain;

import java.util.ArrayList;

public class Genesis extends Block {
	
	//================================================================================
	// Properties
	//================================================================================
	
	private Transaction transactionGenesis;
	
	//================================================================================
	// Constructors
	//================================================================================
	
	public Genesis() {
		
		this.numtransactions = 1;
		this.prehash = "0";
		this.roothash = "0";
		this.hash = "0";
		this.transaction_list = new ArrayList<Transaction>(); //list of transactions
		transactionGenesis = new Transaction();
		this.transaction_list.add(transactionGenesis);
	}
}
