package models;

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
            query =  "select s from Solve2 as s where s.problem = :problem and s.person = :person order by s.date desc " ),
    @NamedQuery(
            name="solveproblem",
            query =  "select s from Solve2 as s where s.problem = :problem and s.person = :person order by s.date " ),
    @NamedQuery(
            name="solves",
            query = "select s from Solve2 as s where s.person = :person"),



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

    @Column(name="target_minute")
    private Integer target_minute;

    @Column(name="target_second")
    private Integer target_second;

    @Column(name="day",nullable=false)
    private String day;

    @Column(name="date",nullable=false)
    private Date date;

    @Column(name="solve_minute")
    private Integer solve_minute;

    @Column(name="solve_second")
    private Integer solve_second;

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

    public Integer getTarget_minute() {
        return target_minute;
    }

    public void setTarget_minute(Integer target_minute) {
        this.target_minute = target_minute;
    }

    public Integer getTarget_second() {
        return target_second;
    }

    public void setTarget_second(Integer target_second) {
        this.target_second = target_second;
    }

    public Integer getSolve_minute() {
        return solve_minute;
    }

    public void setSolve_minute(Integer solve_minute) {
        this.solve_minute = solve_minute;
    }

    public Integer getSolve_second() {
        return solve_second;
    }

    public void setSolve_second(Integer solve_second) {
        this.solve_second = solve_second;
    }







}

