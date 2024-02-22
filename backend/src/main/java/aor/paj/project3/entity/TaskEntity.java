package aor.paj.project3.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name="task")
@NamedQuery(name="Task.findTaskById", query="SELECT a FROM TaskEntity a WHERE a.id = :id")
@NamedQuery(name="Task.findTasksByUser", query="SELECT a FROM TaskEntity a WHERE a.owner = :owner")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column (name="title", nullable = false, unique = false, length = 25)
    private String title;

    @Column (name="description", nullable = false, unique = false, length = 65535, columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name="start_date", nullable = false, unique = false, updatable = false)
    private Timestamp startDate;

    @Column(name="end_date", nullable = true, unique = false, updatable = true)
    private LocalDate endDate;

    @Column (name="state", nullable = false, unique = false, updatable = true)
    private int state;

    @Column (name="priority", nullable = false, unique = false, updatable = true)
    private int priority;

    @Column(name="deleted", nullable = false, unique = false, updatable = true)
    private Boolean deleted;

    //Owning Side User - Task
    @ManyToOne
    private UserEntity creator;

    //Owning Side Category - Task
    @ManyToOne
    private CategoryEntity category;



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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}

