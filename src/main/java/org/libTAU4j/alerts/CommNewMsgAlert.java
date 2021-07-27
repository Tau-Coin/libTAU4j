package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_new_message_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommNewMsgAlert extends AbstractAlert<communication_new_message_alert> {

	byte[] newMsg;
	String alertMsg;

    CommNewMsgAlert(communication_new_message_alert alert) {
        super(alert);

		this.newMsg = Vectors.byte_vector2bytes(alert.get_new_message());
		this.alertMsg = alert.message();

    }

    public byte[] get_new_message() {
        return this.newMsg;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
