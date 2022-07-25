package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.blockchain_fail_to_get_chain_data_alert;

public class BlockChainFailToGetChainDataAlert extends AbstractAlert<blockchain_fail_to_get_chain_data_alert> {

	byte[] chain_id;
    String alertMsg;

    BlockChainFailToGetChainDataAlert(blockchain_fail_to_get_chain_data_alert alert) {
        super(alert);
        this.chain_id = Vectors.byte_vector2bytes(alert.getChain_id());
		this.alertMsg = alert.message();
    }

    public byte[] get_chain_id() {
        return this.chain_id;
    }

    public String get_message() {
        return this.alertMsg;
    }

}
