/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.Entry;
import org.libTAU4j.swig.vote;
import org.libTAU4j.swig.block;

import java.math.BigInteger;

/**
 * Vote in libTAU
 *
 * @author taichen.liu
 */
public final class Vote {
    /*
	private final sha256_hash block_hash;
	private final BigInteger cumulative_difficulty;
	private final long block_number;
    */
	private final block bk;
	private final long count;
    private final vote vt;

    public Vote(vote vt) {
		this.vt = vt;
		this.bk = vt.voting_block();
		this.count = vt.count();
	}

  	public Block getBlock() {
    	return new Block(this.bk);
  	}

  	public long getVoteCount() {
    	return this.count;
  	}

	public vote swig() {
    	return this.vt;
	}
}
