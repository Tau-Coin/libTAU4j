/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.account;

/**
 * Account in libTAU
 *
 * @author taichen.liu
 */
public final class Account {

	private final long balance;
	private final long nonce;
	private final long note_timestamp;
	private final long effective_power;
	private final long block_number;

    private final account act;

    public Account(long balance, long nonce, long note_timestamp, long block_number) {

		this.balance = balance;
		this.nonce = nonce;
		this.note_timestamp = note_timestamp;
		this.block_number = block_number;

		this.act = new account(balance, nonce, note_timestamp, block_number);
		this.effective_power = this.act.effective_power();

	}

    public Account(long balance, long nonce, long note_timestamp, long effective_power, long block_number) {

		this.balance = balance;
		this.nonce = nonce;
		this.note_timestamp = note_timestamp;
		this.effective_power = effective_power;
		this.block_number = block_number;
		
		this.act = new account(balance, nonce, note_timestamp, effective_power, block_number);

	}

    public Account(account act) {

		this.balance = act.balance();		
		this.nonce = act.nonce();		
		this.note_timestamp = act.note_timestamp();		
		this.effective_power = act.effective_power();		
		this.block_number = act.block_number();		

		this.act = act;

	}

  	public long getBalance() {
    	return this.balance;
  	}

  	public long getNonce() {
    	return this.nonce;
  	}

  	public long getNoteTimestamp() {
    	return this.note_timestamp;
  	}

  	public long getEffectivePower() {
    	return this.effective_power;
  	}

  	public long getBlockNumber() {
    	return this.block_number;
  	}

	public account swig() {
    	return this.act;
	}

}
