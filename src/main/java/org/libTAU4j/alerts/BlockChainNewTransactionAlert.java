package org.libTAU4j.alerts;

import org.libTAU4j.Transaction;
import org.libTAU4j.swig.blockchain_new_transaction_alert;

public class BlockChainNewTransactionAlert extends AbstractAlert<blockchain_new_transaction_alert> {

	Transaction tx;
    String alertMsg;

    BlockChainNewTransactionAlert(blockchain_new_transaction_alert alert) {
        super(alert);
		this.tx = new Transaction(alert.getTx());
		this.alertMsg = alert.message();
    }

    public Transaction get_new_transaction() {
        return this.tx;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
