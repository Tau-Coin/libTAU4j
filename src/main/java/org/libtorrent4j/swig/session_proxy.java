/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class session_proxy {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected session_proxy(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(session_proxy obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_session_proxy(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public session_proxy() {
    this(libtorrent_jni.new_session_proxy__SWIG_0(), true);
  }

  public session_proxy(session_proxy arg0) {
    this(libtorrent_jni.new_session_proxy__SWIG_1(session_proxy.getCPtr(arg0), arg0), true);
  }

}
