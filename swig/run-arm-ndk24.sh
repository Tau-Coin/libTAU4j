"ar"  rc "bin/release/android/arm64-v8a/libtry_signal.a" "bin/release/android/arm64-v8a/signal_error_code.o" "bin/release/android/arm64-v8a/try_signal.o";

"${ANDROID_TOOLCHAIN}/bin/llvm-ranlib" "bin/release/android/arm64-v8a/libtry_signal.a";

"ar"  rc "bin/release/android/arm64-v8a/libboost_json.a" "bin/release/android/arm64-v8a/src.o";
"${ANDROID_TOOLCHAIN}/bin/llvm-ranlib" "bin/release/android/arm64-v8a/libboost_json.a";

"ar"  rc "bin/release/android/arm64-v8a/libboost_container.a" "bin/release/android/arm64-v8a/alloc_lib.o" "bin/release/android/arm64-v8a/dlmalloc.o" "bin/release/android/arm64-v8a/global_resource.o" "bin/release/android/arm64-v8a/monotonic_buffer_resource.o" "bin/release/android/arm64-v8a/pool_resource.o" "bin/release/android/arm64-v8a/synchronized_pool_resource.o" "bin/release/android/arm64-v8a/unsynchronized_pool_resource.o";
"${ANDROID_TOOLCHAIN}/bin/llvm-ranlib" "bin/release/android/arm64-v8a/libboost_container.a";


"ar"  rc "bin/release/android/arm64-v8a/libboost_system.a" "bin/release/android/arm64-v8a/error_code.o";
"${ANDROID_TOOLCHAIN}/bin/llvm-ranlib" "bin/release/android/arm64-v8a/libboost_system.a";


