package aor.paj.project3.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="task")
// @NamedQuery(name="Activity.findActivityById", query="SELECT a FROM ActivityEntity a WHERE a.id = :id")
// @NamedQuery(name="Activity.findActivityByUser", query="SELECT a FROM ActivityEntity a WHERE a.owner = :owner")
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
    private Timestamp creationDate;

    @CreationTimestamp
    @Column(name="end_date", nullable = false, unique = false, updatable = true)
    private Timestamp creationDate;

    @Column (name="state", nullable = true, unique = false, length = 65535, columnDefinition = "TEXT")
    private String description;


    @Column (name="category", nullable = true, unique = false, length = 65535, columnDefinition = "TEXT")
    private String description;

    @Column (name="priority", nullable = true, unique = false, length = 65535, columnDefinition = "TEXT")
    private String description;

    @Column (name="creator", nullable = true, unique = false, length = 65535, columnDefinition = "TEXT")
    private String description;



    //Owning Side User - Activity
    //@ManyToOne
    //private UserEntity owner;


    public TaskEntity() {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
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


}

