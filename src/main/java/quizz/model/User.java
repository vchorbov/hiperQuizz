package quizz.model;

import java.util.List;

public class User extends AbstractEntity<Long, User>{

    private String username;
    private String email;
    private String password;
    private Gender gender;
    private Role role = Role.PLAYER;
    private String picture;
    private String description;
    private String metadata;
    private boolean status = true;
    private List<Quiz> quizzes;



    public User() {
    }

    public User(Long id) {
        super(id);
    }

    public User( String username, String email, String password, Gender gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;

    }

    public User(Long id, String username, String email, String password, Gender gender) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;

    }

    public User(Long id, String username, String email, String password, Gender gender, Role role) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public User( String username, String email, String password, Gender gender, List<Quiz> quizzes) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.quizzes = quizzes;
    }

    // All the required fields with the default role
    public User(Long id, String username, String email, String password, Gender gender, List<Quiz> quizzes) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.quizzes = quizzes;
    }

    // All the required fields with explicit role
    public User(Long id, String username, String email, String password, Gender gender, Role role, List<Quiz> quizzes) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.quizzes = quizzes;
    }

    // All fields
    public User(Long id, String username, String email, String password,
                Gender gender, Role role, String picture, String description,
                String metadata, boolean status, List<Quiz> quizzes) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.picture = picture;
        this.description = description;
        this.metadata = metadata;
        this.status = status;
        this.quizzes = quizzes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // private for security reasons
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public boolean getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        //sb.append("id='").append().append('\'');
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", role=").append(role);
        sb.append(", description='").append(description).append('\'');
        sb.append(", metadata='").append(metadata).append('\'');
        sb.append(", status=").append(status);
        sb.append(", quizzes=").append(quizzes);
        sb.append('}');
        return sb.toString();
    }
}
