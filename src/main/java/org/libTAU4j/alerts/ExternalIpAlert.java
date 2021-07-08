package org.libTAU4j.alerts;

import org.libTAU4j.Address;
import org.libTAU4j.swig.external_ip_alert;

/**
 * Whenever libTAU4j learns about the machines external IP, this alert is
 * generated. The external IP address can be acquired from the tracker (if it
 * supports that) or from peers that supports the extension protocol.
 * The address can be accessed through the {@link #externalAddress()} member.
 *
 * @author gubatron
 * @author aldenml
 */
public final class ExternalIpAlert extends AbstractAlert<external_ip_alert> {

    ExternalIpAlert(external_ip_alert alert) {
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
}
