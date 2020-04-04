package entity;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 10:27 PM 2019/7/18
 * @Modified By:
 */
public class ManageSysPdfEntity {
    private int id;
    private String title;
    private String content;
    private String picture;
    private String startPageNumber;
    private int endPageNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStartPageNumber() {
        return startPageNumber;
    }

    public void setStartPageNumber(String startPageNumber) {
        this.startPageNumber = startPageNumber;
    }

    public int getEndPageNumber() {
        return endPageNumber;
    }

    public void setEndPageNumber(int endPageNumber) {
        this.endPageNumber = endPageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManageSysPdfEntity that = (ManageSysPdfEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (startPageNumber != null ? !startPageNumber.equals(that.startPageNumber) : that.startPageNumber != null)
            return false;
        if (endPageNumber != that.endPageNumber)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (startPageNumber != null ? startPageNumber.hashCode() : 0);
        return result;
    }
}
