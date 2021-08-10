/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.boost_string_entry_map;
import org.libTAU4j.swig.byte_vector;
import org.libTAU4j.swig.entry;
import org.libTAU4j.swig.string_vector;
import org.libTAU4j.swig.message;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Entry class represents one node in a bencoded hierarchy. It works as a
 * variant type, it can be either a list, a dictionary, an integer
 * or a string.
 *
 * @author gubatron
 * @author aldenml
 */
public final class Message {

	private final long timestamp;
	private final byte[] sender;
	private final byte[] receiver;
	private final byte[] payload;
	private final String msgId;

    private final message msg;

    public Message(long timestamp, byte[] sender, byte[] receiver, byte[] payload) {

		this.timestamp = timestamp;
		this.sender = sender;
		this.receiver = receiver;
		this.payload = payload;

		byte_vector bv_sender = Vectors.bytes2byte_vector(sender);
		byte_vector bv_receiver = Vectors.bytes2byte_vector(receiver);
		byte_vector bv_payload = Vectors.bytes2byte_vector(payload);
		this.msg = new message(timestamp, bv_sender, bv_receiver, bv_payload);

		this.msgId = this.msg.sha256().to_hex();

	}

    public Message(message msg) {

		this.timestamp = msg.timestamp();
		this.sender = Vectors.byte_vector2bytes(msg.sender());
		this.receiver = Vectors.byte_vector2bytes(msg.receiver());
		this.payload = Vectors.byte_vector2bytes(msg.payload());
		this.msgId = msg.sha256().to_hex();
		
		this.msg = msg;

	}

  	public long timestamp() {
    	return this.timestamp;
  	}

  	public byte[] sender() {
    	return this.sender;
  	}

  	public byte[] receiver() {
    	return this.receiver;
  	}

  	public byte[] payload() {
    	return this.payload;
  	}

	public String msgId() {
    	return this.msgId;
	}

	public message swig() {
    	return this.msg;
	}

}
