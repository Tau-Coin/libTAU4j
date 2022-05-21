package org.libTAU4j.alerts;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_tx_sent_alert;

public class BlockChainTxSentAlert extends AbstractAlert<blockchain_tx_sent_alert> {

	byte[] peer;
	byte[] sentTxHash;
	long timestamp;
	String alertMsg;

    BlockChainTxSentAlert(blockchain_tx_sent_alert alert) {
        super(alert);
		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
        this.sentTxHash = Vectors.byte_vector2bytes(alert.getTx_sent_hash().to_bytes());
		this.timestamp = alert.get_timestamp();
		this.alertMsg = alert.message();
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public byte[] getSent_tx_hash() {
        return this.sentTxHash;
    }

    public long get_timestamp() {
        return this.timestamp;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
