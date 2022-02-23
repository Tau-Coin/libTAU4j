package org.libTAU4j.alerts;

import org.libTAU4j.Block;
import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_syncing_head_block_alert;

public class BlockChainSyncingHeadBlockAlert extends AbstractAlert<blockchain_syncing_head_block_alert> {

	Block  blk;
	String peer;
    String alertMsg;

    BlockChainSyncingHeadBlockAlert(blockchain_syncing_head_block_alert alert) {
        super(alert);
		this.blk = new Block(alert.getBlk());
        byte[] key = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
		this.peer = Hex.encode(key);
		this.alertMsg = alert.message();
    }

    public Block get_new_block() {
        return this.blk;
    }

    public String get_peer() {
        return this.peer;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
