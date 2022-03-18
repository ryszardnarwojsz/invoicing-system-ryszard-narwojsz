/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package pl.futurecollars.invoicing;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import pl.futurecollars.invoicing.db.Database;
import pl.futurecollars.invoicing.db.memory.InMemoryDatabase;
import pl.futurecollars.invoicing.model.Company;
import pl.futurecollars.invoicing.model.Invoice;
import pl.futurecollars.invoicing.model.InvoiceEntry;
import pl.futurecollars.invoicing.model.Vat;
import pl.futurecollars.invoicing.service.InvoiceService;

public class App {

  public static void main(String[] args) throws IOException {

    Company newCustomer = new Company("54-234 Warszawa ul.Nowa 4", "Mala firma", "5467342334");
    Company newSupplier = new Company("52-311 Wrocław ul.Dawna 34", "Adex comp", "4453321346");
    Company newCustomer2 = new Company("51-234 Ostrów ul.Prawa 3", "CompEr", "5411342334");
    Company newSupplier2 = new Company("50-310 Poznań ul.Lewa 6", "OkComp", "4453221346");

    InvoiceEntry newEntry = new InvoiceEntry("Packaging material", BigDecimal.valueOf(34), BigDecimal.valueOf(345), Vat.VAT_23);
    InvoiceEntry newEntry2 = new InvoiceEntry("Service", BigDecimal.valueOf(340), BigDecimal.valueOf(3450), Vat.VAT_23);

    List<InvoiceEntry> listOfEntries1 = new LinkedList<>();
    listOfEntries1.add(newEntry);

    List<InvoiceEntry> listOfEntries2 = new LinkedList<>();
    listOfEntries2.add(newEntry2);

    Invoice newInvoice1 = new Invoice(LocalDate.now(), newCustomer, newSupplier, listOfEntries1);
    Invoice newInvoice2 = new Invoice(LocalDate.now(), newCustomer2, newSupplier2, listOfEntries2);

    Database databaseInMemory = new InMemoryDatabase();
    InvoiceService invoiceService = new InvoiceService(databaseInMemory);

    invoiceService.save(newInvoice1);
    invoiceService.save(newInvoice2);

    System.out.println(invoiceService.getAll());

  }

}

