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
                    std::string str_key(b.begin(), b.end());
                    libTAU::dht::public_key(str_key.c_str());
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
		        explicit secret_key(const std::array<std::int8_t, 64> & b) {
                    std::string str_key(b.begin(), b.end());
                    libTAU::dht::secret_key(str_key.c_str());
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
                    std::string str_sign(s.begin(), s.end());
                    libTAU::dht::signature(str_sign.c_str());
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
