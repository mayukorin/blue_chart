package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table (name = "Problems")
@NamedQueries({
    @NamedQuery(
            name = "getProblemsinArea",
            query = "select p from Problem as p where p.area = :area"),
    @NamedQuery(
            name = "getAllProblems",
            query = "select p from Problem as p")
})
@Entity
public class Problem {

    @Id
    @Column(name ="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "star")
    private Integer star;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name="title")
    private String title;

    @Column(name="page")
    private Integer page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }




}
