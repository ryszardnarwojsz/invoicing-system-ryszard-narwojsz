package pl.futurecollars.invoicing

import spock.lang.Specification

class AppTest extends Specification {
    def "dummy Main"() {
        when:
        def app = new App()
        then:
        App.main()

    }
}
