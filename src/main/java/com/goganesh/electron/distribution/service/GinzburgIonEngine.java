package com.goganesh.electron.distribution.service;

import com.goganesh.electron.distribution.controller.dto.DistributionForm;
import com.goganesh.electron.distribution.model.IonAnalyses;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.special.BesselJ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.goganesh.electron.distribution.model.DistributionParam.*;
import static com.goganesh.electron.distribution.service.MaxwellIonEngine.calculateVelocity;

@Service
public class GinzburgIonEngine {

    private Double calculateVelocityGinz(Double v, DistributionForm distributionForm) {

        double E = distributionForm.getTension() * Math.pow(10, 6); // Напряженность поля
        double Nu = distributionForm.getCollisionFrequency(); // Частота соударений
        double w = distributionForm.getGeneratorFrequency(); // Частота генератора

        double foo = calculateVelocity(v);
        double pro = (foo * (-m * v)) / (BOLTZMANN_CONSTANT * T);
        double resultGinzburg = foo - ((e * E * pro) / (m * (Math.pow(Nu, 2) + Math.pow(w, 2))));

        return resultGinzburg;
    }

    public IonAnalyses calculateVelocityDistribution(DistributionForm distributionForm) {
        // Расчет интервала скоростей
        double dv = distributionForm.getMaxVelocity() / distributionForm.getNumBins();

        // Заполнение массива скоростей
        List<Double> speeds = new ArrayList<>();
        for (int i = 0; i < distributionForm.getNumBins(); i++) {
            speeds.add((i + 0.5) * dv);
        }

        IonAnalyses ionDistribution = new IonAnalyses();
        ionDistribution.setSpeeds(speeds);

        // Расчет функции распределения Гинзбурга
        for (int i = 0; i < distributionForm.getNumBins(); i++) {
            double v = speeds.get(i);
            double resultGinzburg = calculateVelocityGinz(v, distributionForm);

            ionDistribution.getDistribution().add(resultGinzburg);
        }

        SimpsonIntegrator simpsonIntegrator = new SimpsonIntegrator();
        double sigma = Math.pow(10, -20);
        double integrate = simpsonIntegrator.integrate(
                1000000,
                x -> (m / (2 * e)) / sigma * calculateVelocityGinz(x, distributionForm),
                0,
                speeds.get(speeds.size() - 1));
        double de = (s / (3 * N)) * integrate;

        // Расчет интервала скоростей
        double slice = distributionForm.getRadius() / distributionForm.getRadiusBins();

        // Заполнение массива скоростей
        List<Double> radiuces = new ArrayList<>();
        for (int i = 0; i < distributionForm.getRadiusBins(); i++) {
            radiuces.add((i + 0.5) * slice);
        }
        ionDistribution.setRadiuces(radiuces);

        for (Double sliceItem : radiuces) {
            Double value = BesselJ.value(0, 2.405 * sliceItem / distributionForm.getRadius()) / de * n0;
            ionDistribution.getConcentration().add(value);
        }

        return ionDistribution;
    }
}
