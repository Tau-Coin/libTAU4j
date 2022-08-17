package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_peer_attention_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommPeerAttentionAlert extends AbstractAlert<communication_peer_attention_alert> {

	byte[] peer;
	long time;
	String alertMsg;

    CommPeerAttentionAlert(communication_peer_attention_alert alert) {
        super(alert);

		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
        this.time = alert.getTime();
		this.alertMsg = alert.message();
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
