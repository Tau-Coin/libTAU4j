package org.libTAU4j.alerts;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_tx_confirmation_alert;

public class BlockChainTxConfirmAlert extends AbstractAlert<blockchain_tx_confirmation_alert> {

	byte[] chain_id;
	String peer;
	byte[] hash;
    String alertMsg;

    BlockChainTxConfirmAlert(blockchain_tx_confirmation_alert alert) {
        super(alert);
        byte[] key = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
		this.peer = Hex.encode(key);
        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
        this.hash = Vectors.byte_vector2bytes(alert.getHash().to_bytes());
		this.alertMsg = alert.message();
    }

    public byte[] get_chain_id() {
        return this.chain_id;
    }

    public String get_peer() {
        return this.peer;
    }

    public byte[] getHash() {
        return this.hash;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
