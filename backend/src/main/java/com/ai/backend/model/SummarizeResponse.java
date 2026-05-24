package com.ai.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummarizeResponse {
    private String summary;
}