package models;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table (name = "Solves2")
@NamedQueries({
    @NamedQuery(
            name="areasolve2",
            query = "select s from Solve2 as s where s.problem.area=:area and s.person = :person order by s.date"),
    @NamedQuery(
            name="problemcount",
            query = "select count(s) from Solve2 as s where s.problem = :problem and s.person = :person"),

    @NamedQuery(
            name="solveproblem_desc",
            query =  "select s from Solve2 as s where s.problem = :problem and s.person = :person order by s.date desc " )


})

@Entity
public class Solve2 {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person")
    Person person;

    @ManyToOne
    @JoinColumn(name = "problem")
    Problem problem;

    @Column(name="targettime",nullable = false)
    LocalTime targettime;

    @Column(name="day",nullable=false)
    private String day;

    @Column(name="date",nullable=false)
    private Date date;

    @Column(name="solvetime")
    private LocalTime solvetime;

    @Column(name = "rate")
    private Double rate;

    @Lob
    @Column(name = "content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public LocalTime getTargettime() {
        return targettime;
    }

    public void setTargettime(LocalTime targettime) {
        this.targettime = targettime;
    }



    public LocalTime getSolvetime() {
        return solvetime;
    }

    public void setSolvetime(LocalTime solvetime) {
        this.solvetime = solvetime;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }







}

