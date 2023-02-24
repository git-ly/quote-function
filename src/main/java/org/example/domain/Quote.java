package org.example.domain;

public record Quote(
        String content,
        String author,
        Genre genre
) {
}
