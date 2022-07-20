package org.libTAU4j.alerts;

import org.libTAU4j.Account;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_state_array_alert;
import org.libTAU4j.swig.pubkey_account_vector;

import java.util.ArrayList;

public class BlockChainStateArrayAlert extends AbstractAlert<blockchain_state_array_alert> {

	byte[] chain_id;
	ArrayList<Account> accounts = new ArrayList<>();
    String alertMsg;

    BlockChainStateArrayAlert(blockchain_state_array_alert alert) {
        super(alert);
        pubkey_account_vector acts = alert.getAccounts();

        for(int i = 0; i < acts.size(); i++) {
            accounts.add(new Account(acts.get(i)));
        }

        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
		this.alertMsg = alert.message();
    }

    public ArrayList<Account> get_accounts() {
        return this.accounts;
    }

    public byte[] get_chain_id() {
        return this.chain_id;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
