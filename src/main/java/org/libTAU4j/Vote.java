/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.Entry;
import org.libTAU4j.swig.vote;
import org.libTAU4j.swig.sha256_hash;

import java.math.BigInteger;

/**
 * Vote in libTAU
 *
 * @author taichen.liu
 */
public final class Vote {

	private final sha256_hash block_hash;
	private final BigInteger cumulative_difficulty;
	private final long block_number;
	private final long count;

    private final vote vt;

    public Vote(sha256_hash block_hash, BigInteger cumulative_difficulty, long block_number) {

		this.block_hash = block_hash;
		this.cumulative_difficulty = cumulative_difficulty;
		this.block_number = block_number;
		
		this.vt = new vote(block_hash, cumulative_difficulty, block_number);
		this.count = this.vt.count();
	}

    public Vote(vote vt) {
		this.vt = vt;
		this.block_hash = vt.block_hash();
		this.cumulative_difficulty = vt.cumulative_difficulty();
		this.block_number = vt.block_number();
		this.count = vt.count();
	}

    public Vote(Entry e) {
		this.vt = new vote(e.swig());
		this.block_hash = vt.block_hash();
		this.cumulative_difficulty = vt.cumulative_difficulty();
		this.block_number = vt.block_number();
		this.count = vt.count();
	}

    public Vote(String s) {
		this.vt = new vote(s);
		this.block_hash = vt.block_hash();
		this.cumulative_difficulty = vt.cumulative_difficulty();
		this.block_number = vt.block_number();
		this.count = vt.count();
	}

  	public sha256_hash getBlockHash() {
    	return this.block_hash;
  	}

  	public BigInteger getCumulativeDifficulty() {
    	return this.cumulative_difficulty;
  	}

  	public long getBlockNumber() {
    	return this.block_number;
  	}

  	public long getVoteCount() {
    	return this.count;
  	}

	public vote swig() {
    	return this.vt;
	}

}
