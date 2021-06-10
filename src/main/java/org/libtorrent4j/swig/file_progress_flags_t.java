/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class file_progress_flags_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected file_progress_flags_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(file_progress_flags_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_file_progress_flags_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public file_progress_flags_t() {
    this(libtorrent_jni.new_file_progress_flags_t(), true);
  }

  public static file_progress_flags_t all() {
    return new file_progress_flags_t(libtorrent_jni.file_progress_flags_t_all(), true);
  }

  public boolean non_zero() {
    return libtorrent_jni.file_progress_flags_t_non_zero(swigCPtr, this);
  }

  public boolean eq(file_progress_flags_t f) {
    return libtorrent_jni.file_progress_flags_t_eq(swigCPtr, this, file_progress_flags_t.getCPtr(f), f);
  }

  public boolean ne(file_progress_flags_t f) {
    return libtorrent_jni.file_progress_flags_t_ne(swigCPtr, this, file_progress_flags_t.getCPtr(f), f);
  }

  public file_progress_flags_t or_(file_progress_flags_t other) {
    return new file_progress_flags_t(libtorrent_jni.file_progress_flags_t_or_(swigCPtr, this, file_progress_flags_t.getCPtr(other), other), true);
  }

  public file_progress_flags_t and_(file_progress_flags_t other) {
    return new file_progress_flags_t(libtorrent_jni.file_progress_flags_t_and_(swigCPtr, this, file_progress_flags_t.getCPtr(other), other), true);
  }

  public file_progress_flags_t xor(file_progress_flags_t other) {
    return new file_progress_flags_t(libtorrent_jni.file_progress_flags_t_xor(swigCPtr, this, file_progress_flags_t.getCPtr(other), other), true);
  }

  public file_progress_flags_t inv() {
    return new file_progress_flags_t(libtorrent_jni.file_progress_flags_t_inv(swigCPtr, this), true);
  }

  public int to_int() {
    return libtorrent_jni.file_progress_flags_t_to_int(swigCPtr, this);
  }

  public static file_progress_flags_t from_int(int val) {
    return new file_progress_flags_t(libtorrent_jni.file_progress_flags_t_from_int(val), true);
  }

}