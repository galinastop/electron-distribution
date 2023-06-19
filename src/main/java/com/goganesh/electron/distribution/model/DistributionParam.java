package com.goganesh.electron.distribution.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistributionParam {

    public static final double BOLTZMANN_CONSTANT = 1.380649e-23;  // Постоянная Больцмана в м^2·кг/(с^2·К)
    public static final double N = Math.pow(10, 22); // Концентрация нейтральных частиц
    public static final double m = 9.1093837e-31; // Масса электрона
    public static final double T = 11600; // Температура элеткронов в Кельвинах
    public static final double e = 1.6e-19; // Заряд электрона
    public static final double s = Math.sqrt((2 * e) / m);
    public static final double n0 = 1e19;
}
