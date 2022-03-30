package pl.futurecollars.invoicing.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Invoice {

  private Integer id;
  private LocalDate date;
  private Company customerCompany;
  private Company supplierCompany;
  private List<InvoiceEntry> invoiceEntryList;

  public Invoice(LocalDate date, Company customerCompany, Company supplierCompany,
                 List<InvoiceEntry> invoiceEntryList) {

    this.date = date;
    this.customerCompany = customerCompany;
    this.supplierCompany = supplierCompany;
    this.invoiceEntryList = invoiceEntryList;
  }

}

