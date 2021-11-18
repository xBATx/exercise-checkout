import PromotionStrategy.{Buy1Get1Free, Buy2GetAnotherFree, Buy3Pay2}

object Main extends App {
  val apple = Product("apple", 0.6)
  val orange = Product("orange", 0.25)
  val banana = Product("orange", 1)

  val supportedProducts = Set(apple, orange, banana)
  val promotionStrategies: Set[PromotionStrategy] = Set(Buy1Get1Free(apple), Buy3Pay2(orange), Buy2GetAnotherFree(orange, banana.id))
  val checkoutSystem = new CheckoutSystem(supportedProducts, promotionStrategies)

  // TODO maybe improve output - not log case classes
  println(s"Supported products: [${supportedProducts.map(p => s"${p.id} costs ${p.price}").mkString(",")}]")
  println(s"Supported promotions: [${promotionStrategies.mkString(",")}]")
  println("\n-----\n")


  val ex1Input = List("apple", "orange")
  println(s"Exercise 1: Calculate basic price without applying promotion with input [${ex1Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex1Input))

  val ex2Input = List("apple", "apple", "apple", "orange")
  println(s"Exercise 2: Calculate basic price with applying Buy1Get1Free promotion with input [${ex2Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex2Input))

  val ex3Input = List("apple", "apple", "orange", "orange", "orange")
  println(s"Exercise 3: Calculate basic price with applying Buy3Pay2 promotion with input [${ex3Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex3Input))

  val ex4Input = List("apple", "orange", "orange", "orange", "banana")
  println(s"Exercise 4: Calculate basic price with applying Buy2GetAnotherFree promotion with input [${ex4Input.mkString(",")}]")
  println(checkoutSystem.calculatePrice(ex4Input))

}
