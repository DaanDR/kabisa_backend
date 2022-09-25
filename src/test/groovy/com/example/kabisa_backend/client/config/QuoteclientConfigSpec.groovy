package com.example.kabisa_backend.client.config


import org.springframework.web.reactive.function.client.WebClient
import spock.lang.Specification

class QuoteclientConfigSpec extends Specification {

    def quoteclientConfig = new QuoteclientConfig()

    def "Client builds correctly"() {
        given:
        quoteclientConfig.quoteUrl = "test"

        when:
        WebClient client = quoteclientConfig.quoteClientConfig()

        then:
        client != null
    }
}
