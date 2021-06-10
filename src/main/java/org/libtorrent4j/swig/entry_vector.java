/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class entry_vector extends java.util.AbstractList<entry> implements java.util.RandomAccess {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected entry_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(entry_vector obj) {
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
        libtorrent_jni.delete_entry_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public entry_vector(entry[] initialElements) {
    this();
    reserve(initialElements.length);

    for (entry element : initialElements) {
      add(element);
    }
  }

  public entry_vector(Iterable<entry> initialElements) {
    this();
    for (entry element : initialElements) {
      add(element);
    }
  }

  public entry get(int index) {
    return doGet(index);
  }

  public entry set(int index, entry e) {
    return doSet(index, e);
  }

  public boolean add(entry e) {
    modCount++;
    doAdd(e);
    return true;
  }

  public void add(int index, entry e) {
    modCount++;
    doAdd(index, e);
  }

  public entry remove(int index) {
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

  public entry_vector() {
    this(libtorrent_jni.new_entry_vector__SWIG_0(), true);
  }

  public entry_vector(entry_vector other) {
    this(libtorrent_jni.new_entry_vector__SWIG_1(entry_vector.getCPtr(other), other), true);
  }

  public long capacity() {
    return libtorrent_jni.entry_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libtorrent_jni.entry_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libtorrent_jni.entry_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libtorrent_jni.entry_vector_clear(swigCPtr, this);
  }

  public entry_vector(int count, entry value) {
    this(libtorrent_jni.new_entry_vector__SWIG_2(count, entry.getCPtr(value), value), true);
  }

  private int doSize() {
    return libtorrent_jni.entry_vector_doSize(swigCPtr, this);
  }

  private void doAdd(entry x) {
    libtorrent_jni.entry_vector_doAdd__SWIG_0(swigCPtr, this, entry.getCPtr(x), x);
  }

  private void doAdd(int index, entry x) {
    libtorrent_jni.entry_vector_doAdd__SWIG_1(swigCPtr, this, index, entry.getCPtr(x), x);
  }

  private entry doRemove(int index) {
    return new entry(libtorrent_jni.entry_vector_doRemove(swigCPtr, this, index), true);
  }

  private entry doGet(int index) {
    return new entry(libtorrent_jni.entry_vector_doGet(swigCPtr, this, index), false);
  }

  private entry doSet(int index, entry val) {
    return new entry(libtorrent_jni.entry_vector_doSet(swigCPtr, this, index, entry.getCPtr(val), val), true);
  }

  private void doRemoveRange(int fromIndex, int toIndex) {
    libtorrent_jni.entry_vector_doRemoveRange(swigCPtr, this, fromIndex, toIndex);
  }

}