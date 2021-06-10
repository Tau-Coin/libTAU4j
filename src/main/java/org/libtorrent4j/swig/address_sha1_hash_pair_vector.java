/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class address_sha1_hash_pair_vector extends java.util.AbstractList<address_sha1_hash_pair> implements java.util.RandomAccess {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected address_sha1_hash_pair_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(address_sha1_hash_pair_vector obj) {
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
        libtorrent_jni.delete_address_sha1_hash_pair_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public address_sha1_hash_pair_vector(address_sha1_hash_pair[] initialElements) {
    this();
    reserve(initialElements.length);

    for (address_sha1_hash_pair element : initialElements) {
      add(element);
    }
  }

  public address_sha1_hash_pair_vector(Iterable<address_sha1_hash_pair> initialElements) {
    this();
    for (address_sha1_hash_pair element : initialElements) {
      add(element);
    }
  }

  public address_sha1_hash_pair get(int index) {
    return doGet(index);
  }

  public address_sha1_hash_pair set(int index, address_sha1_hash_pair e) {
    return doSet(index, e);
  }

  public boolean add(address_sha1_hash_pair e) {
    modCount++;
    doAdd(e);
    return true;
  }

  public void add(int index, address_sha1_hash_pair e) {
    modCount++;
    doAdd(index, e);
  }

  public address_sha1_hash_pair remove(int index) {
    modCount++;
    return doRemove(index);
  }

  protected void removeRange(int fromIndex, int toIndex) {
    modCount++;
    doRemoveRange(fromIndex, toIndex);
  }

  public int size() {
    return doSize();
  }

  public address_sha1_hash_pair_vector() {
    this(libtorrent_jni.new_address_sha1_hash_pair_vector__SWIG_0(), true);
  }

  public address_sha1_hash_pair_vector(address_sha1_hash_pair_vector other) {
    this(libtorrent_jni.new_address_sha1_hash_pair_vector__SWIG_1(address_sha1_hash_pair_vector.getCPtr(other), other), true);
  }

  public long capacity() {
    return libtorrent_jni.address_sha1_hash_pair_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libtorrent_jni.address_sha1_hash_pair_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libtorrent_jni.address_sha1_hash_pair_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libtorrent_jni.address_sha1_hash_pair_vector_clear(swigCPtr, this);
  }

  public address_sha1_hash_pair_vector(int count, address_sha1_hash_pair value) {
    this(libtorrent_jni.new_address_sha1_hash_pair_vector__SWIG_2(count, address_sha1_hash_pair.getCPtr(value), value), true);
  }

  private int doSize() {
    return libtorrent_jni.address_sha1_hash_pair_vector_doSize(swigCPtr, this);
  }

  private void doAdd(address_sha1_hash_pair x) {
    libtorrent_jni.address_sha1_hash_pair_vector_doAdd__SWIG_0(swigCPtr, this, address_sha1_hash_pair.getCPtr(x), x);
  }

  private void doAdd(int index, address_sha1_hash_pair x) {
    libtorrent_jni.address_sha1_hash_pair_vector_doAdd__SWIG_1(swigCPtr, this, index, address_sha1_hash_pair.getCPtr(x), x);
  }

  private address_sha1_hash_pair doRemove(int index) {
    return new address_sha1_hash_pair(libtorrent_jni.address_sha1_hash_pair_vector_doRemove(swigCPtr, this, index), true);
  }

  private address_sha1_hash_pair doGet(int index) {
    return new address_sha1_hash_pair(libtorrent_jni.address_sha1_hash_pair_vector_doGet(swigCPtr, this, index), false);
  }

  private address_sha1_hash_pair doSet(int index, address_sha1_hash_pair val) {
    return new address_sha1_hash_pair(libtorrent_jni.address_sha1_hash_pair_vector_doSet(swigCPtr, this, index, address_sha1_hash_pair.getCPtr(val), val), true);
  }

  private void doRemoveRange(int fromIndex, int toIndex) {
    libtorrent_jni.address_sha1_hash_pair_vector_doRemoveRange(swigCPtr, this, fromIndex, toIndex);
  }

}
