package com.example.kabisa_backend.service

import com.example.kabisa_backend.TestUtils
import com.example.kabisa_backend.client.QuoteClient
import com.example.kabisa_backend.converter.QuoteConverter
import com.example.kabisa_backend.model.entity.Quote
import com.example.kabisa_backend.model.quoteapi.QuoteRequest
import com.example.kabisa_backend.model.quoteapi.QuoteResponse
import com.example.kabisa_backend.repository.QuoteRepository
import reactor.core.publisher.Mono
import spock.lang.Specification

class QuoteServiceSpec extends Specification {

    def client = Mock(QuoteClient.class)
    def repo = Mock(QuoteRepository.class)
    def converter = Mock(QuoteConverter.class)
    def service = new QuoteService(client, repo, converter)

    final QuoteResponse quoteResponse = TestUtils.createQuoteResponse()
    final QuoteResponse quoteResponse2 = TestUtils.createAnotherQuoteResponse()
    final Quote quote = TestUtils.createQuote()
    final Quote quote2 = TestUtils.createAnotherQuote()

    def "When retrieveRandomQuote is called, a random quote is returned and saved to DB"() {
        given:
        1 * client.getRandomQuote() >> Mono.just(quoteResponse)
        1 * repo.findByQuote(quoteResponse.getQuote()) >> null
        1 * converter.convertToQuoteEntity(_) >> quote

        when:
        def result = service.retrieveRandomQuote().block()

        then:
        1 * repo.save({
            it.author == "testAuthor"
        })
        result != null
        result.author == "testAuthor"
    }

    def "When retrieveRandomQuote is called, a random quote is returned but not saved to DB because it already exists"() {
        given:
        1 * client.getRandomQuote() >> Mono.just(quoteResponse)
        1 * repo.findByQuote(quoteResponse.getQuote()) >> quote
        1 * converter.convertToQuoteResponse(_) >> quoteResponse

        when:
        def result = service.retrieveRandomQuote().block()

        then:
        0 * repo.save(_)
        result != null
        result.author == "testAuthor"
    }

    def "When retrieveAllQuotes is called, all currently saved quotes are returned"() {
        given:
        1 * repo.findAll() >> Arrays.asList(quote, quote2)
        1 * converter.convertToQuoteResponse(quote) >> quoteResponse
        1 * converter.convertToQuoteResponse(quote2) >> quoteResponse2

        when:
        def result = service.retrieveAllQuotes()

        then:
        result != null
        result.size() == 2
    }

    def "When retrieveAllQuotes is called and no quotes are saved, empty list is returned"() {
        given:
        1 * repo.findAll() >> new ArrayList<>()

        when:
        def result = service.retrieveAllQuotes()

        then:
        result != null
        result.size() == 0
    }


    def "When retrieveAllQuotes sorted by likes is called, all currently saved quotes are returned (sorted by likes)"() {
        given:
        1 * repo.findAllByOrderByLikesDesc() >> Arrays.asList(quote2, quote)
        1 * converter.convertToQuoteResponse(quote) >> quoteResponse
        1 * converter.convertToQuoteResponse(quote2) >> quoteResponse2

        when:
        def result = service.retrieveAllQuotesSortedByLikes()

        then:
        result != null
        result[0].getLikes() == 8
    }

    def "When likeAQuote is called, likes for that quote is incremented by one"() {
        given:"Quote created with 5 likes"
        1 * repo.findById(1) >> Optional.of(quote)

        when:
        def result = service.likeQuote(1)

        then:"Quote saved with 6 likes"
        1 * repo.save({
            it.likes == 6
        })
        result != null
    }

    def "When addMyOwnQuote, own quote is saved to the DB"() {
        given:
        QuoteRequest quoteRequest = TestUtils.createQuoteRequest()
        Quote quote = TestUtils.createOwnQuote()
        1 * converter.convertQuoteRequestToQuoteEntity(quoteRequest) >> quote

        when:
        def result = service.addMyOwnQuote(quoteRequest)

        then:
        1 * repo.save({
            it.author == "testAuthor"
            it.quote == "ownQuote"
        })
        result != null
    }

    def "When containingQuotes, all quotes containing that word are returned"() {
        given:
        String containing = "testQuote"
        1 * repo.findByQuoteContainingIgnoreCase(containing) >> Arrays.asList(quote2, quote)
        1 * converter.convertToQuoteResponse(quote) >> quoteResponse
        1 * converter.convertToQuoteResponse(quote2) >> quoteResponse2

        when:
        def result = service.containingQuotes(containing)

        then:
        result != null
        result.size() == 2
    }

}
