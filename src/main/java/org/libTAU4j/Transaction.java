/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.byte_vector;
import org.libTAU4j.swig.byte_array_32;
import org.libTAU4j.swig.byte_array_64;
import org.libTAU4j.swig.public_key;
import org.libTAU4j.swig.secret_key;
import org.libTAU4j.swig.sha1_hash;
import org.libTAU4j.swig.transaction;
import org.libTAU4j.swig.tx_version;

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
	private final int type;
	private final long timestamp;
	private final byte[] sender;
	private final byte[] receiver;
	private final long nonce;
	private final long amount;
	private final long fee;
	private final byte[] payload;
	private final sha1_hash txid;

    private final transaction tx;

    public Transaction(byte[] chain_id, int version, long timestamp, 
			byte[] sender, byte[] payload) {

		this.chain_id = chain_id;
		this.version = version;
		this.timestamp = timestamp;
		this.sender = sender;
		this.receiver = null;
		this.nonce = 0;
		this.amount = 0;
		this.fee = 0;
		this.payload = payload;

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		tx_version  tv = tx_version.swigToEnum(version);
		public_key  pk_sender = new public_key(Vectors.bytes2byte_array_32(sender));
		byte_vector bv_payload = Vectors.bytes2byte_vector(payload);
		this.tx = new transaction(bv_chain_id, tv, timestamp, pk_sender, bv_payload);

        this.type = this.tx.type().swigValue();
		this.txid = this.tx.sha1();
	}

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

        this.type = this.tx.type().swigValue();
		this.txid = this.tx.sha1();
	}

    public Transaction(transaction tx) {

		this.chain_id = Vectors.byte_vector2bytes(tx.chain_id());
		this.version = tx.version().swigValue();
		this.type = tx.type().swigValue();
		this.timestamp = tx.timestamp();
		this.sender = Vectors.byte_vector2bytes(tx.sender().to_bytes());
		this.receiver = Vectors.byte_vector2bytes(tx.receiver().to_bytes());
		this.nonce = tx.nonce();
		this.amount = tx.amount();
		this.fee = tx.fee();
		this.payload = Vectors.byte_vector2bytes(tx.payload());

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(this.chain_id);
		tx_version  tv = tx_version.swigToEnum(this.version);
		public_key  pk_sender = new public_key(Vectors.bytes2byte_array_32(this.sender));
		public_key  pk_receiver = new public_key(Vectors.bytes2byte_array_32(this.receiver));
		byte_vector bv_payload = Vectors.bytes2byte_vector(this.payload);

		this.tx = new transaction(bv_chain_id, tv, this.timestamp,
					   pk_sender, pk_receiver, this.nonce, this.amount, this.fee, bv_payload);

		this.txid = new sha1_hash(tx.sha1());
	}

	public void sign(String publicKey, String secretKey) {
		byte[] pk = Hex.decode(publicKey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key1 = new public_key(bpk);

		byte[] sk = Hex.decode(secretKey);
		byte_array_64 bsk = Vectors.bytes2byte_array_64(sk);
		secret_key key2 = new secret_key(bsk);

		this.tx.sign(key1, key2);
	}

	public void sign(byte[] publicKey, byte[] secretKey) {
		byte_array_32 bpk = Vectors.bytes2byte_array_32(publicKey);
        public_key key1 = new public_key(bpk);

		byte_array_64 bsk = Vectors.bytes2byte_array_64(secretKey);
		secret_key key2 = new secret_key(bsk);

		this.tx.sign(key1, key2);
	}

  	public byte[] getChainID() {
    	return this.chain_id;
  	}

  	public int getVersion() {
    	return this.version;
  	}

  	public int getType() {
    	return this.type;
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

  	public sha1_hash getTxID() {
    	return this.txid;
  	}

  	public boolean verifySignature() {
    	return this.tx.verify_signature();
  	}

	public long Size(){
		return this.tx.get_encode_size();
	}

	public transaction swig() {
    	return this.tx;
	}

}
