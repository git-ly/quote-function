package com.example.demo.config;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Quote;
import com.example.demo.service.QuoteService;

import reactor.core.publisher.Flux;

@Configuration
public class QuoteFunctions {
	private static final Logger log = LoggerFactory.getLogger(QuoteFunctions.class);
	
	@Bean
	Supplier<Flux<Quote>> allQuotes(QuoteService quoteService){
		return () -> {
			log.info("Getting all quotes");
			return Flux.fromIterable(quoteService.getAllQuotes())
					.delaySequence(Duration.ofSeconds(1));
		};
	}
	@Bean
	Supplier<Quote> randomQuote(QuoteService quoteService){
		return () -> {
			log.info("Getting random quote");
			return quoteService.getRandomQuote();
		};
	}
	@Bean
	Function<Genre,Quote> genreQuote(QuoteService quoteService){
		return (genre) -> {
			log.info("Getting genre quote");
			return quoteService.getRandomQuoteByGenre(genre);
		};
	}
	
	@Bean
	Consumer<Quote> logQuote(){
		return quote -> log.info("Quote: '{}' by {}",quote.content(),quote.author());
	}

}
