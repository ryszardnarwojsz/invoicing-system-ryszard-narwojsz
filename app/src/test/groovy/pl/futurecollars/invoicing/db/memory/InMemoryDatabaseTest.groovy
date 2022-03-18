package pl.futurecollars.invoicing.db.memory

import pl.futurecollars.invoicing.model.Company
import pl.futurecollars.invoicing.model.Invoice
import pl.futurecollars.invoicing.model.InvoiceEntry
import pl.futurecollars.invoicing.model.Vat
import spock.lang.Specification

import java.time.LocalDate

class InMemoryDatabaseTest extends Specification {

    private InMemoryDatabase database
    private Invoice newInvoice1
    private Invoice newInvoice2
    private Invoice testInvoice

    def setup() {

        Company newCustomer = new Company("54-234 Warszawa ul.Nowa 4", "Mala firma", "5467342334")
        Company newSupplier = new Company("52-311 Wrocław ul.Dawna 34", "Adex comp", "4453321346")
        Company newCustomer2 = new Company("51-234 Ostrów ul.Prawa 3", "CompEr", "5411342334")
        Company newSupplier2 = new Company("50-310 Poznań ul.Lewa 6", "OkComp", "4453221346")

        InvoiceEntry newEntry = new InvoiceEntry("Packaging material", BigDecimal.valueOf(34), BigDecimal.valueOf(345), Vat.VAT_23)
        InvoiceEntry newEntry2 = new InvoiceEntry("Service", BigDecimal.valueOf(340), BigDecimal.valueOf(3450), Vat.VAT_23)

        List<InvoiceEntry> listOfEntries1 = new LinkedList<>()
        listOfEntries1.add(newEntry)

        List<InvoiceEntry> listOfEntries2 = new LinkedList<>()
        listOfEntries2.add(newEntry2)

        newInvoice1 = new Invoice(LocalDate.now(), newCustomer, newSupplier, listOfEntries1)
        newInvoice2 = new Invoice(LocalDate.now(), newCustomer2, newSupplier2, listOfEntries2)
        database = new InMemoryDatabase()
    }

    def "should save two invoices and return proper Id"() {
        when:
        int nextId = database.save(newInvoice1)
        int nextId2 = database.save(newInvoice2)
        then:
        nextId == 1
        nextId2 == 2
    }

    def "should get saved invoice by Id"() {
        given:
        database.save(newInvoice1)
        when:
        testInvoice = database.getById(1)
        then:
        testInvoice.getId() == 1
        testInvoice == newInvoice1

    }

    def "should put all saved invoices into list"() {
        given:
        database.save(newInvoice1)
        database.save(newInvoice2)
        when:
        List<Invoice> newList = database.getAll()
        then:
        newList.get(0) == newInvoice1
        newList.get(1) == newInvoice2
        newList.size() == 2
    }

    def "should updated invoice with Id 1 (replaced it with newInvoice2)"() {
        given:
        database.save(newInvoice1)
        Invoice initialInvoice = database.getById(1)
        Invoice updatedInvoice = newInvoice2
        when:
        database.update(1, updatedInvoice)
        then:
        initialInvoice == newInvoice1
        database.getById(1) == newInvoice2

    }

    def "should throw exception when update method is called with Id which does not exist"() {
        given:
        database.save(newInvoice1)
        Invoice updatedInvoice = newInvoice2
        when:
        database.update(2, updatedInvoice)
        then:
        thrown(IllegalArgumentException)
        database.getById(2) == null

    }

    def "should delete saved newInvoice1 with Id 1"() {
        given:
        database.save(newInvoice1)
        database.save(newInvoice2)
        when:
        database.delete(1)
        then:
        database.getById(1) == null
        database.getById(2) == newInvoice2
    }

}
