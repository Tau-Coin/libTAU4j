package org.libTAU4j.alerts;

import org.libTAU4j.swig.blockchain_log_alert;

public class BlockChainLogAlert extends AbstractAlert<blockchain_log_alert> {
    BlockChainLogAlert(blockchain_log_alert alert) {
        super(alert);
    }

    /**
     * Returns the log message.
     *
     * @return the message
     */
    public String logMessage() {
        return alert.log_message();
    }
}
