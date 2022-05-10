package org.libTAU4j.alerts;

import org.libTAU4j.swig.alert;
import org.libTAU4j.swig.alert_category_t;

/**
 * @author gubatron
 * @author aldenml
 */
public interface Alert<T extends alert> {

    T swig();

    alert_category_t ERROR_NOTIFICATION = alert.error_notification;
    alert_category_t PORT_MAPPING_NOTIFICATION = alert.port_mapping_notification;
    alert_category_t STATUS_NOTIFICATION = alert.status_notification;
    alert_category_t DHT_NOTIFICATION = alert.dht_notification;
    alert_category_t SESSION_LOG_NOTIFICATION = alert.session_log_notification;
    alert_category_t DHT_LOG_NOTIFICATION = alert.dht_log_notification;
    alert_category_t DHT_OPERATION_NOTIFICATION = alert.dht_operation_notification;
    alert_category_t ALL_CATEGORIES = alert.all_categories;

    /**
     * A timestamp is automatically created in the constructor (in milliseconds).
     *
     * @return the timestamp
     */
    long timestamp();

    // returns an integer that is unique to this alert type. It can be
    // compared against a specific alert by querying a static constant called ``alert_type``
    // in the alert. It can be used to determine the run-time type of an alert* in
    // order to cast to that alert type and access specific members.
    //
    // e.g:
    //
    // .. code:: c++
    //
    //	std::vector<alert*> alerts;
    //	ses.pop_alerts(&alerts);
    //	for (alert* i : alerts) {
    //		switch (a->type()) {
    //
    //			case read_piece_alert::alert_type:
    //			{
    //				read_piece_alert* p = (read_piece_alert*)a;
    //				if (p->ec) {
    //					// read_piece failed
    //					break;
    //				}
    //				// use p
    //				break;
    //			}
    //			case file_renamed_alert::alert_type:
    //			{
    //				// etc...
    //			}
    //		}
    //	}
    AlertType type();

    /**
     * Returns a string literal describing the type of the alert. It does
     * not include any information that might be bundled with the alert.
     *
     *
     */
    String what();

    /**
     * Generate a string describing the alert and the information bundled
     * with it. This is mainly intended for debug and development use. It is not suitable
     * to use this for applications that may be localized. Instead, handle each alert
     * type individually and extract and render the information from the alert depending
     * on the locale.
     *
     *
     */
    String message();

    /**
     * Returns a bitmask specifying which categories this alert belong to.
     *
     * @return the alert category
     */
    alert_category_t category();
}
