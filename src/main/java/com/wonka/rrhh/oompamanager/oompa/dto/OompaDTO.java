package com.wonka.rrhh.oompamanager.oompa.dto;

import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class OompaDTO {

    @ApiModelProperty(value = "Oompa Lumpa's ID", position = 1)
    private long id;

    @NotNull
    @ApiModelProperty(value = "Oompa Lumpa's name", position = 2, required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "Oompa Lumpa's age", position = 3, required = true)
    private int age;

    @NotNull
    @ApiModelProperty(value = "Oompa Lumpa's job position", position = 4, required = true)
    private String job;

    public OompaDTO(@NotNull String name, @NotNull int age, @NotNull String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public OompaDTO(Oompa oompa) {
        this(oompa.getName(), oompa.getAge(), oompa.getJob());
        this.id = oompa.getId();
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

    @Override
    public String toString() {
        return "OompaDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OompaDTO)) return false;
        OompaDTO oompaDTO = (OompaDTO) o;
        return getAge() == oompaDTO.getAge() &&
                getName().equals(oompaDTO.getName()) &&
                getJob().equals(oompaDTO.getJob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getJob());
    }
}
