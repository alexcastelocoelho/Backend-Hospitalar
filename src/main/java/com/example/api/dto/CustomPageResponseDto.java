package com.example.api.dto;

import java.util.List;

public record CustomPageResponseDto<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last

) {
}
