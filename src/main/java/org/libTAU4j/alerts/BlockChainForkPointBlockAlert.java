package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_fork_point_block_alert;

public class BlockChainForkPointBlockAlert extends AbstractAlert<blockchain_fork_point_block_alert> {

    String alertMsg;

    BlockChainForkPointBlockAlert(blockchain_fork_point_block_alert alert) {
        super(alert);
    }

    public String get_message() {
        return this.alertMsg;
    }

}
