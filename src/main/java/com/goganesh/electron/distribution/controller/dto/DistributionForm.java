package com.goganesh.electron.distribution.controller.dto;

import lombok.Data;

@Data
public class DistributionForm {

    private Double maxVelocity;  // Максимальная скорость в м/с
    private Integer numBins;  // Количество интервалов скоростей

    private Double tension;  // Напряженность поля
    private Double generatorFrequency;  // Частота генератора
    private Double collisionFrequency;  // Частота соударений
    private Double radius = 0.012;  // Радиус разрядной камеры
    private Integer radiusBins;  // Количество интервалов радиуса
}
