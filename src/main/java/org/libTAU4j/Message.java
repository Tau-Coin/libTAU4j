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

    private Map msgValue = new HashMap();
    private Entry msgEntry;

    private final message msg;

    public Message(Map<String, ?> map) {

		if(!map.containsKey("timestamp")){
			Date date = new Date();
        	Long time = date.getTime();
	        time = time / 1000;
            msgValue.put("timestamp", time);
		}

        for (String k : map.keySet()) {
            Object v = map.get(k);

            if (v instanceof String) {
                msgValue.put(k, (String)v);
				System.out.println("sk: "+ k);
            } else if (v instanceof BigInteger) {
				BigInteger bv = (BigInteger)v;
				long lv = bv.longValue();
                msgValue.put(k, lv);
				System.out.println("bigk: "+ k);
            } else if (v instanceof Integer) {
                msgValue.put(k, (Integer)v);
				System.out.println("ik: "+ k);
            } else if (v instanceof byte[]) {
				byte_vector vv = Vectors.bytes2byte_vector((byte[])v);
			    entry e = entry.from_preformatted_bytes(vv);
                msgValue.put(k, e);
				System.out.println("bak: "+ k);
            }
        }

		//entry
		msgEntry = Entry.fromMap(msgValue);
		this.msg = new message(msgEntry.swig());
	}

    public Message(entry e) {

        this.msg = new message(e);

		// construct msgValue from entry
		boost_string_entry_map entry_map = e.dict();
		string_vector entry_keys = entry_map.keys();	
		System.out.println("++++++++++++++++++++++++");
		for(int i = 0; i< entry_keys.size(); i++) {
			String key = entry_keys.get(i);
			System.out.println("ek: " + key);
			entry value = entry_map.get(key);
			entry.data_type entry_type = entry.data_type.swigToEnum(value.type().swigValue());
			System.out.println("et: " + entry_type);
			if(entry_type == entry.data_type.int_t) {
					msgValue.put(key, value.integer());
			} else if(entry_type == entry.data_type.string_t) {
					msgValue.put(key, value.string());
			} else if(entry_type == entry.data_type.preformatted_t) {
					byte_vector bv = value.preformatted_bytes();
					msgValue.put(key, Vectors.byte_vector2bytes(bv));
			} else if(entry_type == entry.data_type.undefined_t) {
					byte_vector bv = value.preformatted_bytes();
					msgValue.put(key, Vectors.byte_vector2bytes(bv));
			}
		}
		System.out.println("++++++++++++++++++++++++");
    }

	public message swig() {
    	return this.msg;
	}

  	public long timestamp() {
    	return this.msg.timestamp();
  	}

	public String encode() {
    	return this.msg.encode();
  	}

	public Map value() {
    	return this.msgValue;
  	}

	public Sha256Hash sha256() {
    	return new Sha256Hash(this.msg.sha256());
	}

	public String to_string() {
    	return this.msg.to_string();
	}

	public boolean empty() {
    	return this.msg.empty();
	}

}
