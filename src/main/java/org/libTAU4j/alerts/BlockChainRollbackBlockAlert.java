package org.libTAU4j.alerts;

import org.libTAU4j.Block;
import org.libTAU4j.swig.blockchain_rollback_block_alert;

public class BlockChainRollbackBlockAlert extends AbstractAlert<blockchain_rollback_block_alert> {

	Block  blk;
    String alertMsg;

    BlockChainRollbackBlockAlert(blockchain_rollback_block_alert alert) {
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
