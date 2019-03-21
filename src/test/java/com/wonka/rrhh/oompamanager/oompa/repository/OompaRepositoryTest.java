package com.wonka.rrhh.oompamanager.oompa.repository;

import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static com.wonka.rrhh.oompamanager.config.constants.OompaExample.ANY_ID;
import static com.wonka.rrhh.oompamanager.config.constants.OompaExample.getExampleOompa;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OompaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OompaRepository sut;

    @Test
    public void oompaShouldBeSavedAndReturnable() {
        //given
        final Oompa oompa = getExampleOompa();

        //when
        final Oompa outcome = sut.save(oompa);
        entityManager.flush();

        //then
        assertThat(sut.findById(outcome.getId()).isPresent()).isTrue();
        assertThat(oompa).isEqualTo(sut.findById(outcome.getId()).get());
    }

    @Test
    public void oompaShouldBeUpdatedAndReturnable() {
        //given
        final Oompa oompa = getExampleOompa();
        entityManager.persist(oompa);
        entityManager.flush();

        final Oompa oompaUpdated = getExampleOompa(1);

        //when
        final Oompa outcome = sut.save(oompaUpdated);
        entityManager.flush();

        //then
        assertThat(sut.findById(outcome.getId()).isPresent()).isTrue();
        assertThat(oompaUpdated).isEqualTo(sut.findById(outcome.getId()).get());
    }

    @Test(expected = ConstraintViolationException.class)
    public void oompaShouldNotSavedWhenBreakingConstrains() {
        //given
        final Oompa oompa = new Oompa("", 0, "");

        //when
        sut.save(oompa);
        entityManager.flush();
    }

    @Test
    public void oompaShouldExist() {
        //given
        final Oompa oompa = getExampleOompa();
        final Long index = (Long) entityManager.persistAndGetId(oompa);
        entityManager.flush();

        //when
        final boolean outcome = sut.existsById(index);

        //then
        assertThat(outcome).isTrue();
    }

    @Test
    public void oompaShouldNotExist() {
        //when
        final boolean outcome = sut.existsById(ANY_ID);

        //then
        assertThat(outcome).isFalse();
    }

    @Test
    public void findAllShouldReturnOompaList() {
        //given
        final List<Oompa> oompaList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final Oompa oompa = getExampleOompa(i);
            entityManager.persist(oompa);
            oompaList.add(oompa);
        }
        entityManager.flush();

        //when
        final Iterable<Oompa> outcome = sut.findAll();

        //then
        assertThat(outcome).isEqualTo(oompaList);
    }

    @Test
    public void findAllByIdShouldReturnOompaList() {
        //given
        final List<Oompa> oompaList = new ArrayList<>();
        final List<Long> indexArray = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final Oompa oompa = getExampleOompa(i);
            oompaList.add(oompa);
            final Long index = (Long) entityManager.persistAndGetId(oompa);
            indexArray.add(index);
        }
        entityManager.flush();

        //when
        final Iterable<Oompa> outcome = sut.findAllById(indexArray);

        //then
        assertThat(outcome).isEqualTo(oompaList);
    }
}