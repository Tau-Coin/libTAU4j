package org.libTAU4j.alerts;

import org.libTAU4j.Vote;
import org.libTAU4j.swig.block_vote_vector;
import org.libTAU4j.swig.blockchain_top_three_votes_alert;

import java.util.ArrayList;

public class BlockChainTopThreeVotesAlert extends AbstractAlert<blockchain_top_three_votes_alert> {

	
    ArrayList<Vote> votes = new ArrayList<>(3);
    String alertMsg;

    BlockChainTopThreeVotesAlert(blockchain_top_three_votes_alert alert) {
        super(alert);

		block_vote_vector vts = alert.getVotes();

		for(int i = 0; i < vts.size(); i++) {
			votes.add(new Vote(vts.get(i)));
		}
		this.alertMsg = alert.message();
    }

    public ArrayList<Vote> get_votes() {
		return this.votes;
	}
	
    public String get_message() {
        return this.alertMsg;
    }

}
