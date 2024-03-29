/* * Copyright (c) 2018-2020, Alden Torres
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
	private final int type;
	private final long timestamp;
	private final byte[] sender;
	private final byte[] receiver;
	private final long nonce;
	private final long amount;
	private final long fee;
	private final byte[] payload;
	private final sha1_hash previous_hash;
	private final sha1_hash txid;

    private final transaction tx;

    public Transaction(byte[] chain_id, long timestamp, 
			byte[] sender, byte[] phash, byte[] payload) {

		this.chain_id = chain_id;
		this.timestamp = timestamp;
		this.sender = sender;
		this.receiver = null;
		this.nonce = 0;
		this.amount = 0;
		this.fee = 0;
		if(phash != null)
			this.previous_hash = new sha1_hash(Vectors.bytes2byte_vector(phash));
		else
			this.previous_hash = null;
		this.payload = payload;

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		public_key  pk_sender = new public_key(Vectors.bytes2byte_array_32(sender));
		byte_vector bv_payload = Vectors.bytes2byte_vector(payload);
		this.tx = new transaction(bv_chain_id, timestamp, pk_sender, previous_hash, bv_payload);

        this.type = this.tx.type().swigValue();
		this.txid = this.tx.sha1();
	}

    public Transaction(byte[] chain_id, long timestamp, 
			byte[] sender, byte[] receiver, 
			long nonce, long amount, long fee,
			byte[] payload) {

		this.chain_id = chain_id;
		this.timestamp = timestamp;
		this.sender = sender;
		this.receiver = receiver;
		this.nonce = nonce;
		this.amount = amount;
		this.fee = fee;
		this.previous_hash = null;
		this.payload = payload;

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(chain_id);
		public_key  pk_sender = new public_key(Vectors.bytes2byte_array_32(sender));
		public_key  pk_receiver = new public_key(Vectors.bytes2byte_array_32(receiver));
		byte_vector bv_payload = Vectors.bytes2byte_vector(payload);
		this.tx = new transaction(bv_chain_id, timestamp,
					   pk_sender, pk_receiver, nonce, amount, fee, bv_payload);

        this.type = this.tx.type().swigValue();
		this.txid = this.tx.sha1();
	}

    public Transaction(transaction tx) {

		this.chain_id = Vectors.byte_vector2bytes(tx.chain_id());
		this.type = tx.type().swigValue();
		this.timestamp = tx.timestamp();
		this.sender = Vectors.byte_vector2bytes(tx.sender().to_bytes());
		this.receiver = Vectors.byte_vector2bytes(tx.receiver().to_bytes());
		this.nonce = tx.nonce();
		this.amount = tx.amount();
		this.fee = tx.fee();
		if(tx.previous_hash() != null)
			this.previous_hash = new sha1_hash(tx.previous_hash());
		else
			this.previous_hash = null;
		this.payload = Vectors.byte_vector2bytes(tx.payload());

		byte_vector bv_chain_id = Vectors.bytes2byte_vector(this.chain_id);
		public_key  pk_sender = new public_key(Vectors.bytes2byte_array_32(this.sender));
		public_key  pk_receiver = new public_key(Vectors.bytes2byte_array_32(this.receiver));
		byte_vector bv_payload = Vectors.bytes2byte_vector(this.payload);

		this.tx = new transaction(bv_chain_id, this.timestamp,
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
    	return this.tx.version().swigValue();
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

  	public sha1_hash getPreviousHash() {
    	return this.previous_hash;
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
