package org.libTAU4j.alerts;

import org.libTAU4j.Hex;
import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_new_device_id_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommNewDeviceIdAlert extends AbstractAlert<communication_new_device_id_alert> {

	byte[] deviceId;
	String alertMsg;

    CommNewDeviceIdAlert(communication_new_device_id_alert alert) {
        super(alert);

		this.deviceId = Vectors.byte_vector2bytes(alert.get_device_id());
		this.alertMsg = alert.message();
    }

    public String get_device_id() {
        return Hex.encode(this.deviceId);
    }

	public String get_message() {
		return this.alertMsg;
	}

}
