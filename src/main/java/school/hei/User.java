package school.hei;

public class User {
    String username;
    String password;
    Sex sex;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
