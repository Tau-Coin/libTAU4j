/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;

import org.libTAU4j.swig.libTAU;
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

    private static final byte[] tau_chain = Vectors.byte_vector2bytes(libTAU.getTAU_CHAIN_ID());

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

		if(chain_id.length <= 8) {
			String TAUStr = "";
        	try {
				TAUStr = new String(tau_chain, "US-ASCII");
        	} catch (UnsupportedEncodingException e) {
            	throw new RuntimeException(e);
			}
			return TAUStr;
		}

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

		String TAUStr = "";
       	try {
			TAUStr = new String(tau_chain, "US-ASCII");
       	} catch (UnsupportedEncodingException e) {
           	throw new RuntimeException(e);
		}

		if(chain_id.equals(TAUStr)) {
			byte[] UTFBytes = null;
        	try {
				UTFBytes =  chain_id.substring(0, chain_id.length()).getBytes("UTF-8");
        	} catch (UnsupportedEncodingException e) {
            	throw new RuntimeException(e);
			}
			return UTFBytes;
		}

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
		String KEY_PEER = "bs=";
		String KEY_CHAIN_ID = "&dn=";

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
		byte[] key_peer_byte_1 = new byte[3];
		byte[] key_peer_byte_2 = new byte[4];
		byte[] addr_byte = new byte[32];
		
		System.arraycopy(chain_url, URL_PREFIX.length(), key_peer_byte_1, 0, 3);
		String key_peer_str = "";
        try {
			key_peer_str =  new String(key_peer_byte_1, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}
		int bs_count = 0;
		int pointer = 0;
        while ((key_peer_str.equals(KEY_PEER))||(key_peer_str.equals("&bs="))) {
			if(bs_count == 0) {
				pointer = URL_PREFIX.length() + 3;
				str_url = str_url + KEY_PEER;
				System.arraycopy(chain_url, pointer, addr_byte, 0, 32);
			} else {
				pointer = URL_PREFIX.length() + 35 + bs_count*4 + (bs_count-1)*32;
				str_url = str_url + "&" + KEY_PEER;
				System.arraycopy(chain_url, pointer, addr_byte, 0, 32);
			}
			str_url += Hex.encode(addr_byte);
			bs_count++;
			pointer = URL_PREFIX.length() + 35 + (bs_count-1) * 36;
			System.arraycopy(chain_url, pointer, key_peer_byte_2, 0, 4);
        	try {
				key_peer_str =  new String(key_peer_byte_2, "US-ASCII");
        	} catch (UnsupportedEncodingException e) {
            	throw new RuntimeException(e);
			}
        }

		if(bs_count > 0) { 
			pointer = URL_PREFIX.length() + 3 + (bs_count - 1)* 4 + bs_count * 32;
		} else {
			pointer = URL_PREFIX.length();
		}
		System.arraycopy(chain_url, pointer, key_peer_byte_2, 0, 4);
       	try {
			key_peer_str =  new String(key_peer_byte_2, "US-ASCII");
       	} catch (UnsupportedEncodingException e) {
           	throw new RuntimeException(e);
		}
	
		pointer += 4;
		int id_size = chain_url.length - pointer;
		byte[] id_bytes = new byte[id_size];
		System.arraycopy(chain_url, pointer, id_bytes, 0, id_size);
        if (key_peer_str.equals(KEY_CHAIN_ID)) {
			str_url += KEY_CHAIN_ID + chainIDBytesToString(id_bytes);
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
		// tauchain:?&dn=6b2fad7f7e8e2571TauTest
        index = chain_url.indexOf('&');
        while (index != -1 && index != 0) {
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

		if(index == 0) { 
            chain_url = chain_url.substring(index + 1); 
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

    public static ChainURL chainURLStringToChainURL(String chain_url) {

		String URL_PREFIX = "tauchain:?";
		String KEY_PEER = "bs";
		String KEY_CHAIN_ID = "dn";

		Set<String> peers = new HashSet();

		int index = chain_url.indexOf(URL_PREFIX);
        chain_url = chain_url.substring(index + URL_PREFIX.length());
		
        // bs=pk1&bs=pk2&dn=chainID
		// tauchain:?&dn=6b2fad7f7e8e2571TauTest
        index = chain_url.indexOf('&');
        while (index != -1 && index != 0) {
            // bs=pk1
            String kv = chain_url.substring(0, index);
            int i = kv.indexOf('=');
           	String k = kv.substring(0, i); 
           	String v = kv.substring(i + 1); 
           	if (k.equals(KEY_PEER)) {
				peers.add(v);
           	}
            chain_url = chain_url.substring(index + 1); 
            index = chain_url.indexOf('&');
        }

		if(index == 0) { 
            chain_url = chain_url.substring(index + 1); 
		}
        String kv = chain_url;
        int i = kv.indexOf('=');
        String k = kv.substring(0, i); 
        String v = kv.substring(i + 1);	
	
		byte[] chain_id = null;
        if (k.equals(KEY_CHAIN_ID)) {
			chain_id = chainIDStringToBytes(v);
        }

        return new ChainURL(chain_id, peers);
    }
	
	public static byte[] getTAUChainIdBytes(){
		return tau_chain;
	}

	public static String getTAUChainIdString(){
		String TAUStr = "";
       	try {
			TAUStr = new String(tau_chain, "US-ASCII");
       	} catch (UnsupportedEncodingException e) {
           	throw new RuntimeException(e);
		}
		return TAUStr;
	}

}
