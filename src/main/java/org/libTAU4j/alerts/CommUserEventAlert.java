package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_user_event_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommUserEventAlert extends AbstractAlert<communication_user_event_alert> {

	byte[] peer;
	String userEvent;
	String alertMsg;

    CommUserEventAlert(communication_user_event_alert alert) {
        super(alert);

		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
        this.userEvent = alert.getUser_event();
		this.alertMsg = alert.message();

    }

    public byte[] get_peer() {
		return this.peer;
    }

    public String get_user_event() {
        return this.userEvent;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
