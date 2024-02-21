package aor.paj.project3.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="task")
@NamedQuery(name="Task.findTaskById", query="SELECT a FROM TaskEntity a WHERE a.id = :id")
@NamedQuery(name="Task.findTaskByUser", query="SELECT a FROM TaskEntity a WHERE a.owner = :owner")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column (name="title", nullable = false, unique = true)
    private String title;

    @Column (name="description", nullable = true, unique = false, length = 65535, columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name="start_date", nullable = false, unique = false, updatable = false)
    private Timestamp startDate;

    @CreationTimestamp
    @Column(name="end_date", nullable = true, unique = false, updatable = true)
    private Timestamp endDate;

    @Column (name="state", nullable = false, unique = true, updatable = true)
    private int state;

    @Column (name="category", nullable = false, unique = true, updatable = false)
    private String category;

    @Column (name="priority", nullable = false, unique = true, updatable = false)
    private int priority;

    @Column (name="creator", nullable = false, unique = true, updatable = false)
    private String creatorName;


    //Owning Side User - Task
    @ManyToOne
    private UserEntity owner;


    public TaskEntity() {
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}

