package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Table (name = "areas")
@NamedQueries ({
    @NamedQuery(
            name = "getAllAreas",
            query = "SELECT a FROM Area AS a ORDER BY a.id"
            ),
    @NamedQuery(
            name = "getAreasCount",
            query = "SELECT COUNT(a) FROM Area AS a")
})
@Entity
public class Area {



    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

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





}