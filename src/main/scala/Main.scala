import PromotionStrategy.{Buy1Get1Free, Buy3Pay2}

object Main extends App {
  val apple = Product("apple", 0.6)
  val orange = Product("orange", 0.25)

  val supportedProducts = Set(apple, orange)

  val promotionStrategies: Set[PromotionStrategy] = Set(Buy1Get1Free(apple), Buy3Pay2(orange))

  val checkoutSystem = new CheckoutSystem(supportedProducts, promotionStrategies)

  val ex1Input = List("apple", "orange")
  println(s"Exercise 1: Calculate basic price without applying promotion with input [${ex1Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex1Input))

  val ex2Input = List("apple", "apple", "apple", "orange")
  println(s"Exercise 2: Calculate basic price with applying Buy1Get1Free promotion with input [${ex2Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex2Input))

  val ex3Input = List("apple", "apple", "orange", "orange", "orange")
  println(s"Exercise 3: Calculate basic price with applying Buy3Pay2 promotion with input [${ex3Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex3Input))

}
