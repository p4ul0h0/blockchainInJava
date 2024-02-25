package com.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {
	private int index;
	private long timestamp;
	private String hash;
	private String previousHash;
	private String data;
	private int nonce;
	
	public Block(int index, long timestamp, String previousHash, String data) {
		this.index = index;
		this.timestamp = timestamp;
		this.previousHash = previousHash;
		this.data = data;
		nonce = 0;
		hash = Block.calculateHash(this);
	}
	
	public static String calculateHash(Block block) {
		if(block != null) {
			MessageDigest digest = null;
			
			try {
				digest = MessageDigest.getInstance("SHA-256");
			}catch (NoSuchAlgorithmException e) {
				return null;
			}
			
			String txt = block.str();
			final byte bytes[] = digest.digest(txt.getBytes());
			final StringBuilder builder = new StringBuilder();
			
			for (final byte b: bytes) {
				String hex = Integer.toHexString(0xff & b);
				if(hex.length() == 1) {
					builder.append('0');
				}
				builder.append(hex);
			}
			return builder.toString();
		}
		return null;
	}
	
	public void proofOfWork(int difficulty) {
		nonce = 0;
		
		while(!getHash().substring(0,  difficulty).equals(Utils.zeros(difficulty))) {
			nonce++;
			hash = Block.calculateHash(this);
		}
	}
	
	public String str() {
		return index + timestamp + previousHash + data + nonce;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
		builder.append("Block #").append(index).append(" [previousHash :").append(previousHash).append(", ")
		.append("timestamp: ").append(new Date(timestamp)).append(", ").append("data: ").append("'" + data + "'")
		.append(" hash: ").append(hash).append("]");
		return builder.toString();
	}
	
	
}
