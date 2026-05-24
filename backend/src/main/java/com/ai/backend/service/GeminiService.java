package com.ai.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String generateSummary(String transcript) {
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

            RestTemplate restTemplate = new RestTemplate();

            // Correct nesting: contents > [{ parts: [{ text: "..." }] }]
            Map<String, Object> textPart = new HashMap<>();
            textPart.put("text", "Summarize this lecture in simple bullet points:\n\n" + transcript);

            Map<String, Object> contentItem = new HashMap<>();
            contentItem.put("parts", List.of(textPart));  // ✅ parts goes inside contentItem

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("contents", List.of(contentItem));  // ✅ not wrapping in another map

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            // ✅ Extract actual text from response instead of raw toString()
            Map body = response.getBody();
            List candidates = (List) body.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            Map firstPart = (Map) parts.get(0);
            return (String) firstPart.get("text");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling Gemini: " + e.getMessage();
        }
    }
   }