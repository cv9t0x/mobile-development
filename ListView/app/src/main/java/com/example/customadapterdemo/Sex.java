package com.example.customadapterdemo;

public enum Sex {
    MAN("MAN"),
    WOMAN("WOMAN"),
    UNKNOWN("UNKNOWN");

    public final String label;

    private Sex(String label) {
        this.label = label;
    }

    public static Sex valueOfLabel(String label) {
        for (Sex e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public static int getOrderNumber(Sex sex) {
        switch (sex) {
            case MAN: return 2;
            case WOMAN: return 1;
            case UNKNOWN: return 0;
        }
        return -1;
    }
}

