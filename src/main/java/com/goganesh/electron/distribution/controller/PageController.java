package com.goganesh.electron.distribution.controller;

import com.goganesh.electron.distribution.controller.dto.DistributionForm;
import com.goganesh.electron.distribution.model.IonAnalyses;
import com.goganesh.electron.distribution.service.GinzburgIonEngine;
import com.goganesh.electron.distribution.service.MaxwellIonEngine;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PageController {

    private final GinzburgIonEngine ginzburgIonEngine;
    private final MaxwellIonEngine maxwellIonEngine;

    @GetMapping
    public String getMainPage() {
        return "find_distribution_form";
    }

    @PostMapping(value = "/distributions", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String findDistribution(DistributionForm distributionForm,
                                   Model model) {

        IonAnalyses maxwellDist = maxwellIonEngine.calculateVelocityDistribution(distributionForm);
        IonAnalyses ginzburgDist = ginzburgIonEngine.calculateVelocityDistribution(distributionForm);

        model.addAttribute("distributionForm", distributionForm);
        model.addAttribute("maxwellDist", maxwellDist);
        model.addAttribute("ginzburgDist", ginzburgDist);

        return "distribution_result";
    }

}
