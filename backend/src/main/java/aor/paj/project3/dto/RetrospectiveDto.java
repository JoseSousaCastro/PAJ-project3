package aor.paj.project3.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement
public class RetrospectiveDto {
    @XmlElement
    private String id;
    @XmlElement
    private String title;
    @XmlElement
    private LocalDate date;
    private ArrayList<UserDto> retrospectiveUsers = new ArrayList<>();
    @XmlElement
    private ArrayList<CommentDto> retrospectiveComments = new ArrayList<>();


    public RetrospectiveDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String generateId() {
        this.id = String.valueOf(System.currentTimeMillis());
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.now();
    }

    public void addUser(UserDto user) {
        retrospectiveUsers.add(user);
    }

    public ArrayList<UserDto> getRetrospectiveUsers() {
        return retrospectiveUsers;
    }

    public void addComment(CommentDto comment) {
        retrospectiveComments.add(comment);
    }

    public ArrayList<CommentDto> getRetrospectiveComments() {
        return retrospectiveComments;
    }
}
