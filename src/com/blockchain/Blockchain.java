package com.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	private int difficulty;
	private List<Block> chain;
	
	public Blockchain(int difficulty) {
		this.difficulty = difficulty;
		chain = new ArrayList<>();
		Block block = new Block(0, System.currentTimeMillis(), null, "Genesis Block");
		block.proofOfWork(difficulty);
		chain.add(block);
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public Block latestBlock() {
		return chain.get(chain.size() - 1);
	}
	
	public Block newBlock(String data) {
		Block latestBlock = latestBlock();
		return new Block(latestBlock.getIndex() + 1, System.currentTimeMillis(), latestBlock.getHash(), data);
	}
	
	public void addBlock(Block block) {
		if(block != null) {
			block.proofOfWork(difficulty);
			chain.add(block);
		}
	}
	
	public boolean isFirstBlockValid() {
		Block firstBlock = chain.get(0);
		
		if(firstBlock.getIndex() != 0) {
			return false;
		}
		
		if(firstBlock.getPreviousHash() != null) {
			return false;
		}
		
		if(firstBlock.getHash() == null || !Block.calculateHash(firstBlock).equals(firstBlock.getHash())) {
			return false;
		}
		
		return true;
	}
	
	public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
		if(newBlock != null && previousBlock != null) {
			if(previousBlock.getIndex() + 1 != newBlock.getIndex()) {
				return false;
			}
			if(newBlock.getPreviousHash() != previousBlock.getHash()) {
				return false;
			}
			if(newBlock.getHash() == null || !Block.calculateHash(newBlock).equals(newBlock.getHash())) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean isBlockchainValid() {
		if(!isFirstBlockValid()) {
			return false;
		}
		
		for(int i = 1; i < chain.size(); i++) {
			Block currentBlock = chain.get(i);
			Block previousBlock = chain.get(i - 1);
			
			if(!isValidNewBlock(currentBlock, previousBlock)) {
				return false;
			}
		}
		
		return true;
	}
	
	  public String toString() {
		  StringBuilder builder = new StringBuilder();

		  for (Block block : chain) {
			  builder.append(block).append("\n");
		  }

		  return builder.toString();
	  }
	
}
