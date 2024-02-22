package aor.paj.project3.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;


@XmlRootElement
public class TaskDto {
    @XmlElement
    private int id;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private int stateId;
    @XmlElement
    private int priority;
    @XmlElement
    private LocalDate startDate;
    @XmlElement
    private LocalDate limitDate;
    @XmlElement
    public static final int TODO = 100;
    @XmlElement
    public static final int DOING = 200;
    @XmlElement
    public static final int DONE = 300;
    @XmlElement
    public static final int LOWPRIORITY = 100;
    @XmlElement
    public static final int MEDIUMPRIORITY = 200;
    @XmlElement
    public static final int HIGHPRIORITY = 300;

    public TaskDto() {
    }

    public TaskDto(int id, String title, String description, int stateId, int priority, LocalDate startDate, LocalDate limitDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stateId = TODO;
        this.priority = priority;
        this.startDate = startDate;
        this.limitDate = limitDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }
}