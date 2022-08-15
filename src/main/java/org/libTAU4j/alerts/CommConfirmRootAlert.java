package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_confirmation_root_alert;
import org.libTAU4j.swig.sha1_hash_vector;

import java.util.ArrayList;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommConfirmRootAlert extends AbstractAlert<communication_confirmation_root_alert> {

	byte[] peer;
	long timestamp;
	String alertMsg;
	ArrayList<byte[]> confirmRoots = new ArrayList<>();

    CommConfirmRootAlert(communication_confirmation_root_alert alert) {
        super(alert);

		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
		this.timestamp = alert.get_timestamp();
		this.alertMsg = alert.message();

		sha1_hash_vector hash_vector = alert.getConfirmation_roots();
		if (!hash_vector.isEmpty()) {
            long size = hash_vector.size();
            for (int i = 0; i < size; ++i) {
                confirmRoots.add(Vectors.byte_vector2bytes(hash_vector.get(i).to_bytes()));
			}
		}
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public ArrayList<byte[]> getConfirmation_roots() {
        return this.confirmRoots;
    }

    public long get_timestamp() {
        return this.timestamp;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
