package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_message_arrived_alert;
import org.libTAU4j.swig.libTAU_jni;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommMsgArrivedAlert extends AbstractAlert<communication_message_arrived_alert> {

	byte[] peer;
	byte[] arrivedMsgHash;
	long timestamp;
	String alertMsg;

    CommMsgArrivedAlert(communication_message_arrived_alert alert) {
        super(alert);
		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
        this.arrivedMsgHash = Vectors.byte_vector2bytes(alert.getMsg_arrived_hash().to_bytes());
		this.timestamp = alert.get_timestamp();
		this.alertMsg = alert.message();
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public byte[] getMsg_arrived_hash() {
        return this.arrivedMsgHash;
    }

    public long get_timestamp() {
        return this.timestamp;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
