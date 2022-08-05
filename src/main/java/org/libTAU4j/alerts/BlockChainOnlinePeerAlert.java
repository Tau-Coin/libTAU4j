package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_online_peer_alert;

public class BlockChainOnlinePeerAlert extends AbstractAlert<blockchain_online_peer_alert> {

	byte[] chain_id;
	byte[] peer;
	long time;
    String alertMsg;

    BlockChainOnlinePeerAlert(blockchain_online_peer_alert alert) {
        super(alert);
        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
		this.time = alert.getTime();
		this.alertMsg = alert.message();
    }

    public byte[] get_chain_id() {
        return this.chain_id;
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public long get_time() {
        return this.time;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
