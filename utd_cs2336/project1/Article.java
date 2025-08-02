public class Article {
    private int id;
    private String title;
    private String author;

    public Article(int id, String title, String author) {
        this.title = title;
        this.id = id;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("\t %d | %s | %s--->", id, title, author);
    }
}