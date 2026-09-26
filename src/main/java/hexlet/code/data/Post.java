package hexlet.code.data;

import java.time.LocalDate;

public class Post {
    private Long id;
    private Long userId;
    private LocalDate postDate;
    private String title;
    private String content;

    public Post(Long userId, LocalDate postDate, String title, String content) {
        this.userId = userId;
        this.postDate = postDate;
        this.title = title;
        this.content = content;
    }

    public Post(LocalDate postDate, String title, String content) {
        this.postDate = postDate;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
