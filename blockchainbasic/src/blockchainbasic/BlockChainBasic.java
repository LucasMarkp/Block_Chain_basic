package blockchainbasic;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class BlockChainBasic {

		public static ArrayList<Block> blockchain = new ArrayList<Block>();
		public static int difficulty = 5 ;/*
											Dependendo do nivel da dificildade demora mais exemplo dificuldade 5 demora cerca de 2 segundo para finlizar o programa 
											enquanto 0 e 1 é quase intantaneo o funcionamento 
											*/
		
		public static void main(String...args) {
		
			//BLoco 1 
		blockchain.add( new Block("Meu primeiro Bloco","0"));
		System.out.println("Hash de Bloco 1:");
		blockchain.get(0).mineBlock(difficulty);
			//Bloco 2
		blockchain.add( new Block("Esse é o Segundo Bloco", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Hash de Bloco 2:");
		blockchain.get(1).mineBlock(difficulty);
			//Bloco 3
		blockchain.add( new Block("Esse ai é o terceiro Bloco", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Hash de Bloco 3");
		blockchain.get(2).mineBlock(difficulty);
		/*
		 * Bloco 4 bloco teste
		 * 
		//BLoco 4 Bloco teste
		blockchain.add(new Block("teste te Bloco numero 4",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Hash Do Bloco 4");
		blockchain.get(2).mineBlock(difficulty);
		*/	
		System.out.println("\nBlockchain é valido:" + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("Cadeia de Blocos:");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {

		Block currentBlock;
		Block previusBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		
		for(int i = 1; i < blockchain.size(); i++) {
				
				currentBlock = blockchain.get(i);
				previusBlock = blockchain.get(i-1);
				
			if(!previusBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("O hash do arquivo baixado não é igual ao hash do arquivo original:");
				return false;
			}
			if(!previusBlock.hash.equals(previusBlock.previousHash)) {
				System.out.println("O hash do bloco de transação atual não é igual ao hash do bloco anterior:");
				return false;
			}
		}
		return true;
	}
}
