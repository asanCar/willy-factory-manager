package com.wonka.rrhh.oompamanager.oompa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "oompas")
@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class Oompa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(value = "Oompa Loompa's ID", position = 1)
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "Oompa Loompa's name", position = 2, required = true)
    private String name;

    @Column(name = "age", nullable = false)
    @Min(16)
    @NotNull
    @ApiModelProperty(value = "Oompa Loompa's age", position = 3, required = true)
    private Integer age;

    @Column(name = "job", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "Oompa Loompa's job position", position = 4, required = true)
    private String job;

    @Column(name = "height")
    @ApiModelProperty(value = "Oompa Loompa's height", position = 5)
    private Float height;

    @Column(name = "weight")
    @ApiModelProperty(value = "Oompa Loompa's weight", position = 6)
    private Float weight;

    @Column(name = "description")
    @ApiModelProperty(value = "Oompa Loompa's description", position = 7)
    private String description;

    public Oompa() {

    }

    public Oompa(@NotBlank String name, @Min(16) @NotNull Integer age, @NotBlank String job) {

        this.name = name;
        this.age = age;
        this.job = job;
    }

    public Oompa(@NotBlank String name, @Min(16) @NotNull Integer age, @NotBlank String job, Float height, Float weight,
                 String description) {

        this.name = name;
        this.age = age;
        this.job = job;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public String getJob() {

        return job;
    }

    public void setJob(String job) {

        this.job = job;
    }

    public Float getHeight() {

        return height;
    }

    public void setHeight(Float height) {

        this.height = height;
    }

    public Float getWeight() {

        return weight;
    }

    public void setWeight(Float weight) {

        this.weight = weight;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Override
    public String toString() {

        return "Oompa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Oompa)) return false;
        Oompa oompa = (Oompa) o;
        return getId() == oompa.getId() &&
                Objects.equals(getName(), oompa.getName()) &&
                Objects.equals(getAge(), oompa.getAge()) &&
                Objects.equals(getJob(), oompa.getJob()) &&
                Objects.equals(getHeight(), oompa.getHeight()) &&
                Objects.equals(getWeight(), oompa.getWeight()) &&
                Objects.equals(getDescription(), oompa.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getAge(), getJob(), getHeight(), getWeight(), getDescription());
    }
}
