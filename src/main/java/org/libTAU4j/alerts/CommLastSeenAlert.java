package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_last_seen_alert;
import org.libTAU4j.swig.libTAU_jni;

public class CommLastSeenAlert extends AbstractAlert<communication_last_seen_alert> {

	byte[] peer;
	long timestamp;
	String alertMsg;

    CommLastSeenAlert(communication_last_seen_alert alert) {
        super(alert);
		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
		this.timestamp = alert.get_last_seen();
		this.alertMsg = alert.message();
    }

    public byte[] get_peer() {
        return this.peer;
    }

    public long get_last_seen() {
        return this.timestamp;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
