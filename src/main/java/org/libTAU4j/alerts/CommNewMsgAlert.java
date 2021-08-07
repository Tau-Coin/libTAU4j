package org.libTAU4j.alerts;

import org.libTAU4j.Entry;
import org.libTAU4j.Message;
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

	Entry newMsg;
	String alertMsg;

    CommNewMsgAlert(communication_new_message_alert alert) {
        super(alert);

		this.newMsg = new Entry(alert.getMsg());
		this.alertMsg = alert.message();

    }

    public Message get_new_message() {
        return new Message(this.newMsg.swig());
    }

	public String get_message() {
		return this.alertMsg;
	}

}
