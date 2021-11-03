package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_new_tip_block_alert;

public class BlockChainNewTipBlockAlert extends AbstractAlert<blockchain_new_tip_block_alert> {

    String alertMsg;

    BlockChainNewTipBlockAlert(blockchain_new_tip_block_alert alert) {
        super(alert);
    }

    public String get_message() {
        return this.alertMsg;
    }

}
