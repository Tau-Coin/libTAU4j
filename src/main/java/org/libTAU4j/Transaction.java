/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.byte_vector;
import org.libTAU4j.swig.transaction;

/**
 * The Entry class represents one node in a bencoded hierarchy. It works as a
 * variant type, it can be either a list, a dictionary, an integer
 * or a string.
 *
 * @author gubatron
 * @author aldenml
 */
public final class Transaction {

	private final byte[] chain_id;
	private final int version;
	private final long timestamp;
	private final byte[] sender;
	private final byte[] receiver;
	private final long nonce;
	private final long amount;
	private final long fee;
	private final byte[] payload;

    private final transaction tx;

    public Transaction(byte[] chain_id, int version, long timestamp, 
			byte[] sender, byte[] receiver, 
			long nonce, long amount, long fee,
			byte[] payload) {

		this.chain_id = chain_id;
		this.version = version;
		this.timestamp = timestamp;
		this.sender = sender;
		this.receiver = receiver;
		this.nonce = nonce;
		this.amount = amount;
		this.fee = fee;
		this.payload = payload;

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		tx_version  tv = tx_version.swigToEnum(version);
		public_key  pk_sender = new public_key(Vectors.bytes2byte_array_32(sender));
		public_key  pk_receiver = new public_key(Vectors.bytes2byte_array_32(receiver));
		byte_vector bv_payload = Vectors.bytes2byte_vector(payload);
		this.tx = new transaction(bv_chain_id, tv, timestamp,
					   pk_sender, pk_receiver, nonce, amount, fee, bv_payload);
	}

    public Transaction(transaction tx) {

		this.chain_id = Vectors.byte_vector2bytes(tx.chain_id());
		this.version = tx.version().swigValue();
		this.timestamp = tx.timestamp();
		this.sender = Vectors.byte_vector2bytes(tx.sender().get_bytes());
		this.receiver = Vectors.byte_vector2bytes(tx.receiver().get_bytes());
		this.nonce = tx.nonce();
		this.amount = tx.amount();
		this.fee = tx.fee();
		this.payload = Vectors.byte_vector2bytes(tx.payload());
		
		this.tx = tx;

	}

  	public byte[] getChainID() {
    	return this.chain_id;
  	}

  	public byte[] getVersion() {
    	return this.version;
  	}

  	public long getTimestamp() {
    	return this.timestamp;
  	}

  	public byte[] getSender() {
    	return this.sender;
  	}

  	public byte[] getReceiver() {
    	return this.receiver;
  	}

  	public long getNonce() {
    	return this.nonce;
  	}

  	public long getAmount() {
    	return this.amount;
  	}

  	public long getFee() {
    	return this.fee;
  	}

  	public byte[] getPayload() {
    	return this.payload;
  	}

	public transaction swig() {
    	return this.tx;
	}

}
