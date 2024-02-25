package com.blockchain;

public class Main {
	public static void main(String[] args) {
		Blockchain blockchain = new Blockchain(4);
		blockchain.addBlock(blockchain.newBlock("Teste 1"));
		blockchain.addBlock(blockchain.newBlock("Teste 2"));
		blockchain.addBlock(blockchain.newBlock("Teste 3"));
		System.out.println(blockchain);
		System.out.println("Is blockchain valid? :" + blockchain.isBlockchainValid());
		
		blockchain.addBlock(new Block(4, System.currentTimeMillis(), "25252f3f2f", "Valid Block"));
		System.out.println(blockchain);
		System.out.println("Is blockchain valid? : " + blockchain.isBlockchainValid());
	}
}
