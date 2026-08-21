package com.sccs.model;

import java.util.*;

public class Sid implements Comparable<Sid> {
    private int[] components;

    public Sid(int release, int level, int... branchSeq) {
        if(release < 1 || level < 1) {
            System.out.println("release and level must be >= 1");
        }

        if(branchSeq.length % 2 != 0) {
            System.out.println("branch and sequence must come in pairs"); //that is it should 1.2 or 1.2.3.4 or 1.2.3.4.5.6 and not 1.2.3
        }

        for(int value : branchSeq) {
            if(value < 1) {
                System.out.println("values must be >= 1");
            }
        }

        components = new int[2 + branchSeq.length];
        components[0] = release;
        components[1] = level;

        for(int i = 0; i < branchSeq.length; i++) {
            components[i + 2] = branchSeq[i];
        }
    }

    public int getRelease() { //getter
        return components[0];
    }

    public int getLevel() {
        return components[1];
    }

    public Integer getBranch() {
        if(components.length > 2) {
            return components[2]; //branch must be in index greater than 2
        }
        return null;
    }

    public Integer getSequence() {
        if(components.length > 3) {
            return components[3];
        }
        return null;
    }

    public SidType getType() {
        if(components.length == 2) {
            return SidType.TRUNK;
        }
        return SidType.BRANCH;
    }

    public static Sid parse(String sidStr) {
        Objects.requireNonNull(sidStr);

        String[] parts = sidStr.split("\\."); //regex
        if(parts.length < 2 || parts.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid SID: " + sidStr);
        }

        int[] values = new int[parts.length];
        for(int i = 0; i < parts.length; i++) {
            values[i] = Integer.parseInt(parts[i]);
        }

        int release = values[0];
        int level = values[1];

        int[] branchSeq = Arrays.copyOfRange(values, 2, values.length);

        return new Sid(release, level, branchSeq);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < components.length; i++) {
            if(i > 0) {
                result.append(".");
            }
            result.append(components[i]);
        }
        return result.toString();
    }

    @Override
    public int compareTo(Sid other) {
        int length = Math.min(components.length,other.components.length);

        for(int i = 0; i < length; i++) {
            if(components[i] != other.components[i]) {
                return Integer.compare(components[i], other.components[i]);
            }
        }

        return Integer.compare(components.length, other.components.length);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!(o instanceof Sid)) {
            return false;
        }

        Sid other = (Sid) o;

        return Arrays.equals(components,other.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}
