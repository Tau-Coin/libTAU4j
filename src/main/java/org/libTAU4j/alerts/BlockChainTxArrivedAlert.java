package org.libTAU4j.alerts;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_tx_arrived_alert;

public class BlockChainTxArrivedAlert extends AbstractAlert<blockchain_tx_arrived_alert> {

	byte[] peer;
	byte[] arrivedTxHash;
	long timestamp;
	String alertMsg;

    BlockChainTxArrivedAlert(blockchain_tx_arrived_alert alert) {
        super(alert);
		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
        this.arrivedTxHash = Vectors.byte_vector2bytes(alert.getTx_arrived_hash().to_bytes());
		this.timestamp = alert.get_timestamp();
		this.alertMsg = alert.message();
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public byte[] getArrived_tx_hash() {
        return this.arrivedTxHash;
    }

    public long get_timestamp() {
        return this.timestamp;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
