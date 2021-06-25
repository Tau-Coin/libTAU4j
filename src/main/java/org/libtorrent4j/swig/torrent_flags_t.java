/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class torrent_flags_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected torrent_flags_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(torrent_flags_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_torrent_flags_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public torrent_flags_t() {
    this(libtorrent_jni.new_torrent_flags_t(), true);
  }

  public static torrent_flags_t all() {
    return new torrent_flags_t(libtorrent_jni.torrent_flags_t_all(), true);
  }

  public boolean non_zero() {
    return libtorrent_jni.torrent_flags_t_non_zero(swigCPtr, this);
  }

  public boolean eq(torrent_flags_t f) {
    return libtorrent_jni.torrent_flags_t_eq(swigCPtr, this, torrent_flags_t.getCPtr(f), f);
  }

  public boolean ne(torrent_flags_t f) {
    return libtorrent_jni.torrent_flags_t_ne(swigCPtr, this, torrent_flags_t.getCPtr(f), f);
  }

  public torrent_flags_t or_(torrent_flags_t other) {
    return new torrent_flags_t(libtorrent_jni.torrent_flags_t_or_(swigCPtr, this, torrent_flags_t.getCPtr(other), other), true);
  }

  public torrent_flags_t and_(torrent_flags_t other) {
    return new torrent_flags_t(libtorrent_jni.torrent_flags_t_and_(swigCPtr, this, torrent_flags_t.getCPtr(other), other), true);
  }

  public torrent_flags_t xor(torrent_flags_t other) {
    return new torrent_flags_t(libtorrent_jni.torrent_flags_t_xor(swigCPtr, this, torrent_flags_t.getCPtr(other), other), true);
  }

  public torrent_flags_t inv() {
    return new torrent_flags_t(libtorrent_jni.torrent_flags_t_inv(swigCPtr, this), true);
  }

  public int to_int() {
    return libtorrent_jni.torrent_flags_t_to_int(swigCPtr, this);
  }

  public static torrent_flags_t from_int(int val) {
    return new torrent_flags_t(libtorrent_jni.torrent_flags_t_from_int(val), true);
  }

}
