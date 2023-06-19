package com.goganesh.electron.distribution.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IonAnalyses {

    List<Double> speeds = new ArrayList<>();
    List<Double> distribution = new ArrayList<>();

    List<Double> radiuces = new ArrayList<>();
    List<Double> concentration = new ArrayList<>();
}
