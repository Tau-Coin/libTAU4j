/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class create_torrent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected create_torrent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(create_torrent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_create_torrent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public create_torrent(file_storage fs, int piece_size, create_flags_t flags) {
    this(libTAU_jni.new_create_torrent__SWIG_0(file_storage.getCPtr(fs), fs, piece_size, create_flags_t.getCPtr(flags), flags), true);
  }

  public create_torrent(file_storage fs, int piece_size) {
    this(libTAU_jni.new_create_torrent__SWIG_1(file_storage.getCPtr(fs), fs, piece_size), true);
  }

  public create_torrent(file_storage fs) {
    this(libTAU_jni.new_create_torrent__SWIG_2(file_storage.getCPtr(fs), fs), true);
  }

  public create_torrent(torrent_info ti) {
    this(libTAU_jni.new_create_torrent__SWIG_3(torrent_info.getCPtr(ti), ti), true);
  }

  public entry generate() {
    return new entry(libTAU_jni.create_torrent_generate(swigCPtr, this), true);
  }

  public file_storage files() {
    return new file_storage(libTAU_jni.create_torrent_files(swigCPtr, this), false);
  }

  public void set_comment(String str) {
    libTAU_jni.create_torrent_set_comment(swigCPtr, this, str);
  }

  public void set_creator(String str) {
    libTAU_jni.create_torrent_set_creator(swigCPtr, this, str);
  }

  public void set_creation_date(long timestamp) {
    libTAU_jni.create_torrent_set_creation_date(swigCPtr, this, timestamp);
  }

  public void set_hash(int index, sha1_hash h) {
    libTAU_jni.create_torrent_set_hash(swigCPtr, this, index, sha1_hash.getCPtr(h), h);
  }

  public void add_node(string_int_pair node) {
    libTAU_jni.create_torrent_add_node(swigCPtr, this, string_int_pair.getCPtr(node), node);
  }

  public void set_priv(boolean p) {
    libTAU_jni.create_torrent_set_priv(swigCPtr, this, p);
  }

  public boolean priv() {
    return libTAU_jni.create_torrent_priv(swigCPtr, this);
  }

  public boolean is_v2_only() {
    return libTAU_jni.create_torrent_is_v2_only(swigCPtr, this);
  }

  public boolean is_v1_only() {
    return libTAU_jni.create_torrent_is_v1_only(swigCPtr, this);
  }

  public int num_pieces() {
    return libTAU_jni.create_torrent_num_pieces(swigCPtr, this);
  }

  public int piece_length() {
    return libTAU_jni.create_torrent_piece_length(swigCPtr, this);
  }

  public int piece_size(int i) {
    return libTAU_jni.create_torrent_piece_size(swigCPtr, this, i);
  }

  public void add_similar_torrent(sha1_hash ih) {
    libTAU_jni.create_torrent_add_similar_torrent(swigCPtr, this, sha1_hash.getCPtr(ih), ih);
  }

  public void add_url_seed(String url) {
    libTAU_jni.create_torrent_add_url_seed(swigCPtr, this, url);
  }

  public void add_tracker(String url) {
    libTAU_jni.create_torrent_add_tracker__SWIG_0(swigCPtr, this, url);
  }

  public void add_tracker(String url, int tier) {
    libTAU_jni.create_torrent_add_tracker__SWIG_1(swigCPtr, this, url, tier);
  }

  public void add_collection(String c) {
    libTAU_jni.create_torrent_add_collection(swigCPtr, this, c);
  }

  public void set_root_cert(byte_vector cert) {
    libTAU_jni.create_torrent_set_root_cert(swigCPtr, this, byte_vector.getCPtr(cert), cert);
  }

  public void set_hash2(int file, int piece, sha256_hash h) {
    libTAU_jni.create_torrent_set_hash2(swigCPtr, this, file, piece, sha256_hash.getCPtr(h), h);
  }

  public final static create_flags_t modification_time = new create_flags_t(libTAU_jni.create_torrent_modification_time_get(), false);
  public final static create_flags_t symlinks = new create_flags_t(libTAU_jni.create_torrent_symlinks_get(), false);
  public final static create_flags_t v2_only = new create_flags_t(libTAU_jni.create_torrent_v2_only_get(), false);
  public final static create_flags_t v1_only = new create_flags_t(libTAU_jni.create_torrent_v1_only_get(), false);
  public final static create_flags_t canonical_files = new create_flags_t(libTAU_jni.create_torrent_canonical_files_get(), false);
}