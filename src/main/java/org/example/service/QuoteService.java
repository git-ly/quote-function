package org.example.service;

import org.example.domain.Genre;
import org.example.domain.Quote;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    private static final Random random = new Random();
    private static final List<Quote> quotes = List.of(
            new Quote("Content A","Abigail", Genre.ADVENTURE),
            new Quote("Content B","Beatrix",Genre.ADVENTURE),
            new Quote("Content C","Casper",Genre.FANTASY),
            new Quote("Content D","Dobby",Genre.FANTASY),
            new Quote("Content E","Eileen",Genre.SCIENCE_FICTION),
            new Quote("Content F","Flora",Genre.SCIENCE_FICTION)
    );

    public List<Quote> getAllQuotes(){
        return quotes;
    }

    public Quote getRandomQuote(){
        return quotes.get(random.nextInt(quotes.size()-1));
    }

    public Quote getRandomQuoteByGenre(Genre genre){
        var quotesForGenre = quotes.stream()
                .filter(q -> q.genre().equals(genre))
                .toList();
        return quotesForGenre.get(random.nextInt(quotesForGenre.size() - 1));
    }
}
