package aor.paj.project3.enums;

public enum TaskPriority {
    LOW_PRIORITY(100),
    MEDIUM_PRIORITY(200),
    HIGH_PRIORITY(300);

    private final int value;

    TaskPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}