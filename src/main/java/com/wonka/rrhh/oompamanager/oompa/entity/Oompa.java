package com.wonka.rrhh.oompamanager.oompa.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "oompas")
public class Oompa {

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
    private int age;

    @Column(name = "job", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "Oompa Loompa's job position", position = 4, required = true)
    private String job;

    @Column(name = "height")
    @ApiModelProperty(value = "Oompa Loompa's height", position = 5)
    private float height;

    @Column(name = "weight")
    @ApiModelProperty(value = "Oompa Loompa's weight", position = 6)
    private float weight;

    @Column(name = "description")
    @ApiModelProperty(value = "Oompa Loompa's description", position = 7)
    private String description;

    public Oompa(@NotBlank String name, @Min(16) @NotNull int age, @NotBlank String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public Oompa(@NotBlank String name, @Min(16) @NotNull int age, @NotBlank String job, float height, float weight,
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
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
                getAge() == oompa.getAge() &&
                Float.compare(oompa.getHeight(), getHeight()) == 0 &&
                Float.compare(oompa.getWeight(), getWeight()) == 0 &&
                getName().equals(oompa.getName()) &&
                getJob().equals(oompa.getJob()) &&
                getDescription().equals(oompa.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getJob(), getHeight(), getWeight(), getDescription());
    }
}
