package com.wonka.rrhh.oompamanager.oompa.dto;

import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class OompaDetailDTO {

    @NotNull
    @ApiModelProperty(value = "Oompa Loompa's name", position = 1, required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "Oompa Loompa's age", position = 2, required = true)
    private int age;

    @NotNull
    @ApiModelProperty(value = "Oompa Loompa's job position", position = 3, required = true)
    private String job;

    @ApiModelProperty(value = "Oompa Loompa's height", position = 4)
    private float height;

    @ApiModelProperty(value = "Oompa Loompa's weight", position = 5)
    private float weight;

    @ApiModelProperty(value = "Oompa Loompa's description", position = 6)
    private String description;

    public OompaDetailDTO(@NotNull String name, @NotNull int age, @NotNull String job, float height, float weight,
                          String description) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }

    public OompaDetailDTO(Oompa oompa) {
        this(oompa.getName(), oompa.getAge(), oompa.getJob(), oompa.getHeight(), oompa.getWeight(),
                oompa.getDescription());
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
        return "OompaDetailDTO{" +
                "name='" + name + '\'' +
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
        if (!(o instanceof OompaDetailDTO)) return false;
        OompaDetailDTO that = (OompaDetailDTO) o;
        return getAge() == that.getAge() &&
                Float.compare(that.getHeight(), getHeight()) == 0 &&
                Float.compare(that.getWeight(), getWeight()) == 0 &&
                getName().equals(that.getName()) &&
                getJob().equals(that.getJob()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getJob(), getHeight(), getWeight(), getDescription());
    }
}
