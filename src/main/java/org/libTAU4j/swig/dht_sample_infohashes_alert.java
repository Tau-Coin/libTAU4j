/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class dht_sample_infohashes_alert extends alert {
  private transient long swigCPtr;

  protected dht_sample_infohashes_alert(long cPtr, boolean cMemoryOwn) {
    super(libTAU_jni.dht_sample_infohashes_alert_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(dht_sample_infohashes_alert obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_dht_sample_infohashes_alert(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public int type() {
    return libTAU_jni.dht_sample_infohashes_alert_type(swigCPtr, this);
  }

  public alert_category_t category() {
    return new alert_category_t(libTAU_jni.dht_sample_infohashes_alert_category(swigCPtr, this), true);
  }

  public String what() {
    return libTAU_jni.dht_sample_infohashes_alert_what(swigCPtr, this);
  }

  public String message() {
    return libTAU_jni.dht_sample_infohashes_alert_message(swigCPtr, this);
  }

  public void setNode_id(sha256_hash value) {
    libTAU_jni.dht_sample_infohashes_alert_node_id_set(swigCPtr, this, sha256_hash.getCPtr(value), value);
  }

  public sha256_hash getNode_id() {
    long cPtr = libTAU_jni.dht_sample_infohashes_alert_node_id_get(swigCPtr, this);
    return (cPtr == 0) ? null : new sha256_hash(cPtr, false);
  }

  public int getNum_infohashes() {
    return libTAU_jni.dht_sample_infohashes_alert_num_infohashes_get(swigCPtr, this);
  }

  public int num_samples() {
    return libTAU_jni.dht_sample_infohashes_alert_num_samples(swigCPtr, this);
  }

  public sha256_hash_vector samples() {
    return new sha256_hash_vector(libTAU_jni.dht_sample_infohashes_alert_samples(swigCPtr, this), true);
  }

  public int num_nodes() {
    return libTAU_jni.dht_sample_infohashes_alert_num_nodes(swigCPtr, this);
  }

  public sha256_hash_udp_endpoint_pair_vector nodes() {
    return new sha256_hash_udp_endpoint_pair_vector(libTAU_jni.dht_sample_infohashes_alert_nodes(swigCPtr, this), true);
  }

  public udp_endpoint get_endpoint() {
    return new udp_endpoint(libTAU_jni.dht_sample_infohashes_alert_get_endpoint(swigCPtr, this), true);
  }

  public long get_interval() {
    return libTAU_jni.dht_sample_infohashes_alert_get_interval(swigCPtr, this);
  }

  public final static alert_category_t static_category = new alert_category_t(libTAU_jni.dht_sample_infohashes_alert_static_category_get(), false);
  public final static alert_priority priority = alert_priority.swigToEnum(libTAU_jni.dht_sample_infohashes_alert_priority_get());
  public final static int alert_type = libTAU_jni.dht_sample_infohashes_alert_alert_type_get();
}