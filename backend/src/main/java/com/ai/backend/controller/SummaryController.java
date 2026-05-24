package com.ai.backend.controller;

import com.ai.backend.model.SummarizeRequest;
import com.ai.backend.model.SummarizeResponse;
import com.ai.backend.service.SummaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping("/summarize")
    public SummarizeResponse summarize(@RequestBody SummarizeRequest request) {

        String result = summaryService.summarizeVideo(request.getYoutubeUrl());

        return new SummarizeResponse(result);
    }
}