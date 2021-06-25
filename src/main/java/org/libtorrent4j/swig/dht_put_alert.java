/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class dht_put_alert extends alert {
  private transient long swigCPtr;

  protected dht_put_alert(long cPtr, boolean cMemoryOwn) {
    super(libtorrent_jni.dht_put_alert_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(dht_put_alert obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_dht_put_alert(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public int type() {
    return libtorrent_jni.dht_put_alert_type(swigCPtr, this);
  }

  public alert_category_t category() {
    return new alert_category_t(libtorrent_jni.dht_put_alert_category(swigCPtr, this), true);
  }

  public String what() {
    return libtorrent_jni.dht_put_alert_what(swigCPtr, this);
  }

  public String message() {
    return libtorrent_jni.dht_put_alert_message(swigCPtr, this);
  }

  public void setTarget(sha256_hash value) {
    libtorrent_jni.dht_put_alert_target_set(swigCPtr, this, sha256_hash.getCPtr(value), value);
  }

  public sha256_hash getTarget() {
    long cPtr = libtorrent_jni.dht_put_alert_target_get(swigCPtr, this);
    return (cPtr == 0) ? null : new sha256_hash(cPtr, false);
  }

  public void setSalt(String value) {
    libtorrent_jni.dht_put_alert_salt_set(swigCPtr, this, value);
  }

  public String getSalt() {
    return libtorrent_jni.dht_put_alert_salt_get(swigCPtr, this);
  }

  public void setSeq(long value) {
    libtorrent_jni.dht_put_alert_seq_set(swigCPtr, this, value);
  }

  public long getSeq() {
    return libtorrent_jni.dht_put_alert_seq_get(swigCPtr, this);
  }

  public void setNum_success(int value) {
    libtorrent_jni.dht_put_alert_num_success_set(swigCPtr, this, value);
  }

  public int getNum_success() {
    return libtorrent_jni.dht_put_alert_num_success_get(swigCPtr, this);
  }

  public byte_array_32 get_public_key() {
    return new byte_array_32(libtorrent_jni.dht_put_alert_get_public_key(swigCPtr, this), true);
  }

  public byte_array_64 get_signature() {
    return new byte_array_64(libtorrent_jni.dht_put_alert_get_signature(swigCPtr, this), true);
  }

  public byte_vector get_salt() {
    return new byte_vector(libtorrent_jni.dht_put_alert_get_salt(swigCPtr, this), true);
  }

  public long get_seq() {
    return libtorrent_jni.dht_put_alert_get_seq(swigCPtr, this);
  }

  public final static alert_priority priority = alert_priority.swigToEnum(libtorrent_jni.dht_put_alert_priority_get());
  public final static int alert_type = libtorrent_jni.dht_put_alert_alert_type_get();
  public final static alert_category_t static_category = new alert_category_t(libtorrent_jni.dht_put_alert_static_category_get(), false);
}
