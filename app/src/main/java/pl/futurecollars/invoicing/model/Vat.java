package pl.futurecollars.invoicing.model;

public enum Vat {

  VAT_0(0),
  VAT_5(0.05),
  VAT_8(0.08),
  VAT_23(0.23);

  private double vatRate;

  Vat(double vatRate) {
    this.vatRate = vatRate;
  }

  public double getRate() {
    return vatRate;
  }

}
