package pl.futurecollars.invoicing.service

import pl.futurecollars.invoicing.db.Database
import pl.futurecollars.invoicing.db.memory.InMemoryDatabase
import pl.futurecollars.invoicing.model.Invoice
import spock.lang.Specification

class InvoiceServiceTest extends Specification {

    private InvoiceService newInvoiceService
    private Database database

    void setup() {
        database = Mock()
        newInvoiceService = new InvoiceService(database)
    }

    def "Save"() {
        given:
        Invoice invoice = Mock()
        when:
        newInvoiceService.save(invoice)
        then:
        1 * database.save(invoice)
    }

    def "GetById"() {
        given:
        int id = 1
        when:
        newInvoiceService.getById(id)
        then:
        1 * database.getById(id)
    }

    def "GetAll"() {
        when:
        newInvoiceService.getAll()
        then:
        1 * database.getAll()
    }

    def "Update"() {
        given:
        Invoice invoice = Mock()
        int id = 1
        when:
        newInvoiceService.update(id, invoice)
        then:
        1 * database.update(id, invoice)

    }

    def "Delete"() {
        given:
        def id = 1
        when:
        newInvoiceService.delete(id)
        then:
        1 * database.delete(id)
    }
}
