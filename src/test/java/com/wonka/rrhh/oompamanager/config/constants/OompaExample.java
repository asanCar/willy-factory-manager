package com.wonka.rrhh.oompamanager.config.constants;

import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;

import java.util.ArrayList;
import java.util.List;

public class OompaExample {

    public static final long ANY_ID = 1L;
    private static final String[] NAMES = {"John", "Margaret", "Harry", "Bill", "Lucy", "Julia", "Marc", "Charles",
            "Maria", "Carlos"};
    private static final int[] AGES = {32, 22, 53, 55, 42, 33, 29, 34, 58, 28};
    private static final String[] JOBS = {"cooker", "transporter", "cooker", "dancer", "singer", "dancer", "driver",
            "operator", "singer", "spy"};
    private static final float[] HEIGHTS = {1.23f, 0.85f, 0.96f, 1.32f, 1.11f, 1.08f, 0.76f, 1.30f, 1.02f, 0.98f};
    private static final float[] WEIGHTS = {40.5f, 55.3f, 30.2f, 44.3f, 52.4f, 32.4f, 36.5f, 56.2f, 45.9f, 35.7f};

    public static Oompa getExampleOompa() {
        return new Oompa(NAMES[0], AGES[0], JOBS[0]);
    }

    public static Oompa getExampleOompa(int index) {
        return new Oompa(NAMES[index], AGES[index], JOBS[index]);
    }

    public static List<Oompa> getExampleOompaList() {
        final List<Oompa> iterable = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Oompa oompa = new Oompa(NAMES[i], AGES[i], JOBS[i], HEIGHTS[i], WEIGHTS[i], "sample text");
            iterable.add(oompa);
        }

        return iterable;
    }

    public static List<OompaDTO> getExampleOompaDTOs() {
        final List<OompaDTO> iterable = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final OompaDTO oompa = new OompaDTO(NAMES[i], AGES[i], JOBS[i]);
            iterable.add(oompa);
        }

        return iterable;
    }
}
