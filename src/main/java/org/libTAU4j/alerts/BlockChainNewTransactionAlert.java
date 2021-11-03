package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_new_transaction_alert;

public class BlockChainNewTransactionAlert extends AbstractAlert<blockchain_new_transaction_alert> {

    String alertMsg;

    BlockChainNewTransactionAlert(blockchain_new_transaction_alert alert) {
        super(alert);
    }

    public String get_message() {
        return this.alertMsg;
    }

}
