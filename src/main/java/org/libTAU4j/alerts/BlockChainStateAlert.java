package org.libTAU4j.alerts;

import org.libTAU4j.Account;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_state_alert;

public class BlockChainStateAlert extends AbstractAlert<blockchain_state_alert> {

	byte[] chain_id;
	Account account;
    String alertMsg;

    BlockChainStateAlert(blockchain_state_alert alert) {
        super(alert);
		this.account = new Account(alert.getAct());
        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
		this.alertMsg = alert.message();
    }

    public Account get_account() {
        return this.account;
    }

    public byte[] get_chain_id() {
        return this.chain_id;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
