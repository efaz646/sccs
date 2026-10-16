package com.sccs.model;

import java.util.*;

public class sccsHeader {

    private final int checksum;
    private final Map<Character, String> flags;

    public sccsHeader(int checksum, Map<Character, String> flags) {
        this.checksum = checksum;
        this.flags = flags != null
                ? Collections.unmodifiableMap(flags)
                : Collections.emptyMap();
    }

    public int getChecksum() {
        return checksum;
    }

    public Map<Character, String> getFlags() {
        return flags;
    }

    public boolean isEncrypted() {
        return flags.containsKey('y');
    }

    public boolean validateChecksum() {
        return checksum > 0 && checksum <= 99999;
    }
}
