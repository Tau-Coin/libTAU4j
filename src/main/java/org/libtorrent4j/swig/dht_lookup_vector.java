/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class dht_lookup_vector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected dht_lookup_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(dht_lookup_vector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_dht_lookup_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public dht_lookup_vector() {
    this(libtorrent_jni.new_dht_lookup_vector__SWIG_0(), true);
  }

  public dht_lookup_vector(long n) {
    this(libtorrent_jni.new_dht_lookup_vector__SWIG_1(n), true);
  }

  public long size() {
    return libtorrent_jni.dht_lookup_vector_size(swigCPtr, this);
  }

  public long capacity() {
    return libtorrent_jni.dht_lookup_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libtorrent_jni.dht_lookup_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libtorrent_jni.dht_lookup_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libtorrent_jni.dht_lookup_vector_clear(swigCPtr, this);
  }

  public void add(dht_lookup x) {
    libtorrent_jni.dht_lookup_vector_add(swigCPtr, this, dht_lookup.getCPtr(x), x);
  }

  public dht_lookup get(int i) {
    return new dht_lookup(libtorrent_jni.dht_lookup_vector_get(swigCPtr, this, i), false);
  }

  public void set(int i, dht_lookup val) {
    libtorrent_jni.dht_lookup_vector_set(swigCPtr, this, i, dht_lookup.getCPtr(val), val);
  }

}
