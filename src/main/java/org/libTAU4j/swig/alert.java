/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class alert {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected alert(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(alert obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_alert(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public int type() {
    return libTAU_jni.alert_type(swigCPtr, this);
  }

  public String what() {
    return libTAU_jni.alert_what(swigCPtr, this);
  }

  public String message() {
    return libTAU_jni.alert_message(swigCPtr, this);
  }

  public alert_category_t category() {
    return new alert_category_t(libTAU_jni.alert_category(swigCPtr, this), true);
  }

  public long get_timestamp() {
    return libTAU_jni.alert_get_timestamp(swigCPtr, this);
  }

  public static udp_error_alert cast_to_udp_error_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_udp_error_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new udp_error_alert(cPtr, false);
  }

  public static external_ip_alert cast_to_external_ip_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_external_ip_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new external_ip_alert(cPtr, false);
  }

  public static listen_failed_alert cast_to_listen_failed_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_listen_failed_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new listen_failed_alert(cPtr, false);
  }

  public static listen_succeeded_alert cast_to_listen_succeeded_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_listen_succeeded_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new listen_succeeded_alert(cPtr, false);
  }

  public static portmap_error_alert cast_to_portmap_error_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_portmap_error_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new portmap_error_alert(cPtr, false);
  }

  public static portmap_alert cast_to_portmap_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_portmap_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new portmap_alert(cPtr, false);
  }

  public static portmap_log_alert cast_to_portmap_log_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_portmap_log_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new portmap_log_alert(cPtr, false);
  }

  public static dht_announce_alert cast_to_dht_announce_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_announce_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_announce_alert(cPtr, false);
  }

  public static dht_get_peers_alert cast_to_dht_get_peers_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_get_peers_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_get_peers_alert(cPtr, false);
  }

  public static dht_bootstrap_alert cast_to_dht_bootstrap_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_bootstrap_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_bootstrap_alert(cPtr, false);
  }

  public static session_start_over_alert cast_to_session_start_over_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_session_start_over_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new session_start_over_alert(cPtr, false);
  }

  public static session_stop_over_alert cast_to_session_stop_over_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_session_stop_over_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new session_stop_over_alert(cPtr, false);
  }

  public static incoming_connection_alert cast_to_incoming_connection_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_incoming_connection_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new incoming_connection_alert(cPtr, false);
  }

  public static state_update_alert cast_to_state_update_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_state_update_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new state_update_alert(cPtr, false);
  }

  public static session_stats_alert cast_to_session_stats_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_session_stats_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new session_stats_alert(cPtr, false);
  }

  public static dht_error_alert cast_to_dht_error_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_error_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_error_alert(cPtr, false);
  }

  public static dht_immutable_item_alert cast_to_dht_immutable_item_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_immutable_item_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_immutable_item_alert(cPtr, false);
  }

  public static dht_mutable_item_alert cast_to_dht_mutable_item_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_mutable_item_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_mutable_item_alert(cPtr, false);
  }

  public static dht_put_alert cast_to_dht_put_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_put_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_put_alert(cPtr, false);
  }

  public static dht_outgoing_get_peers_alert cast_to_dht_outgoing_get_peers_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_outgoing_get_peers_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_outgoing_get_peers_alert(cPtr, false);
  }

  public static log_alert cast_to_log_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_log_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new log_alert(cPtr, false);
  }

  public static peer_log_alert cast_to_peer_log_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_peer_log_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new peer_log_alert(cPtr, false);
  }

  public static dht_stats_alert cast_to_dht_stats_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_stats_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_stats_alert(cPtr, false);
  }

  public static dht_log_alert cast_to_dht_log_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_log_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_log_alert(cPtr, false);
  }

  public static dht_pkt_alert cast_to_dht_pkt_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_pkt_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_pkt_alert(cPtr, false);
  }

  public static dht_get_peers_reply_alert cast_to_dht_get_peers_reply_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_get_peers_reply_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_get_peers_reply_alert(cPtr, false);
  }

  public static dht_direct_response_alert cast_to_dht_direct_response_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_direct_response_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_direct_response_alert(cPtr, false);
  }

  public static picker_log_alert cast_to_picker_log_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_picker_log_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new picker_log_alert(cPtr, false);
  }

  public static session_error_alert cast_to_session_error_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_session_error_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new session_error_alert(cPtr, false);
  }

  public static dht_live_nodes_alert cast_to_dht_live_nodes_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_live_nodes_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_live_nodes_alert(cPtr, false);
  }

  public static session_stats_header_alert cast_to_session_stats_header_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_session_stats_header_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new session_stats_header_alert(cPtr, false);
  }

  public static dht_sample_infohashes_alert cast_to_dht_sample_infohashes_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_dht_sample_infohashes_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new dht_sample_infohashes_alert(cPtr, false);
  }

  public static alerts_dropped_alert cast_to_alerts_dropped_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_alerts_dropped_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new alerts_dropped_alert(cPtr, false);
  }

  public static socks5_alert cast_to_socks5_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_socks5_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new socks5_alert(cPtr, false);
  }

  public static communication_new_device_id_alert cast_to_communication_new_device_id_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_new_device_id_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_new_device_id_alert(cPtr, false);
  }

  public static communication_new_message_alert cast_to_communication_new_message_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_new_message_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_new_message_alert(cPtr, false);
  }

  public static communication_confirmation_root_alert cast_to_communication_confirmation_root_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_confirmation_root_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_confirmation_root_alert(cPtr, false);
  }

  public static communication_syncing_message_alert cast_to_communication_syncing_message_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_syncing_message_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_syncing_message_alert(cPtr, false);
  }

  public static communication_friend_info_alert cast_to_communication_friend_info_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_friend_info_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_friend_info_alert(cPtr, false);
  }

  public static communication_log_alert cast_to_communication_log_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_log_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_log_alert(cPtr, false);
  }

  public static communication_last_seen_alert cast_to_communication_last_seen_alert(alert a) {
    long cPtr = libTAU_jni.alert_cast_to_communication_last_seen_alert(alert.getCPtr(a), a);
    return (cPtr == 0) ? null : new communication_last_seen_alert(cPtr, false);
  }

  public final static alert_category_t error_notification = new alert_category_t(libTAU_jni.alert_error_notification_get(), false);
  public final static alert_category_t peer_notification = new alert_category_t(libTAU_jni.alert_peer_notification_get(), false);
  public final static alert_category_t port_mapping_notification = new alert_category_t(libTAU_jni.alert_port_mapping_notification_get(), false);
  public final static alert_category_t storage_notification = new alert_category_t(libTAU_jni.alert_storage_notification_get(), false);
  public final static alert_category_t tracker_notification = new alert_category_t(libTAU_jni.alert_tracker_notification_get(), false);
  public final static alert_category_t connect_notification = new alert_category_t(libTAU_jni.alert_connect_notification_get(), false);
  public final static alert_category_t status_notification = new alert_category_t(libTAU_jni.alert_status_notification_get(), false);
  public final static alert_category_t ip_block_notification = new alert_category_t(libTAU_jni.alert_ip_block_notification_get(), false);
  public final static alert_category_t performance_warning = new alert_category_t(libTAU_jni.alert_performance_warning_get(), false);
  public final static alert_category_t dht_notification = new alert_category_t(libTAU_jni.alert_dht_notification_get(), false);
  public final static alert_category_t session_log_notification = new alert_category_t(libTAU_jni.alert_session_log_notification_get(), false);
  public final static alert_category_t torrent_log_notification = new alert_category_t(libTAU_jni.alert_torrent_log_notification_get(), false);
  public final static alert_category_t peer_log_notification = new alert_category_t(libTAU_jni.alert_peer_log_notification_get(), false);
  public final static alert_category_t incoming_request_notification = new alert_category_t(libTAU_jni.alert_incoming_request_notification_get(), false);
  public final static alert_category_t dht_log_notification = new alert_category_t(libTAU_jni.alert_dht_log_notification_get(), false);
  public final static alert_category_t dht_operation_notification = new alert_category_t(libTAU_jni.alert_dht_operation_notification_get(), false);
  public final static alert_category_t port_mapping_log_notification = new alert_category_t(libTAU_jni.alert_port_mapping_log_notification_get(), false);
  public final static alert_category_t picker_log_notification = new alert_category_t(libTAU_jni.alert_picker_log_notification_get(), false);
  public final static alert_category_t file_progress_notification = new alert_category_t(libTAU_jni.alert_file_progress_notification_get(), false);
  public final static alert_category_t piece_progress_notification = new alert_category_t(libTAU_jni.alert_piece_progress_notification_get(), false);
  public final static alert_category_t upload_notification = new alert_category_t(libTAU_jni.alert_upload_notification_get(), false);
  public final static alert_category_t block_progress_notification = new alert_category_t(libTAU_jni.alert_block_progress_notification_get(), false);
  public final static alert_category_t all_categories = new alert_category_t(libTAU_jni.alert_all_categories_get(), false);
}