package school.hei.Model;

public class Author {
    public Author() {}
    private Long id;
    private String authorName;
    private Sex sex;

    public Author(String authorName, Sex sex) {
        this.authorName = authorName;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "id= " + id +
                ", authorName= " + authorName +
                ", Sex= " + sex +
                "} \n";
    }
}
