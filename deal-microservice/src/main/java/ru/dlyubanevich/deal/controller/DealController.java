package ru.dlyubanevich.deal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.deal.model.DataDeal;
import ru.dlyubanevich.deal.service.ProcessingService;

@RestController
@RequiredArgsConstructor
public class DealController {

    private final ProcessingService processingService;

    @PostMapping("/api/v1/deal")
    public String addDeal(@RequestBody DataDeal dataDeal){
        return processingService.addDeal(dataDeal);
    }

    @GetMapping("/api/v1/deal/{dealId}")
    public DataDeal getDeal(@PathVariable String dealId){
        return processingService.getDeal(dealId);
    }
}
