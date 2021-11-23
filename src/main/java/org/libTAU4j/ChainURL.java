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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
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
    private final byte[] url;

    public ChainURL(byte[] chainID, Set<String> peers) {

		this.chainID = chainID;
		this.peers = peers;
	
		pubkey_set ps = new pubkey_set();
		for(String p: peers){
			byte[] peer = Hex.decode(p);
			ps.add(new public_key(Vectors.bytes2byte_array_32(peer)));
		}
		this.cul = new chain_url(Vectors.bytes2byte_vector(chainID), ps);
		this.url = Vectors.byte_vector2bytes(this.cul.get_URL_java());
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
		this.url = Vectors.byte_vector2bytes(cul.get_URL_java());
	}

  	public byte[] getChainID() {
    	return this.chainID;
  	}

  	public Set<String> getPeers() {
    	return this.peers;
  	}

  	public byte[] getURL() {
    	return this.url;
  	}

	public chain_url swig() {
    	return this.cul;
	}

    public static String chainIDBytesToString(byte[] chain_id) {
		byte[] hexBytes = new byte[8];
		byte[] utfBytes = new byte[chain_id.length - 8];

		System.arraycopy(chain_id, 0, hexBytes, 0, 8);
		System.arraycopy(chain_id, 8, utfBytes, 0, chain_id.length - 8);

		String hexStr =  Hex.encode(hexBytes);

		String UTFStr = "";
        try {
			UTFStr =  new String(utfBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}
        return hexStr + UTFStr;
    }
	
    public static byte[] chainIDStringToBytes(String chain_id) {
		byte[] hexBytes =  Hex.decode(chain_id.substring(0, 16));
		byte[] UTFBytes = null;
        try {
			UTFBytes =  chain_id.substring(16, chain_id.length()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}

		byte[] bytes = new byte[hexBytes.length + UTFBytes.length];
		System.arraycopy(hexBytes, 0, bytes, 0, hexBytes.length);
		System.arraycopy(UTFBytes, 0, bytes, hexBytes.length, UTFBytes.length);

        return bytes;
    }

    public static String chainURLBytesToString(byte[] chain_url) {
		String URL_PREFIX = "tauchain:?";
		String KEY_PEER = "bs";
		String KEY_CHAIN_ID = "dn";

		String str_asc_url = "";
		String str_url = "";
        try {
			str_asc_url =  new String(chain_url, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}

		int index = str_asc_url.indexOf(URL_PREFIX);
        str_asc_url = str_asc_url.substring(index + URL_PREFIX.length());
		str_url = URL_PREFIX;
		
        // bs=pk1&bs=pk2&dn=chainID
        index = str_asc_url.indexOf('&');
        while (index != -1) {
            // bs=pk1
            String kv = str_asc_url.substring(0, index);
            int i = kv.indexOf('=');
            String k = kv.substring(0, i); 
            String v = kv.substring(i + 1); 
            if (k.equals(KEY_PEER)) {
				str_url = str_url + KEY_PEER + "=";
				//string -> hex
				byte[] tag_key = null;
        		try {
					tag_key =  v.getBytes("US-ASCII");
        		} catch (UnsupportedEncodingException e) {
            		throw new RuntimeException(e);
				}
				str_url += Hex.encode(tag_key);
            }

            str_asc_url = str_asc_url.substring(index + 1); 

            index = str_asc_url.indexOf('&');
			str_url += "&";
        }

        String kv = str_asc_url;
        int i = kv.indexOf('=');
        String k = kv.substring(0, i); 
        String v = kv.substring(i + 1);	
	
		byte[] id = null;
        try {
			id = v.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}

        if (k.equals(KEY_CHAIN_ID)) {
			str_url += KEY_CHAIN_ID + "=" + chainIDBytesToString(id);
        }
	
        return str_url;
    }
	
    public static byte[] chainURLStringToBytes(String chain_url) {

		String URL_PREFIX = "tauchain:?";
		String KEY_PEER = "bs";
		String KEY_CHAIN_ID = "dn";

		ArrayList<byte[]> byte_array = new ArrayList<byte[]>();

		int index = chain_url.indexOf(URL_PREFIX);
        chain_url = chain_url.substring(index + URL_PREFIX.length());
        try {
			byte_array.add(URL_PREFIX.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}
		
        // bs=pk1&bs=pk2&dn=chainID
        index = chain_url.indexOf('&');
        while (index != -1) {
            // bs=pk1
            String kv = chain_url.substring(0, index);
            int i = kv.indexOf('=');
            String k = kv.substring(0, i); 
            String v = kv.substring(i + 1); 
            if (k.equals(KEY_PEER)) {
				String tag_key= KEY_PEER + "=";
        		try {
					byte_array.add(tag_key.getBytes("US-ASCII"));
        		} catch (UnsupportedEncodingException e) {
            		throw new RuntimeException(e);
				}
				byte_array.add(Hex.decode(v));
            }

            chain_url = chain_url.substring(index + 1); 

            index = chain_url.indexOf('&');
			byte[] tag_7 = null;
        	try {
				tag_7 =  "&".getBytes("US-ASCII");
        	} catch (UnsupportedEncodingException e) {
            	throw new RuntimeException(e);
			}
			byte_array.add(tag_7);
        }

        String kv = chain_url;
        int i = kv.indexOf('=');
        String k = kv.substring(0, i); 
        String v = kv.substring(i + 1);	
	
		byte[] tag_id = null;
        try {
			String id_str = KEY_CHAIN_ID + "=";
			tag_id = id_str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}
		byte_array.add(tag_id);

        if (k.equals(KEY_CHAIN_ID)) {
			byte_array.add(chainIDStringToBytes(v));
        }


		//total size
		int byte_url_size = 0;
		for(byte[] array : byte_array) {
			byte_url_size += array.length;	
		}

		byte[] byte_url = new byte[byte_url_size];
	
		// fuzhi	
		int l = 0;
		for(int j = 0; j < byte_array.size(); j++)	{
			byte[] array = byte_array.get(j);
			for(int m = 0; m < array.length; m++)
			{
				byte_url[l] = array[m];
				l++;
			}
		}

        return byte_url;
    }
}
