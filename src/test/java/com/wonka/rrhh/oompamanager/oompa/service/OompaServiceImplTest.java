package com.wonka.rrhh.oompamanager.oompa.service;

import com.wonka.rrhh.oompamanager.eceptions.ResourceNotFoundException;
import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import com.wonka.rrhh.oompamanager.oompa.repository.OompaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.wonka.rrhh.oompamanager.config.constants.OompaExample.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OompaServiceImplTest {

    @InjectMocks
    private OompaServiceImpl sut;

    @Mock
    private OompaRepository repository;

    @Before
    public void setup() {

    }

    @Test
    public void getAllOompaShouldReturnOompasList() {
        //given
        List<Oompa> iterable = getExampleOompaList();
        when(repository.findAll()).thenReturn(iterable);

        //when
        List<OompaDTO> outcome = sut.getAllOompa();

        //then
        assertThat(outcome).isEqualTo(getExampleOompaDTOs());
        verify(repository).findAll();
    }

    @Test
    public void getAllOompaShouldReturnEmptyList() {
        //given
        List<Oompa> iterable = new ArrayList<>();
        when(repository.findAll()).thenReturn(iterable);

        //when
        List<OompaDTO> outcome = sut.getAllOompa();

        //then
        assertThat(outcome.isEmpty()).isTrue();
        verify(repository).findAll();
    }

    @Test
    public void getOompaDetailShouldReturnOompa() {
        //given
        final Oompa oompa = getExampleOompa();
        when(repository.findById(anyLong())).thenReturn(Optional.of(oompa));

        //when
        final Oompa outcome = sut.getOompaDetail(ANY_ID);

        //then
        assertThat(outcome).isEqualTo(oompa);
        verify(repository).findById(ANY_ID);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getOompaDetailShouldThrowResourceNotFoundException() {
        //given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        sut.getOompaDetail(ANY_ID);
        verify(repository).findById(ANY_ID);
    }

    @Test
    public void oompaShouldBeAdded() {
        //given
        final Oompa oompa = getExampleOompa();
        when(repository.save(any(Oompa.class))).thenReturn(oompa);

        //when
        final Oompa outcome = sut.addOompa(oompa);

        //then
        assertThat(outcome).isEqualTo(oompa);
        verify(repository).save(any(Oompa.class));
    }

//    @Test
//    public void oompaShouldNotBeAdded() {
//        //given
//        final Oompa oompa = new Oompa("", 0, "");
//        when(repository.save(any(Oompa.class))).thenReturn(oompa);
//
//        //when
//        final Oompa outcome = sut.addOompa(oompa);
//
//        //then
//        assertThat(outcome).isEqualTo(oompa);
//    }

    @Test
    public void oompaShouldBeUpdated() {
        //given
        final Oompa oompa = getExampleOompa();
        when(repository.existsById(anyLong())).thenReturn(TRUE);
        when(repository.save(any(Oompa.class))).thenReturn(oompa);

        //when
        final Oompa outcome = sut.updateOompa(ANY_ID, oompa);

        //then
        assertThat(outcome).isEqualTo(oompa);
        verify(repository).existsById(ANY_ID);
        verify(repository).save(any(Oompa.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateOompaShoulThrowResourceNotFoundException() {
        //given
        final Oompa oompa = getExampleOompa();
        when(repository.existsById(anyLong())).thenReturn(FALSE);

        //when
        sut.updateOompa(ANY_ID, oompa);
    }

    @Test
    public void oompaShouldBeDeleted() {
        //given
        when(repository.existsById(anyLong())).thenReturn(TRUE);

        //when
        sut.deleteOompa(ANY_ID);

        //then
        verify(repository).existsById(ANY_ID);
        verify(repository).deleteById(ANY_ID);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteOompaShouldThrowResourceNotFoundException() {
        //given
        when(repository.existsById(anyLong())).thenReturn(FALSE);

        //when
        sut.deleteOompa(ANY_ID);
    }
}