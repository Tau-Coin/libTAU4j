/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class torrent_handle_vector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected torrent_handle_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(torrent_handle_vector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_torrent_handle_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public torrent_handle_vector() {
    this(libtorrent_jni.new_torrent_handle_vector__SWIG_0(), true);
  }

  public torrent_handle_vector(long n) {
    this(libtorrent_jni.new_torrent_handle_vector__SWIG_1(n), true);
  }

  public long size() {
    return libtorrent_jni.torrent_handle_vector_size(swigCPtr, this);
  }

  public long capacity() {
    return libtorrent_jni.torrent_handle_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libtorrent_jni.torrent_handle_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libtorrent_jni.torrent_handle_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libtorrent_jni.torrent_handle_vector_clear(swigCPtr, this);
  }

  public void add(torrent_handle x) {
    libtorrent_jni.torrent_handle_vector_add(swigCPtr, this, torrent_handle.getCPtr(x), x);
  }

  public torrent_handle get(int i) {
    return new torrent_handle(libtorrent_jni.torrent_handle_vector_get(swigCPtr, this, i), false);
  }

  public void set(int i, torrent_handle val) {
    libtorrent_jni.torrent_handle_vector_set(swigCPtr, this, i, torrent_handle.getCPtr(val), val);
  }

}
