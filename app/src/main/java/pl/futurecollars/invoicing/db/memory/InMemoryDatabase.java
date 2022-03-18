package pl.futurecollars.invoicing.db.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.futurecollars.invoicing.db.Database;
import pl.futurecollars.invoicing.model.Invoice;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> newInvoiceSet = new HashMap<>();
  private int nextId = 1;

  @Override
  public int save(Invoice invoice) {
    invoice.setId(nextId);
    newInvoiceSet.put(nextId, invoice);
    return nextId++;
  }

  @Override
  public Invoice getById(int id) {
    return newInvoiceSet.get(id);
  }

  @Override
  public List<Invoice> getAll() {
    return new ArrayList<>(newInvoiceSet.values());
  }

  @Override
  public void update(int id, Invoice updatedInvoice) {
    if (!newInvoiceSet.containsKey(id)) {
      throw new IllegalArgumentException("Invoice id: " + id + " does not exist");
    }
    updatedInvoice.setId(id);
    newInvoiceSet.put(id, updatedInvoice);
  }

  @Override
  public void delete(int id) {
    newInvoiceSet.remove(id);
  }
}
