package aor.paj.project3.dto;

import aor.paj.project3.enums.TaskPriority;
import aor.paj.project3.enums.TaskState;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;


@XmlRootElement
public class TaskDto {
    @XmlElement
    private int id;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private TaskState state;
    @XmlElement
    private TaskPriority priority;
    @XmlElement
    private LocalDate startDate;
    @XmlElement
    private LocalDate limitDate;


    public TaskDto() {
    }

    public TaskDto(int id, String title, String description, TaskState state, TaskPriority priority, LocalDate startDate, LocalDate limitDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
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

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
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