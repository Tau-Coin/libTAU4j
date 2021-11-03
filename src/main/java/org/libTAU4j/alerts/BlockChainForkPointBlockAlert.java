package org.libTAU4j.alerts;

import org.libTAU4j.Block;
import org.libTAU4j.swig.blockchain_fork_point_block_alert;

public class BlockChainForkPointBlockAlert extends AbstractAlert<blockchain_fork_point_block_alert> {

	Block  bk;
    String alertMsg;

    BlockChainForkPointBlockAlert(blockchain_fork_point_block_alert alert) {
        super(alert);
		this.blk = new Block(alert.getBlk());
		this.alertMsg = alert.message();
    }

    public Block get_new_block() {
        return this.blk;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
