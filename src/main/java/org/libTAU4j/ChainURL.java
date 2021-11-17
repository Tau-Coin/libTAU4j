/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;

import org.libTAU4j.swig.pubkey_set;
import org.libTAU4j.swig.public_key;
import org.libTAU4j.swig.chain_url;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

/**
 * ChainURL in libTAU
 *
 * @author taichen.liu
 */
public final class ChainURL {

	private final byte[] chainID;
	private final Set<String> peers;

    private final chain_url cul;

    public ChainURL(byte[] chainID, Set<String> peers) {

		this.chainID = chainID;
		this.peers = peers;
	
		pubkey_set ps = new pubkey_set();
		for(String p: peers){
			byte[] peer = Hex.decode(p);
			ps.add(new public_key(Vectors.bytes2byte_array_32(peer)));
		}
		this.cul = new chain_url(Vectors.bytes2byte_vector(chainID), ps);
	}

    public ChainURL(chain_url cul) {
		this.cul = cul;
		this.chainID = Vectors.byte_vector2bytes(cul.chain_id());
		this.peers = new HashSet();
		pubkey_set ps = cul.peers();		
		Iterator<public_key> it = ps.iterator();
		while (it.hasNext()) {
			public_key pk = it.next();
			String key = Hex.encode(Vectors.byte_vector2bytes(pk.to_bytes()));
			this.peers.add(key);
		}
	}

  	public byte[] getChainID() {
    	return this.chainID;
  	}

  	public Set<String> getPeers() {
    	return this.peers;
  	}

	public chain_url swig() {
    	return this.cul;
	}

}
