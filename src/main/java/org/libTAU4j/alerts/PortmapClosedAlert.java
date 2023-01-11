package org.libTAU4j.alerts;

import org.libTAU4j.PortmapProtocol;
import org.libTAU4j.PortmapTransport;
import org.libTAU4j.swig.portmap_closed_alert;

/**
 * This alert is generated when a NAT router was successfully found and
 * a port was successfully mapped on it. On a NAT:ed network with a NAT-PMP
 * capable router, this is typically generated once when mapping the TCP
 * port and, if DHT is enabled, when the UDP port is mapped.
 *
 * @author gubatron
 * @author aldenml
 */
public final class PortmapClosedAlert extends AbstractAlert<portmap_closed_alert> {

    PortmapTransport ptp;
    PortmapProtocol pp;

    PortmapClosedAlert(portmap_closed_alert alert) {
        super(alert);
        this.ptp = PortmapTransport.fromSwig(alert.getMap_transport().swigValue());
        this.pp = PortmapProtocol.fromSwig(alert.getMap_protocol().swigValue());
    }

    public PortmapTransport mapTransport() {
        return this.ptp;
    }

    /**
     * The protocol this mapping was for.
     *
     * @return the mapping protocol
     */
    public PortmapProtocol mapProtocol() {
        return this.pp;
    }
}
