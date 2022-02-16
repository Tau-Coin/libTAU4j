package org.libTAU4j.alerts;

import org.libTAU4j.Account;
import org.libTAU4j.swig.blockchain_state_alert;

public class BlockChainStateAlert extends AbstractAlert<blockchain_state_alert> {

	Account  account;
    String alertMsg;

    BlockChainStateAlert(blockchain_state_alert alert) {
        super(alert);
		this.account = new Account(alert.getAct());
		this.alertMsg = alert.message();
    }

    public Account get_account() {
        return this.account;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
