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
import org.libTAU4j.swig.sha1_hash;

import java.math.BigInteger;

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
	private final BigInteger base_target;
	private final BigInteger cumulative_difficulty;
	private final byte[] generation_signature;
	private final byte[] state_root;
	private final byte[] news_root;
	private final Transaction tx;
	private final byte[] miner;
    /*
	private final UdpEndpoint endp;
	private final long miner_balance;
	private final long miner_nonce;
	private final long sender_balance;
	private final long sender_nonce;
	private final long receiver_balance;
	private final long receiver_nonce;
    */

    private final block blk;
    private final String genesis_blk_hash; //block hex string
    private final String blk_hash; //hex string

    public Block(byte[] chain_id, int version, long timestamp, long block_number,
			byte[] previous_block_hash, BigInteger base_target, BigInteger cumulative_difficulty,
			byte[] generation_signature, byte[] state_root, byte[] news_root, Transaction tx, byte[] miner) {

		this.chain_id = chain_id;
		this.version = version;
		this.timestamp = timestamp;
		this.block_number = block_number;
		this.previous_block_hash = previous_block_hash;
		this.base_target = base_target;
		this.cumulative_difficulty = cumulative_difficulty;
		this.state_root = state_root;
		this.news_root = news_root;
		this.generation_signature = generation_signature;
		this.tx = tx;
		this.miner = miner;
        /*
		this.miner_balance = miner_balance;
		this.miner_nonce = miner_nonce;
		this.sender_balance = sender_balance;
		this.sender_nonce = sender_nonce;
		this.receiver_balance = receiver_balance;
		this.receiver_nonce = receiver_nonce;
        */

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		block_version  bv = block_version.swigToEnum(version);
		sha1_hash sh_pbh = new sha1_hash(Vectors.bytes2byte_vector(previous_block_hash));
		sha1_hash sh_sign = new sha1_hash(Vectors.bytes2byte_vector(generation_signature));
		sha1_hash sh_sr = new sha1_hash(Vectors.bytes2byte_vector(state_root));
		sha1_hash sh_nr = new sha1_hash(Vectors.bytes2byte_vector(news_root));
		public_key  pk_miner = new public_key(Vectors.bytes2byte_array_32(miner));

		this.blk = new block(bv_chain_id, bv, timestamp, block_number,
					  sh_pbh, base_target, cumulative_difficulty,
					  sh_sign, sh_sr, sh_nr, tx.swig(), pk_miner);

        /*
		if(blk.end_point() != null) {
			this.endp = new UdpEndpoint(blk.end_point());
		} else {
			this.endp = null;
		}
        */
		this.genesis_blk_hash = this.blk.genesis_block_hash().to_hex();
		this.blk_hash = this.blk.sha1().to_hex();
	}

    public Block(byte[] chain_id, int version, long timestamp, long block_number,
			byte[] previous_block_hash, BigInteger base_target, BigInteger cumulative_difficulty,
			byte[] generation_signature, byte[] state_root, byte[] news_root, Transaction tx, byte[] miner, UdpEndpoint endp) {

		this.chain_id = chain_id;
		this.version = version;
		this.timestamp = timestamp;
		this.block_number = block_number;
		this.previous_block_hash = previous_block_hash;
		this.base_target = base_target;
		this.cumulative_difficulty = cumulative_difficulty;
		this.generation_signature = generation_signature;
		this.state_root = state_root;
		this.news_root = news_root;
		this.tx = tx;
		this.miner = miner;
        /*
		this.miner_balance = miner_balance;
		this.miner_nonce = miner_nonce;
		this.sender_balance = sender_balance;
		this.sender_nonce = sender_nonce;
		this.receiver_balance = receiver_balance;
		this.receiver_nonce = receiver_nonce;
		this.endp = endp;
        */

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		block_version  bv = block_version.swigToEnum(version);
		sha1_hash sh_pbh = new sha1_hash(Vectors.bytes2byte_vector(previous_block_hash));
		sha1_hash sh_sign = new sha1_hash(Vectors.bytes2byte_vector(generation_signature));
		sha1_hash sh_sr = new sha1_hash(Vectors.bytes2byte_vector(state_root));
		sha1_hash sh_nr = new sha1_hash(Vectors.bytes2byte_vector(news_root));
		public_key  pk_miner = new public_key(Vectors.bytes2byte_array_32(miner));

		this.blk = new block(bv_chain_id, bv, timestamp, block_number,
					  sh_pbh, base_target, cumulative_difficulty,
					  sh_sign, sh_sr, sh_nr, tx.swig(), pk_miner);

		this.genesis_blk_hash = this.blk.genesis_block_hash().to_hex();
		this.blk_hash = this.blk.sha1().to_hex();
	}

    public Block(block blk) {
		this.chain_id = Vectors.byte_vector2bytes(blk.chain_id());
		this.version = blk.version().swigValue();
		this.timestamp = blk.timestamp();
		this.block_number = blk.block_number();
		this.previous_block_hash = Vectors.byte_vector2bytes(blk.previous_block_hash().to_bytes());
		this.base_target = blk.base_target();
		this.cumulative_difficulty = blk.cumulative_difficulty();
		this.generation_signature = Vectors.byte_vector2bytes(blk.generation_signature().to_bytes());
		this.state_root = Vectors.byte_vector2bytes(blk.state_root().to_bytes());
		this.news_root = Vectors.byte_vector2bytes(blk.news_root().to_bytes());
		if(blk.tx() != null) {
			this.tx = new Transaction(blk.tx());
		} else {
			this.tx = null;
		}
		this.miner = Vectors.byte_vector2bytes(blk.miner().to_bytes());
        /*
		this.miner_balance = blk.miner_balance();
		this.miner_nonce = blk.miner_nonce();
		this.sender_balance = blk.sender_balance();
		this.sender_nonce = blk.sender_nonce();
		this.receiver_balance = blk.receiver_balance();
		this.receiver_nonce = blk.receiver_nonce();
		if(blk.end_point() != null) {
			this.endp = new UdpEndpoint(blk.end_point());
		} else {
			this.endp = null;
		}
        */

		this.blk = blk;
		this.genesis_blk_hash = blk.genesis_block_hash().to_hex();
		this.blk_hash = blk.sha1().to_hex();

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

  	public BigInteger getBaseTarget() {
    	return this.base_target;
  	}

  	public BigInteger getCumulativeDifficulty() {
    	return this.cumulative_difficulty;
  	}

  	public byte[] getGenerationSignature() {
    	return this.generation_signature;
  	}

  	public byte[] getStateRoot() {
    	return this.state_root;
  	}

  	public byte[] getNewsRoot() {
    	return this.news_root;
  	}

  	public byte[] getMiner() {
    	return this.miner;
  	}

    /*
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

  	public UdpEndpoint getEndp() {
    	return this.endp;
  	}
    */

  	public Transaction getTx() {
    	return this.tx;
  	}

	public String GenesisBlockHash() {
    	return this.genesis_blk_hash;
	}

	public String Hash() {
    	return this.blk_hash;
	}

	public long Size() {
    	return 84 + chain_id.length + tx.Size() 
			      + previous_block_hash.length + generation_signature.length 
				  + miner.length;
	}

	public block swig() {
    	return this.blk;
	}

}
