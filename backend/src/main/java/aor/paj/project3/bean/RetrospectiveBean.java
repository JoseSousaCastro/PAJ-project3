package aor.paj.project3.bean;

import aor.paj.project3.dto.RetrospectiveDto;
import aor.paj.project3.dto.CommentDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

@ApplicationScoped
public class RetrospectiveBean {
    final String filename = "retrospectives.json";
    private ArrayList<RetrospectiveDto> retrospectives;

    public RetrospectiveBean() {
        File f = new File(filename);
        if (f.exists()) {
            try {
                FileReader filereader = new FileReader(f);
                retrospectives = JsonbBuilder.create().fromJson(filereader, new ArrayList<RetrospectiveDto>() {
                }.getClass().getGenericSuperclass());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else
            retrospectives = new ArrayList<RetrospectiveDto>();
    }

    public boolean addRetrospective(RetrospectiveDto retrospective) {
        boolean added = true;
        if (retrospective.getTitle().isBlank() && retrospective.getDate() == null) {
            added = false;
        } else {
            retrospective.setId(retrospective.generateId());
            retrospective.setTitle(retrospective.getTitle());
            retrospective.setDate(retrospective.getDate());
            retrospectives.add(retrospective);
            writeIntoJsonFile();
        }
        return added;
    }


    public boolean addCommentToRetrospective(String id, CommentDto comment) {
        boolean added = true;
        if (comment.getDescription().isBlank() && comment.getUsername() == null && !validateCommentStatus(comment)) {
            added = false;
        } else {
            for (RetrospectiveDto a : retrospectives) {
                if (a.getId().equals(id)) {
                    comment.generateId();
                    a.addComment(comment);
                    writeIntoJsonFile();
                }
            }
        }
        return added;
    }

    public RetrospectiveDto getRetrospective(String id) {
        RetrospectiveDto retrospective = null;
        boolean found = false;
        while (!found) {
            for (RetrospectiveDto a : retrospectives) {
                if (a.getId().equals(id)) {
                    retrospective = a;
                    found = true;
                }
            }
        }
        return retrospective;
    }

    public ArrayList<RetrospectiveDto> getRetrospectives() {
        return retrospectives;
    }

    public ArrayList<CommentDto> getComments(String id) {
        ArrayList<CommentDto> comment = null;
        for (RetrospectiveDto a : retrospectives) {
            if (a.getId().equals(id)) {
                comment = a.getRetrospectiveComments();
            }
        }
        return comment;
    }

    public CommentDto getComment(String id, String commentId) {
        CommentDto comment = null;
        for (RetrospectiveDto a : retrospectives) {
            if (a.getId().equals(id)) {
                for (CommentDto c : a.getRetrospectiveComments()) {
                    if (c.getId().equals(commentId)) {
                        comment = c;
                    }
                }
            }
        }
        return comment;
    }

    public boolean validateCommentStatus(CommentDto comment) {
        boolean valid = true;
        if (comment.getCommentStatus() != CommentDto.STRENGTHS && comment.getCommentStatus() != CommentDto.CHALLENGES && comment.getCommentStatus() != CommentDto.IMPROVEMENTS) {
            valid = false;
        }
        return valid;
    }


    private void writeIntoJsonFile() {
        Jsonb jsonb = JsonbBuilder.create(new
                JsonbConfig().withFormatting(true));
        try {
            jsonb.toJson(retrospectives, new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}