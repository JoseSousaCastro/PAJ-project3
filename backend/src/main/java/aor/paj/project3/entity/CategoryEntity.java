package aor.paj.project3.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="task_category")
@NamedQuery(name="Category.findCategoryByName", query="SELECT c FROM CategoryEntity c WHERE c.categoryName = :name")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column (name="name", nullable = false, unique = true, updatable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<TaskEntity> tasks;

    public CategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
