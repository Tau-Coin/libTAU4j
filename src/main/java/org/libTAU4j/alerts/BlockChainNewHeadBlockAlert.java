package org.libTAU4j.alerts;

import org.libTAU4j.Account;
import org.libTAU4j.Block;
import org.libTAU4j.swig.account;
import org.libTAU4j.swig.blockchain_new_head_block_alert;
import org.libTAU4j.swig.pubkey_account_set;

import java.util.ArrayList;
import java.util.Iterator;

public class BlockChainNewHeadBlockAlert extends AbstractAlert<blockchain_new_head_block_alert> {

	Block  blk;
	ArrayList<Account> accounts = new ArrayList<>();
    String alertMsg;

    BlockChainNewHeadBlockAlert(blockchain_new_head_block_alert alert) {
        super(alert);
		this.blk = new Block(alert.getBlk());
        pubkey_account_set acts = alert.getAccounts();
        if(acts.size() > 0) {
            Iterator<account> act_iter = acts.iterator();
            while(act_iter.hasNext()) {
                accounts.add(new Account(act_iter.next()));
            }
        }
		this.alertMsg = alert.message();
    }

    public ArrayList<Account> get_accounts() {
        return this.accounts;
    }

    public Block get_new_block() {
        return this.blk;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
