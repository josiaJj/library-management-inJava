package school.hei;

import java.util.Date;

public class Book {
    public Book() {}
    public Book(Long id, String bookName, int pageNumbers, Topic topic, Date releaseDate, Author author) {
        this.id = id;
        this.bookName = bookName;
        this.pageNumbers = pageNumbers;
        this.topic = topic;
        this.releaseDate = releaseDate;
        this.author = author;
    }

    public Book(Long id, String bookName, int pageNumbers, Topic topic, Date releaseDate) {
        this.id = id;
        this.bookName = bookName;
        this.pageNumbers = pageNumbers;
        this.topic = topic;
        this.releaseDate = releaseDate;
    }

    private Long id;
    private String bookName;
    private int pageNumbers;
    private Topic topic;
    private Date releaseDate;
    private Author author;

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getPageNumbers() {
        return pageNumbers;
    }

    public Topic getTopic() {
        return topic;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPageNumbers(int pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + bookName + '\'' +
                ", pageNumbers=" + pageNumbers +
                ", releaseDate=" + releaseDate +
                ", topic=" + topic +
                "} \n";
    }
}
