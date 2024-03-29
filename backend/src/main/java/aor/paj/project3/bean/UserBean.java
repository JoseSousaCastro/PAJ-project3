package aor.paj.project3.bean;

import aor.paj.project3.dao.UserDao;
import aor.paj.project3.dto.LoginDto;
import aor.paj.project3.entity.UserEntity;
import aor.paj.project3.dto.UserDto;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Base64;



@Stateless
public class UserBean implements Serializable {

    @EJB
    UserDao userDao;

    public String login(LoginDto user) {
        UserEntity userEntity = userDao.findUserByEmail(user.getEmail());
        if (userEntity != null) {
            if (userEntity.getPassword().equals(user.getPassword())) {
                String token = generateNewToken();
                userEntity.setToken(token);
                return token;
            }
        }
        return null;
    }


    public boolean register(UserDto user) {
        UserEntity u = userDao.findUserByEmail(user.getEmail());
        if (u == null) {
            userDao.persist(convertUserDtotoUserEntity(user));
            return true;
        } else
            return false;
    }

    private UserEntity convertUserDtotoUserEntity(UserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setPhotoURL(user.getPhotoURL());
        userEntity.setToken(user.getToken());
        userEntity.setDeleted(user.getDeleted());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    private UserDto convertUserEntitytoUserDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setPhotoURL(user.getPhotoURL());
        userDto.setToken(user.getToken());
        userDto.setDeleted(user.getDeleted());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private String generateNewToken() {
        SecureRandom secureRandom = new SecureRandom(); //threadsafe
        Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public boolean logout(String token) {
        UserEntity u = userDao.findUserByToken(token);
        if (u != null) {
            u.setToken(null);
            return true;
        }
        return false;
    }

    public boolean tokenExist(String token) {
        if (userDao.findUserByToken(token) != null)
            return true;
        return false;

    }
}




/*package aor.paj.project3.bean;

import aor.paj.project3.dto.TaskDto;
import aor.paj.project3.dto.UserDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

@ApplicationScoped
public class UserBean {
    private final String filename = "users.json";
    private ArrayList<UserDto> users;

    public UserBean() {
        File f = new File(filename);
        if (f.exists()) {
            try {
                FileReader filereader = new FileReader(f);
                users = JsonbBuilder.create().fromJson(filereader, new ArrayList<UserDto>() {
                }.getClass().getGenericSuperclass());
                System.out.println("Users: " + users);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            users = new ArrayList<>();
        }
    }

    public ArrayList<UserDto> getUsers() {
        return users;
    }

    public boolean addUser(UserDto user) {

        boolean status = false;
        if (users.add(user)) {
            status = true;
        }
        writeIntoJsonFile();
        return status;
    }

    public UserDto getUser(String username) {
        for (UserDto user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public boolean updateUser(UserDto user) {
        boolean status = false;

        for (UserDto a : users) {
            if (a.getUsername().equals(user.getUsername())) {
                a.setPassword(user.getPassword());
                a.setEmail(user.getEmail());
                a.setFirstName(user.getFirstName());
                a.setLastName(user.getLastName());
                a.setPhone(user.getPhone());
                a.setPhotoURL(user.getPhotoURL());
                writeIntoJsonFile();
                status = true;
            }
        }
        return status;
    }

    public boolean isAuthenticated(String username, String password) {
        boolean status = false;

        for (UserDto user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                status = true;
            }
        }
        return status;
    }


    // Método a construir
    public boolean authenticatesToken(String token) {
        boolean status = false;
        return status;
    }

    public boolean isUsernameAvailable(String username) {
        boolean status = true;

        for (UserDto user : users) {
            if (user.getUsername().equals(username)) {
                status = false;
            }
        }
        return status;
    }

    private boolean isEmailFormatValid(String email) {
        // Use a regular expression to perform email format validation
        // This regex is a basic example and may need to be adjusted
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public boolean isEmailValid(String email, String username) {
        // Check if the email format is valid
        if (!isEmailFormatValid(email)) {
            return false;
        }

        // Check if the email is already in use by a different user
        for (UserDto user : users) {
            if (user.getEmail().equals(email) && !user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }



    public boolean isAnyFieldEmpty(UserDto user) {
        boolean status = false;

        if (user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getEmail().isEmpty() ||
                user.getFirstName().isEmpty() ||
                user.getLastName().isEmpty() ||
                user.getPhone().isEmpty() ||
                user.getPhotoURL().isEmpty()) {
            status = true;
        }
        return status;
    }

    public boolean isPhoneNumberValid(String phone) {
        boolean status = true;
        int i = 0;

        while (status && i < phone.length() - 1) {
            if (phone.length() == 9) {
                for (; i < phone.length(); i++) {
                    if (!Character.isDigit(phone.charAt(i))) {
                        status = false;
                    }
                }
            } else {
                status = false;
            }
        }
        return status;
    }

    public boolean isImageUrlValid(String url) {
        boolean status = true;

        if (url == null) {
            status = false;
        }

        try {
            BufferedImage img = ImageIO.read(new URL(url));
            if (img == null) {
                status = false;
            }
        } catch (IOException e) {
            status = false;
        }

        return status;
    }

    public ArrayList<TaskDto> getUserAndHisTasks(String username) {
        ArrayList<TaskDto> userTasks = null;

        for (UserDto user : users) {
            if (user.getUsername().equals(username)) {
                userTasks = user.getUserTasks();
            }
        }
        return userTasks;
    }
/*
    public boolean addTaskToUser(String username, TaskDto temporaryTask) {
        TaskBean taskBean = new TaskBean();
        boolean done = taskBean.newTask(temporaryTask);
        if (done) {
            getUserAndHisTasks(username).add(temporaryTask);
            writeIntoJsonFile();
        }
        return done;
    }
/*
    public boolean updateTask(String username, TaskDto task) {
        TaskBean taskBean = new TaskBean();
        boolean updated = false;

        if (taskBean.editTask(task, getUserAndHisTasks(username))) {
            writeIntoJsonFile();
            updated = true;
        }
        return updated;
    }
*/

/*
    public boolean updateTaskStatus(String username, String taskId, int newStatus) {

        if (newStatus != 100 && newStatus != 200 && newStatus != 300) {
            return false;
        }

        for (UserDto user : users) {
            if (user.getUsername().equals(username)) {
                ArrayList<TaskDto> userTasks = user.getUserTasks();
                for (TaskDto task : userTasks) {
                    if (task.getId().equals(taskId)) {
                        task.setStateId(newStatus);
                        writeIntoJsonFile();
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public void writeIntoJsonFile() {
        Jsonb jsonb = JsonbBuilder.create(new
                JsonbConfig().withFormatting(true));
        try {
            jsonb.toJson(users, new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}*/