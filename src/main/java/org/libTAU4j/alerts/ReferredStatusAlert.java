package org.libTAU4j.alerts;

import org.libTAU4j.Address;
import org.libTAU4j.swig.referred_status_alert;

/**
 * Whenever libTAU4j learns about referred status, this alert is
 * generated.
 *
 * @author Xianshui Sheng
 */
public final class ReferredStatusAlert extends AbstractAlert<referred_status_alert> {

    ReferredStatusAlert(referred_status_alert alert) {
        super(alert);
    }

    /**
     * The IP address that is believed to be our external IP.
     *
     * @return the external address
     */
    public Address externalAddress() {
        return new Address(alert.get_external_address());
    }

	/**
	 * The port that is believed to be our external port.
	 *
	 * @return the external port
	 */
	public int externalPort() {
		return alert.get_external_port();
	}
}
