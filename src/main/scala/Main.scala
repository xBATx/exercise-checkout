
object Main extends App {
  val supportedProducts = Set(Product("apple", PriceCalculationStrategy.Default(0.6)), Product("orange", PriceCalculationStrategy.Default(0.25)))
  val checkoutSystem = new CheckoutSystem(supportedProducts)
  checkoutSystem.calculatePrice(List("apple", "apple", "orange", "apple"))
}
