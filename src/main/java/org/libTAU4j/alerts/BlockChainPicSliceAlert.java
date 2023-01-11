package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_pic_slice_alert;

import java.util.ArrayList;
import java.util.Iterator;

public class BlockChainPicSliceAlert extends AbstractAlert<blockchain_pic_slice_alert> {

	byte[] chain_id;
	byte[] news_hash;
	byte[] key;
	byte[] slice;
    String alertMsg;

    BlockChainPicSliceAlert(blockchain_pic_slice_alert alert) {
        super(alert);
        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
        this.news_hash = Vectors.byte_vector2bytes(alert.getNews_hash().to_bytes());
        this.key = Vectors.byte_vector2bytes(alert.getKey());
        this.slice = Vectors.byte_vector2bytes(alert.getSlice());
		this.alertMsg = alert.message();
    }

    public byte[] get_chain_id() {
        return this.chain_id;
    }

    public byte[] get_hash() {
        return this.news_hash;
    }

    public byte[] get_key() {
        return this.key;
    }

    public byte[] get_slice() {
        return this.slice;
    }

    public String get_message() {
        return this.alertMsg;
    }
}
