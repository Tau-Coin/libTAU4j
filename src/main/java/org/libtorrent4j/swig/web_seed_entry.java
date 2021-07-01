/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class web_seed_entry {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected web_seed_entry(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(web_seed_entry obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_web_seed_entry(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public web_seed_entry(String url_, String auth_, string_string_pair_vector extra_headers_) {
    this(libtorrent_jni.new_web_seed_entry__SWIG_0(url_, auth_, string_string_pair_vector.getCPtr(extra_headers_), extra_headers_), true);
  }

  public web_seed_entry(String url_, String auth_) {
    this(libtorrent_jni.new_web_seed_entry__SWIG_1(url_, auth_), true);
  }

  public web_seed_entry(String url_) {
    this(libtorrent_jni.new_web_seed_entry__SWIG_2(url_), true);
  }

  public web_seed_entry(web_seed_entry arg0) {
    this(libtorrent_jni.new_web_seed_entry__SWIG_3(web_seed_entry.getCPtr(arg0), arg0), true);
  }

  public boolean eq(web_seed_entry e) {
    return libtorrent_jni.web_seed_entry_eq(swigCPtr, this, web_seed_entry.getCPtr(e), e);
  }

  public boolean lt(web_seed_entry e) {
    return libtorrent_jni.web_seed_entry_lt(swigCPtr, this, web_seed_entry.getCPtr(e), e);
  }

  public void setUrl(String value) {
    libtorrent_jni.web_seed_entry_url_set(swigCPtr, this, value);
  }

  public String getUrl() {
    return libtorrent_jni.web_seed_entry_url_get(swigCPtr, this);
  }

  public void setAuth(String value) {
    libtorrent_jni.web_seed_entry_auth_set(swigCPtr, this, value);
  }

  public String getAuth() {
    return libtorrent_jni.web_seed_entry_auth_get(swigCPtr, this);
  }

  public void setExtra_headers(string_string_pair_vector value) {
    libtorrent_jni.web_seed_entry_extra_headers_set(swigCPtr, this, string_string_pair_vector.getCPtr(value), value);
  }

  public string_string_pair_vector getExtra_headers() {
    long cPtr = libtorrent_jni.web_seed_entry_extra_headers_get(swigCPtr, this);
    return (cPtr == 0) ? null : new string_string_pair_vector(cPtr, false);
  }

}
