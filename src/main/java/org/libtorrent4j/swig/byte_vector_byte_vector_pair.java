/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class byte_vector_byte_vector_pair {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected byte_vector_byte_vector_pair(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(byte_vector_byte_vector_pair obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_byte_vector_byte_vector_pair(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public byte_vector_byte_vector_pair() {
    this(libtorrent_jni.new_byte_vector_byte_vector_pair__SWIG_0(), true);
  }

  public byte_vector_byte_vector_pair(byte_vector first, byte_vector second) {
    this(libtorrent_jni.new_byte_vector_byte_vector_pair__SWIG_1(byte_vector.getCPtr(first), first, byte_vector.getCPtr(second), second), true);
  }

  public byte_vector_byte_vector_pair(byte_vector_byte_vector_pair p) {
    this(libtorrent_jni.new_byte_vector_byte_vector_pair__SWIG_2(byte_vector_byte_vector_pair.getCPtr(p), p), true);
  }

  public void setFirst(byte_vector value) {
    libtorrent_jni.byte_vector_byte_vector_pair_first_set(swigCPtr, this, byte_vector.getCPtr(value), value);
  }

  public byte_vector getFirst() {
    long cPtr = libtorrent_jni.byte_vector_byte_vector_pair_first_get(swigCPtr, this);
    return (cPtr == 0) ? null : new byte_vector(cPtr, false);
  }

  public void setSecond(byte_vector value) {
    libtorrent_jni.byte_vector_byte_vector_pair_second_set(swigCPtr, this, byte_vector.getCPtr(value), value);
  }

  public byte_vector getSecond() {
    long cPtr = libtorrent_jni.byte_vector_byte_vector_pair_second_get(swigCPtr, this);
    return (cPtr == 0) ? null : new byte_vector(cPtr, false);
  }

}
