package com.ai.backend.service;

import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final GeminiService geminiService;

    public SummaryService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String summarizeVideo(String youtubeUrl) {

        // TEMP: we will replace with real transcript in next step
        String fakeTranscript = "This is a long lecture about DBMS, normalization, ACID properties, indexing, SQL joins...";

        return geminiService.generateSummary(fakeTranscript);
    }
}