package model;

public class Category {
    private int categoryId;
    private String categoryName;
    private String desc;
    private int status;

    public Category() {
    }

    public Category(int categoryId, int status) {
        this.categoryId = categoryId;
        this.status = status;
    }

    public Category(String categoryName, String desc) {
        this.categoryName = categoryName;
        this.desc = desc;
    }

    public Category(String categoryName, String desc, int status) {
        this.categoryName = categoryName;
        this.desc = desc;
        this.status = status;
    }

    public Category(int categoryId, String categoryName, String desc) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.desc = desc;
    }

    public Category(int categoryId, String categoryName, String desc, int status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.desc = desc;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
