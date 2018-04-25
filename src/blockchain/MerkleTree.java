package blockchain;

import java.util.List;

public class MerkleTree {

	public void merkleHash(Block b) {
		int numtransactions = b.getNumtransactions();
		List<Transaction> list = b.getTransaction_list();
		
		if (numtransactions % 2 == 0) {
			for (int i = 0; i < list.size(); i++) {
				//Hash.applySha256();
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				//Hash.applySha256();
			}
		}
	}
}
