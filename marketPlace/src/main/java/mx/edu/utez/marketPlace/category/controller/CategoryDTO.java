package mx.edu.utez.marketPlace.category.controller;

public class CategoryDTO  {
    private long id;
    String description;

    public CategoryDTO() {
    }

    public String getDescription() {
        return description;
    }

    public CategoryDTO(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
