package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_user_info_alert;

public final class CommUserInfoAlert extends AbstractAlert<communication_user_info_alert> {

	byte[] peer;
	byte[] key;
	byte[] userInfo;
	String alertMsg;

    CommUserInfoAlert(communication_user_info_alert alert) {
        super(alert);

		this.peer = Vectors.byte_vector2bytes(alert.getPeer().to_bytes());
        this.key = Vectors.byte_vector2bytes(alert.getKey());
        this.userInfo = Vectors.byte_vector2bytes(alert.getUser_info());
		this.alertMsg = alert.message();
    }

    public byte[] get_peer() {
		return this.peer;
    }

    public byte[] get_key() {
		return this.key;
    }

    public byte[] get_user_info() {
        return this.userInfo;
    }

	public String get_message() {
		return this.alertMsg;
	}

}
