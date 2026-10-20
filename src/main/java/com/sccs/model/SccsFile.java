package com.sccs.model;

import com.sccs.config.sccsRepoConfig;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SccsFile {

    private final sccsHeader header;
    private final List<DeltaRecord> deltaTable;
    private final List<String> rawBodyLines;

    public SccsFile(sccsHeader header, List<DeltaRecord> deltaTable, List<String> rawBodyLines) {
        this.header = Objects.requireNonNull(header, "header must not be null");
        this.deltaTable = deltaTable != null
                ? Collections.unmodifiableList(deltaTable)
                : Collections.emptyList();
        this.rawBodyLines = rawBodyLines != null
                ? Collections.unmodifiableList(rawBodyLines)
                : Collections.emptyList();
    }

    public boolean validate() {
        sccsRepoConfig config = sccsRepoConfig.getInstance();
        if (!header.validateChecksum()) {
            if (!config.isLenientMode()) {
                return false;
            }
        }
        return true;
    }

    public sccsHeader getHeader() {
        return header;
    }

    public List<DeltaRecord> getDeltaTable() {
        return deltaTable;
    }

    public List<String> getRawBodyLines() {
        return rawBodyLines;
    }
}
