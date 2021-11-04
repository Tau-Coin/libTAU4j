/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.byte_vector;
import org.libTAU4j.swig.block;
import org.libTAU4j.swig.block_version;
import org.libTAU4j.swig.public_key;
import org.libTAU4j.swig.sha256_hash;

/**
 * The Entry class represents one node in a bencoded hierarchy. It works as a
 * variant type, it can be either a list, a dictionary, an integer
 * or a string.
 *
 * @author gubatron
 * @author aldenml
 */
public final class Block {

	private final byte[] chain_id;
	private final int version;
	private final long timestamp;
	private final long block_number;
	private final byte[] previous_block_hash;
	private final long base_target;
	private final long cumulative_difficulty;
	private final byte[] generation_signature;
	private final Transaction tx;
	private final byte[] miner;
	private final long miner_balance;
	private final long miner_nonce;
	private final long sender_balance;
	private final long sender_nonce;
	private final long receiver_balance;
	private final long receiver_nonce;

    private final block bk;

    public Block(byte[] chain_id, int version, long timestamp, long block_number,
			byte[] previous_block_hash, long base_target, long cumulative_difficulty,
			byte[] generation_signature, Transaction tx, 
			byte[] miner, long miner_balance, long miner_nonce,
			long sender_balance, long sender_nonce, 
			long receiver_balance, long receiver_nonce) {

		this.chain_id = chain_id;
		this.version = version;
		this.timestamp = timestamp;
		this.block_number = block_number;
		this.previous_block_hash = previous_block_hash;
		this.base_target = base_target;
		this.cumulative_difficulty = cumulative_difficulty;
		this.generation_signature = generation_signature;
		this.tx = tx;
		this.miner = miner;
		this.miner_balance = miner_balance;
		this.miner_nonce = miner_nonce;
		this.sender_balance = sender_balance;
		this.sender_nonce = sender_nonce;
		this.receiver_balance = receiver_balance;
		this.receiver_nonce = receiver_nonce;

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		block_version  bv = block_version.swigToEnum(version);
		sha256_hash sh_pbh = new sha256_hash(Vectors.bytes2byte_vector(previous_block_hash));
		sha256_hash sh_sign = new sha256_hash(Vectors.bytes2byte_vector(generation_signature));
		public_key  pk_miner = new public_key(Vectors.bytes2byte_array_32(miner));

		this.bk = new block(bv_chain_id, bv, timestamp, block_number,
					  sh_pbh, base_target, cumulative_difficulty,
					  sh_sign, tx.swig(), 
					  pk_miner, miner_balance, miner_nonce,
					  sender_balance, sender_nonce, receiver_balance, receiver_nonce);
	}

    public Block(block bk) {

		this.chain_id = Vectors.byte_vector2bytes(bk.chain_id());
		this.version = bk.version().swigValue();
		this.timestamp = bk.timestamp();
		this.block_number = bk.block_number();
		this.previous_block_hash = Vectors.byte_vector2bytes(bk.previous_block_hash().to_bytes());
		this.base_target = bk.base_target();
		this.cumulative_difficulty = bk.cumulative_difficulty();
		this.generation_signature = Vectors.byte_vector2bytes(bk.generation_signature().to_bytes());
		this.tx = new Transaction(bk.tx());
		this.miner = Vectors.byte_vector2bytes(bk.miner().to_bytes());
		this.miner_balance = bk.miner_balance();
		this.miner_nonce = bk.miner_nonce();
		this.sender_balance = bk.sender_balance();
		this.sender_nonce = bk.sender_nonce();
		this.receiver_balance = bk.receiver_balance();
		this.receiver_nonce = bk.receiver_nonce();
		
		this.bk = bk;

	}

  	public byte[] getChainID() {
    	return this.chain_id;
  	}

  	public int getVersion() {
    	return this.version;
  	}

  	public long getTimestamp() {
    	return this.timestamp;
  	}

  	public long getBlockNumber() {
    	return this.block_number;
  	}

  	public byte[] getPreviousBlockHash() {
    	return this.previous_block_hash;
  	}

  	public long getBaseTarget() {
    	return this.base_target;
  	}

  	public long getCumulativeDifficulty() {
    	return this.cumulative_difficulty;
  	}

  	public byte[] getGenerationSignature() {
    	return this.generation_signature;
  	}

  	public byte[] getMiner() {
    	return this.miner;
  	}

  	public long getMinerBalance() {
    	return this.miner_balance;
  	}

  	public long getMinerNonce() {
    	return this.miner_nonce;
  	}

  	public long getSenderBalance() {
    	return this.sender_balance;
  	}

  	public long getSenderNonce() {
    	return this.sender_nonce;
  	}

  	public long getReceiverBalance() {
    	return this.receiver_balance;
  	}

  	public long getReceiverNonce() {
    	return this.receiver_nonce;
  	}

	public block swig() {
    	return this.bk;
	}

}