"ar"  rc "bin/release/android/armeabi-v7a/libTAU.a" "bin/release/android/armeabi-v7a/src/alert.o" "bin/release/android/armeabi-v7a/src/alert_manager.o" "bin/release/android/armeabi-v7a/src/announce_entry.o" "bin/release/android/armeabi-v7a/src/assert.o" "bin/release/android/armeabi-v7a/src/bandwidth_limit.o" "bin/release/android/armeabi-v7a/src/bandwidth_manager.o" "bin/release/android/armeabi-v7a/src/bandwidth_queue_entry.o" "bin/release/android/armeabi-v7a/src/bdecode.o" "bin/release/android/armeabi-v7a/src/bitfield.o" "bin/release/android/armeabi-v7a/src/bloom_filter.o" "bin/release/android/armeabi-v7a/src/close_reason.o" "bin/release/android/armeabi-v7a/src/common.o" "bin/release/android/armeabi-v7a/src/common_data.o" "bin/release/android/armeabi-v7a/src/cpuid.o" "bin/release/android/armeabi-v7a/src/crc32c.o" "bin/release/android/armeabi-v7a/src/directory.o" "bin/release/android/armeabi-v7a/src/disk_buffer_holder.o" "bin/release/android/armeabi-v7a/src/entry.o" "bin/release/android/armeabi-v7a/src/error_code.o" "bin/release/android/armeabi-v7a/src/escape_string.o" "bin/release/android/armeabi-v7a/src/string_util.o" "bin/release/android/armeabi-v7a/src/path.o" "bin/release/android/armeabi-v7a/src/fingerprint.o" "bin/release/android/armeabi-v7a/src/gzip.o" "bin/release/android/armeabi-v7a/src/hasher.o" "bin/release/android/armeabi-v7a/src/hex.o" "bin/release/android/armeabi-v7a/src/http_connection.o" "bin/release/android/armeabi-v7a/src/http_parser.o" "bin/release/android/armeabi-v7a/src/identify_client.o" "bin/release/android/armeabi-v7a/src/ip_filter.o" "bin/release/android/armeabi-v7a/src/ip_helpers.o" "bin/release/android/armeabi-v7a/src/ip_notifier.o" "bin/release/android/armeabi-v7a/src/ip_voter.o" "bin/release/android/armeabi-v7a/src/listen_socket_handle.o" "bin/release/android/armeabi-v7a/src/platform_util.o" "bin/release/android/armeabi-v7a/src/instantiate_connection.o" "bin/release/android/armeabi-v7a/src/natpmp.o" "bin/release/android/armeabi-v7a/src/packet_buffer.o" "bin/release/android/armeabi-v7a/src/proxy_base.o" "bin/release/android/armeabi-v7a/src/puff.o" "bin/release/android/armeabi-v7a/src/random.o" "bin/release/android/armeabi-v7a/src/receive_buffer.o" "bin/release/android/armeabi-v7a/src/session.o" "bin/release/android/armeabi-v7a/src/session_params.o" "bin/release/android/armeabi-v7a/src/session_handle.o" "bin/release/android/armeabi-v7a/src/session_impl.o" "bin/release/android/armeabi-v7a/src/session_call.o" "bin/release/android/armeabi-v7a/src/settings_pack.o" "bin/release/android/armeabi-v7a/src/sha1.o" "bin/release/android/armeabi-v7a/src/sha1_hash.o" "bin/release/android/armeabi-v7a/src/sha256.o" "bin/release/android/armeabi-v7a/src/socket_io.o" "bin/release/android/armeabi-v7a/src/socket_type.o" "bin/release/android/armeabi-v7a/src/socks5_stream.o" "bin/release/android/armeabi-v7a/src/stat.o" "bin/release/android/armeabi-v7a/src/time.o" "bin/release/android/armeabi-v7a/src/tracker_manager.o" "bin/release/android/armeabi-v7a/src/udp_tracker_connection.o" "bin/release/android/armeabi-v7a/src/timestamp_history.o" "bin/release/android/armeabi-v7a/src/udp_socket.o" "bin/release/android/armeabi-v7a/src/upnp.o" "bin/release/android/armeabi-v7a/src/utf8.o" "bin/release/android/armeabi-v7a/src/utp_socket_manager.o" "bin/release/android/armeabi-v7a/src/utp_stream.o" "bin/release/android/armeabi-v7a/src/lsd.o" "bin/release/android/armeabi-v7a/src/enum_net.o" "bin/release/android/armeabi-v7a/src/parse_url.o" "bin/release/android/armeabi-v7a/src/xml_parse.o" "bin/release/android/armeabi-v7a/src/version.o" "bin/release/android/armeabi-v7a/src/peer_class.o" "bin/release/android/armeabi-v7a/src/peer_class_set.o" "bin/release/android/armeabi-v7a/src/session_stats.o" "bin/release/android/armeabi-v7a/src/performance_counters.o" "bin/release/android/armeabi-v7a/src/resolver.o" "bin/release/android/armeabi-v7a/src/session_settings.o" "bin/release/android/armeabi-v7a/src/proxy_settings.o" "bin/release/android/armeabi-v7a/src/ffs.o" "bin/release/android/armeabi-v7a/src/peer_info.o" "bin/release/android/armeabi-v7a/src/stack_allocator.o" "bin/release/android/armeabi-v7a/src/generate_peer_id.o" "bin/release/android/armeabi-v7a/src/generate_port.o" "bin/release/android/armeabi-v7a/src/ssl.o" "bin/release/android/armeabi-v7a/src/account_manager.o" "bin/release/android/armeabi-v7a/src/crypto.o" "bin/release/android/armeabi-v7a/src/common/entry_type.o" "bin/release/android/armeabi-v7a/src/communication/online_signal.o" "bin/release/android/armeabi-v7a/src/communication/message.o" "bin/release/android/armeabi-v7a/src/communication/message_db_impl.o" "bin/release/android/armeabi-v7a/src/communication/message_wrapper.o" "bin/release/android/armeabi-v7a/src/communication/communication.o" "bin/release/android/armeabi-v7a/src/communication/message_hash_list.o" "bin/release/android/armeabi-v7a/src/communication/immutable_data_info.o" "bin/release/android/armeabi-v7a/src/blockchain/account.o" "bin/release/android/armeabi-v7a/src/blockchain/account_block_pointer.o" "bin/release/android/armeabi-v7a/src/blockchain/block.o" "bin/release/android/armeabi-v7a/src/blockchain/blockchain.o" "bin/release/android/armeabi-v7a/src/blockchain/blockchain_signal.o" "bin/release/android/armeabi-v7a/src/blockchain/consensus.o" "bin/release/android/armeabi-v7a/src/blockchain/pool_hash_set.o" "bin/release/android/armeabi-v7a/src/blockchain/state_hash_array.o" "bin/release/android/armeabi-v7a/src/blockchain/index_key_info.o" "bin/release/android/armeabi-v7a/src/blockchain/peer_info.o" "bin/release/android/armeabi-v7a/src/blockchain/repository.o" "bin/release/android/armeabi-v7a/src/blockchain/repository_impl.o" "bin/release/android/armeabi-v7a/src/blockchain/repository_track.o" "bin/release/android/armeabi-v7a/src/blockchain/state_array.o" "bin/release/android/armeabi-v7a/src/blockchain/state_linker.o" "bin/release/android/armeabi-v7a/src/blockchain/transaction.o" "bin/release/android/armeabi-v7a/src/blockchain/transaction_wrapper.o" "bin/release/android/armeabi-v7a/src/blockchain/tx_pool.o" "bin/release/android/armeabi-v7a/src/blockchain/vote.o" "bin/release/android/armeabi-v7a/src/ed25519/add_scalar.o" "bin/release/android/armeabi-v7a/src/ed25519/fe.o" "bin/release/android/armeabi-v7a/src/ed25519/ge.o" "bin/release/android/armeabi-v7a/src/ed25519/hasher512.o" "bin/release/android/armeabi-v7a/src/ed25519/key_exchange.o" "bin/release/android/armeabi-v7a/src/ed25519/keypair.o" "bin/release/android/armeabi-v7a/src/ed25519/sc.o" "bin/release/android/armeabi-v7a/src/ed25519/sha512.o" "bin/release/android/armeabi-v7a/src/ed25519/sign.o" "bin/release/android/armeabi-v7a/src/ed25519/verify.o" "bin/release/android/armeabi-v7a/src/kademlia/bs_nodes_db_sqlite.o" "bin/release/android/armeabi-v7a/src/kademlia/bs_nodes_learner.o" "bin/release/android/armeabi-v7a/src/kademlia/bs_nodes_manager.o" "bin/release/android/armeabi-v7a/src/kademlia/dht_settings.o" "bin/release/android/armeabi-v7a/src/kademlia/dht_state.o" "bin/release/android/armeabi-v7a/src/kademlia/dht_storage.o" "bin/release/android/armeabi-v7a/src/kademlia/dht_tracker.o" "bin/release/android/armeabi-v7a/src/kademlia/dos_blocker.o" "bin/release/android/armeabi-v7a/src/kademlia/ed25519.o" "bin/release/android/armeabi-v7a/src/kademlia/find_data.o" "bin/release/android/armeabi-v7a/src/kademlia/get_item.o" "bin/release/android/armeabi-v7a/src/kademlia/get_peers.o" "bin/release/android/armeabi-v7a/src/kademlia/incoming_table.o" "bin/release/android/armeabi-v7a/src/kademlia/item.o" "bin/release/android/armeabi-v7a/src/kademlia/items_db_sqlite.o" "bin/release/android/armeabi-v7a/src/kademlia/keep.o" "bin/release/android/armeabi-v7a/src/kademlia/msg.o" "bin/release/android/armeabi-v7a/src/kademlia/node.o" "bin/release/android/armeabi-v7a/src/kademlia/node_entry.o" "bin/release/android/armeabi-v7a/src/kademlia/node_id.o" "bin/release/android/armeabi-v7a/src/kademlia/put_data.o" "bin/release/android/armeabi-v7a/src/kademlia/refresh.o" "bin/release/android/armeabi-v7a/src/kademlia/relay.o" "bin/release/android/armeabi-v7a/src/kademlia/routing_table.o" "bin/release/android/armeabi-v7a/src/kademlia/rpc_manager.o" "bin/release/android/armeabi-v7a/src/kademlia/traversal_algorithm.o" "bin/release/android/armeabi-v7a/src/pe_crypto.o"

"${ANDROID_TOOLCHAIN}/bin/llvm-ranlib" "bin/release/android/arm64-v8a/libTAU.a";



