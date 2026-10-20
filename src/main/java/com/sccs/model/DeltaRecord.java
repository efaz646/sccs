package com.sccs.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeltaRecord {

    private final Sid sid;
    private final LocalDateTime date;
    private final String author;
    private final String comment;
    private final int seq;
    private final int prevSeq;

    public DeltaRecord(Sid sid, LocalDateTime date, String author, String comment) {
        this(sid, date, author, comment, 1, 0);
    }

    public DeltaRecord(Sid sid, LocalDateTime date, String author, String comment, int seq, int prevSeq) {
        this.sid = Objects.requireNonNull(sid, "sid must not be null");
        this.date = Objects.requireNonNull(date, "date must not be null");
        this.author = Objects.requireNonNull(author, "author must not be null");
        this.comment = Objects.requireNonNull(comment, "comment must not be null");
        this.seq = seq;
        this.prevSeq = prevSeq;
    }

    public Sid getSid() { return sid; }
    public LocalDateTime getDate() { return date; }
    public String getAuthor() { return author; }
    public String getComment() { return comment; }
    public int getSeq() { return seq; }
    public int getPrevSeq() { return prevSeq; }

    public String getFormattedDate() {
        int y = date.getYear();
        int m = date.getMonthValue();
        int d = date.getDayOfMonth();
        int h = date.getHour();
        int mi = date.getMinute();
        int s = date.getSecond();
        return String.format("%02d/%02d/%02d %02d:%02d:%02d",
                y % 100, m, d, h, mi, s);
    }

    public String getMetadata() {
        return String.format("%s %s %s %s", sid, getFormattedDate(), author, comment);
    }
}
