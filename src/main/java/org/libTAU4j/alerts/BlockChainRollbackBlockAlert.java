package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_rollback_block_alert;

public class BlockChainRollbackBlockAlert extends AbstractAlert<blockchain_rollback_block_alert> {

    String alertMsg;

    BlockChainRollbackBlockAlert(blockchain_rollback_block_alert alert) {
        super(alert);
    }

    public String get_message() {
        return this.alertMsg;
    }

}
