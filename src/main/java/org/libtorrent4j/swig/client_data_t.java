/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class client_data_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected client_data_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(client_data_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_client_data_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public client_data_t() {
    this(libtorrent_jni.new_client_data_t__SWIG_0(), true);
  }

  public client_data_t(long arg0) {
    this(libtorrent_jni.new_client_data_t__SWIG_1(arg0), true);
  }

  public long get() {
    return libtorrent_jni.client_data_t_get(swigCPtr, this);
  }

}
