
object Main extends App {
  val supportedProducts = Set(Product("apple", 0.6), Product("orange", 0.25))
  val cart = new Cart(supportedProducts)
  cart.calculatePrice(List("apple", "apple", "orange", "apple"))
}
