package org.libTAU4j.alerts;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_tx_arrived_alert;

public class BlockChainTxArrivedAlert extends AbstractAlert<blockchain_tx_arrived_alert> {

	byte[] chain_id;
	byte[] arrivedTxHash;
	long timestamp;
	String alertMsg;

    BlockChainTxArrivedAlert(blockchain_tx_arrived_alert alert) {
        super(alert);
        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
        this.arrivedTxHash = Vectors.byte_vector2bytes(alert.getTx_arrived_hash().to_bytes());
		this.timestamp = alert.get_timestamp();
		this.alertMsg = alert.message();
    }

    public byte[] get_chain_id() {
        return this.chain_id;
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
