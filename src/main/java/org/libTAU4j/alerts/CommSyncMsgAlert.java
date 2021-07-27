package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_syncing_message_alert;
import org.libTAU4j.swig.libTAU_jni;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommSyncMsgAlert extends AbstractAlert<communication_syncing_message_alert> {

	byte[] peer;
	byte[] syncMsgHash;
	long timestamp;
	String alertMsg;

    CommSyncMsgAlert(communication_syncing_message_alert alert) {
        super(alert);
		this.peer = Vectors.byte_vector2bytes(alert.get_peer());
        this.syncMsgHash = Vectors.byte_vector2bytes(alert.getSyncing_msg_hash().to_bytes());
		this.timestamp = alert.get_timestamp();
		this.alertMsg = alert.message();
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public byte[] getSyncing_msg_hash() {
        return this.syncMsgHash;
    }

    public long get_timestamp() {
        return this.timestamp;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
