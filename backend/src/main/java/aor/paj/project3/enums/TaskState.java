package aor.paj.project3.enums;

import jakarta.xml.bind.annotation.XmlElement;

public enum TaskState {
    TODO(100),
    DOING(200),
    DONE(300);

    private final int value;

    TaskState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

