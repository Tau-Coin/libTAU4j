package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_new_tail_block_alert;

public class BlockChainNewTailBlockAlert extends AbstractAlert<blockchain_new_tail_block_alert> {

    String alertMsg;

    BlockChainNewTailBlockAlert(blockchain_new_tail_block_alert alert) {
        super(alert);
    }

    public String get_message() {
        return this.alertMsg;
    }

}
