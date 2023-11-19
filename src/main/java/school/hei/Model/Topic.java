package school.hei.Model;

public class Topic {
    public Topic(String topicName) {
        this.topicName = topicName;
    }
    private Integer id;
    private String topicName;

    public Integer getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return topicName;
    }
}
