/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class address_sha256_hash_pair_vector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected address_sha256_hash_pair_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(address_sha256_hash_pair_vector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_address_sha256_hash_pair_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public address_sha256_hash_pair_vector() {
    this(libtorrent_jni.new_address_sha256_hash_pair_vector__SWIG_0(), true);
  }

  public address_sha256_hash_pair_vector(long n) {
    this(libtorrent_jni.new_address_sha256_hash_pair_vector__SWIG_1(n), true);
  }

  public long size() {
    return libtorrent_jni.address_sha256_hash_pair_vector_size(swigCPtr, this);
  }

  public long capacity() {
    return libtorrent_jni.address_sha256_hash_pair_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libtorrent_jni.address_sha256_hash_pair_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libtorrent_jni.address_sha256_hash_pair_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libtorrent_jni.address_sha256_hash_pair_vector_clear(swigCPtr, this);
  }

  public void add(address_sha256_hash_pair x) {
    libtorrent_jni.address_sha256_hash_pair_vector_add(swigCPtr, this, address_sha256_hash_pair.getCPtr(x), x);
  }

  public address_sha256_hash_pair get(int i) {
    return new address_sha256_hash_pair(libtorrent_jni.address_sha256_hash_pair_vector_get(swigCPtr, this, i), false);
  }

  public void set(int i, address_sha256_hash_pair val) {
    libtorrent_jni.address_sha256_hash_pair_vector_set(swigCPtr, this, i, address_sha256_hash_pair.getCPtr(val), val);
  }

}
