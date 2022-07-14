/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.account;
import org.libTAU4j.swig.byte_array_32;
import org.libTAU4j.swig.public_key;

/**
 * Account in libTAU
 *
 * @author taichen.liu
 */
public final class Account {

	private final byte[] peer;
	private final long balance;
	private final long nonce;
	//private final long effective_power;
	//private final long block_number;

    private final account act;

    public Account(byte[] peer, long balance, long nonce) {

		this.peer = peer;
		this.balance = balance;
		this.nonce = nonce;
		//this.block_number = block_number;
        
		byte_array_32 bpk = Vectors.bytes2byte_array_32(peer);
        public_key key = new public_key(bpk);
		this.act = new account(key, balance, nonce);
		//this.effective_power = this.act.effective_power();

	}

    public Account(account act) {

		this.peer = Vectors.byte_vector2bytes(act.peer().to_bytes());
		this.balance = act.balance();
		this.nonce = act.nonce();
		//this.effective_power = act.effective_power();		
		//this.block_number = act.block_number();		

		this.act = act;

	}

  	public long getBalance() {
    	return this.balance;
  	}

  	public long getNonce() {
    	return this.nonce;
  	}

    /*
  	public long getEffectivePower() {
    	return this.effective_power;
  	}

  	public long getBlockNumber() {
    	return this.block_number;
  	}
    */

	public account swig() {
    	return this.act;
	}

}
