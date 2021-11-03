package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_top_three_votes_alert;

public class BlockChainTopThreeVotesAlert extends AbstractAlert<blockchain_top_three_votes_alert> {

    String alertMsg;

    BlockChainTopThreeVotesAlert(blockchain_top_three_votes_alert alert) {
        super(alert);
    }

    public String get_message() {
        return this.alertMsg;
    }

}
