package com.example.kabisa_backend.IT

import com.example.kabisa_backend.KabisaBackendApplication
import com.example.kabisa_backend.repository.QuoteRepository
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.http.HttpHeaders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*

@SpringBootTest(classes = KabisaBackendApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("integration")
abstract class BaseTestSpec extends Specification {

    @Shared
    WireMockServer wireMockServer

    @Autowired
    QuoteRepository quoteRepository

    @Autowired
    TestRestTemplate template

    public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("test")
            .withPassword("test")

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.driver-class-name", () -> postgreSQLContainer.getDriverClassName())
        registry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl())
        registry.add("spring.datasource.username", () -> postgreSQLContainer.getUsername())
        registry.add("spring.datasource.password", () -> postgreSQLContainer.getPassword())
    }

    def setup(){
        quoteRepository.deleteAll()
    }

    def setupSpec() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig())
        postgreSQLContainer.start()
        wireMockServer.start()
    }

    void createStub(String urlMatch, String resourceBody) {
        stubFor(get(urlEqualTo(urlMatch))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(resourceBody)))
    }

    protected <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders()
        template.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), responseType)
    }

//    protected <T> ResponseEntity<T> postForEntityWithBody(String url, String body) {
//        HttpHeaders headers = new HttpHeaders()
//        template.postForEntity(url, new HttpEntity<>(body, headers), String.class) as ResponseEntity<T>
//    }
}
