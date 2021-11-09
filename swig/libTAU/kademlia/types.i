namespace libTAU {
    namespace dht {
            
        struct public_key
	    {
		    public_key();
		    explicit public_key(char const* b);

            bool operator==(public_key const& rhs) const;

            bool operator<(const public_key &rhs) const;

            bool operator>(const public_key &rhs) const;

            bool operator<=(const public_key &rhs) const;

            bool operator>=(const public_key &rhs) const;

            %extend {
                public_key(const std::array<std::int8_t, 32> & b) {
                    return new libTAU::dht::public_key((char *)b.data());
                }

                std::vector<std::int8_t> to_bytes() {
                    std::vector<std::int8_t> key_bytes; 
                    std::copy($self->bytes.begin(), $self->bytes.end(), std::inserter(key_bytes, key_bytes.begin()));
                    return key_bytes;
                }
            }

        };

        struct secret_key
	    {
		    secret_key();
		    explicit secret_key(char const* b);
		    bool operator==(secret_key const& rhs) const;
            %extend {
		        secret_key(const std::array<std::int8_t, 64> & b) {
                    return new libTAU::dht::secret_key((char *)b.data());
                }
            }
        };

        struct signature
	    {
		    signature();
		    explicit signature(char const* b);
		    bool operator==(signature const& rhs) const;
            %extend {
		        explicit signature(const std::array<std::int8_t, 64> & s) {
                    return new libTAU::dht::signature((char *)s.data());
                }
            }
        };

        struct timestamp
	    {
		    timestamp() : value(0) {}
            explicit timestamp(std::int64_t v) : value(v) {}
            std::int64_t value;
        };

    }
}
