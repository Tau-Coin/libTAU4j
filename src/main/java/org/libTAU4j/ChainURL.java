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

	private final String chainID;
	private final Set<String> peers;

    private final byte[] bytes_url;
    private final String string_url;

    private static final byte[] tau_chain = Vectors.byte_vector2bytes(libTAU.getTAU_CHAIN_ID());
    private static final String URL_PREFIX = "tauchain:?";
    private static final String KEY_PEER = "&bs=";
    private static final String KEY_CHAIN_ID = "dn=";

    public ChainURL(String chainID, Set<String> peers) {

		this.chainID = chainID;
		this.peers = peers;

        String url = URL_PREFIX + KEY_CHAIN_ID + chainID;

		for(String p: peers){
            url = url + KEY_PEER + p;
		}

        this.string_url = url;
        this.bytes_url = ChainURL.chainURLStringToBytes(this.string_url);
	}

  	public String getChainID() {
    	return this.chainID;
  	}

  	public Set<String> getPeers() {
    	return this.peers;
  	}

  	public String getStringURL() {
    	return this.string_url;
  	}

  	public byte[] getBytesURL() {
    	return this.bytes_url;
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

    public static byte[] chainURLStringToBytes(String chain_url) {

		ArrayList<byte[]> byte_array = new ArrayList<byte[]>();

		int index_head = chain_url.indexOf(URL_PREFIX);
        chain_url = chain_url.substring(index_head + URL_PREFIX.length());
        try {
			byte_array.add(URL_PREFIX.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
		}
		
        // dn=chainID&bs=pk1&bs=pk2
		// tauchain:?dn=6b2fad7f7e8e2571TauTest
        int index_chain_id = chain_url.indexOf(KEY_CHAIN_ID);
        if (index_chain_id != -1) {

            int[] index_array = new int[20];
            int[] tag_array = new int[20];
            index_array[0] = index_chain_id;
            tag_array[0] = 1;
            int index = 1;

            int index_bs = chain_url.indexOf(KEY_PEER);
            while(index_bs != -1) {
                String c_url = chain_url.substring(index_bs + KEY_PEER.length());
                index_array[index] = index_bs;
                tag_array[index] = 2;
                index++;
                int index_current = c_url.indexOf(KEY_PEER);
                if(index_current == -1) break;
                index_bs += index_current + KEY_PEER.length();
            }
            //has chain id, but no bs nodes 
            if(index == 1) {
                String chain_id = chain_url.substring(index_array[0] + KEY_CHAIN_ID.length(), chain_url.length());
       		    try {
				    byte_array.add(KEY_CHAIN_ID.getBytes("US-ASCII"));
       			} catch (UnsupportedEncodingException e) {
           		    throw new RuntimeException(e);
				}
			    byte_array.add(chainIDStringToBytes(chain_id));
            } else {
                //select order
                for(int i = 0; i < index - 1; i++){
                    int min_index = i;
                    for(int j = i + 1; j < index ; j++) {
                        if(index_array[j] < index_array[min_index]) {
                            min_index = j;
                        }
                    }
                    if(min_index != i) {
                        int temp_index = index_array[min_index];
                        index_array[min_index] = index_array[i];
                        index_array[i] = temp_index; 

                        int temp_tag = tag_array[min_index];
                        tag_array[min_index] = tag_array[i];
                        tag_array[i] = temp_tag; 
                    }
                }
                //slice
                for(int i = 0 ; i < index ; i++) {
                    int index_end = 0;
                    if(i == index - 1)
                        index_end = chain_url.length();
                    else 
                        index_end = index_array[i + 1];
                    if(tag_array[i] == 2) {
                        //bs node
                        String c_url = chain_url.substring(index_array[i] + KEY_PEER.length(), index_end);
       			        try {
					        byte_array.add(KEY_PEER.getBytes("US-ASCII"));
       			        } catch (UnsupportedEncodingException e) {
           			        throw new RuntimeException(e);
				        }
				        byte_array.add(Hex.decode(c_url));
                    } else {
                        String chain_id = chain_url.substring(index_array[i] + KEY_CHAIN_ID.length(), index_end);
       			        try {
					        byte_array.add(KEY_CHAIN_ID.getBytes("US-ASCII"));
       			        } catch (UnsupportedEncodingException e) {
           			        throw new RuntimeException(e);
				        }
			            byte_array.add(chainIDStringToBytes(chain_id));
                    }
                }
            }

        } else { 
            //no chain id in chain url, error
            return null;
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

        Set<String> peers = new HashSet();
        String chain_id = "";

		int index_head = chain_url.indexOf(URL_PREFIX);
        chain_url = chain_url.substring(index_head + URL_PREFIX.length());

        // dn=chainID&bs=pk1&bs=pk2
		// tauchain:?dn=6b2fad7f7e8e2571TauTest
        int index_chain_id = chain_url.indexOf(KEY_CHAIN_ID);
        if (index_chain_id != -1) {

            int[] index_array = new int[20];
            int[] tag_array = new int[20];
            index_array[0] = index_chain_id;
            tag_array[0] = 1;
            int index = 1;

            int index_bs = chain_url.indexOf(KEY_PEER);
            while(index_bs != -1) {
                String c_url = chain_url.substring(index_bs + KEY_PEER.length());
                index_array[index] = index_bs;
                tag_array[index] = 2;
                index++;
                int index_current = c_url.indexOf(KEY_PEER);
                if(index_current == -1) break;
                index_bs += index_current + KEY_PEER.length();
            }
            //has chain id, but no bs nodes 
            if(index == 1) {
                chain_id = chain_url.substring(index_array[0] + KEY_CHAIN_ID.length(), chain_url.length());
			    return new ChainURL(chain_id, peers);
            } else {
                //select order
                for(int i = 0; i < index - 1; i++){
                    int min_index = i;
                    for(int j = i + 1; j < index ; j++) {
                        if(index_array[j] < index_array[min_index]) {
                            min_index = j;
                        }
                    }
                    if(min_index != i) {
                        int temp_index = index_array[min_index];
                        index_array[min_index] = index_array[i];
                        index_array[i] = temp_index; 

                        int temp_tag = tag_array[min_index];
                        tag_array[min_index] = tag_array[i];
                        tag_array[i] = temp_tag; 
                    }
                }
                //slice
                for(int i = 0 ; i < index ; i++) {
                    int index_end = 0;
                    if(i == index - 1)
                        index_end = chain_url.length();
                    else 
                        index_end = index_array[i + 1];
                    if(tag_array[i] == 2) {
                        //bs node
                        String c_url = chain_url.substring(index_array[i] + KEY_PEER.length(), index_end);
				        peers.add(c_url);
                    } else {
                        String c_id = chain_url.substring(index_array[i] + KEY_CHAIN_ID.length(), index_end);
			            chain_id = c_id;
                    }
                }

                return new ChainURL(chain_id, peers);
            }

        } else { 
            //no chain id in chain url, error
            return null;
		}
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
