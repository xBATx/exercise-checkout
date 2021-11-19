import PromotionStrategy._
import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers

class CheckoutSystemSpec extends AnyFlatSpec with Matchers {

  val apple: Product = Product("apple", 0.6)
  val orange: Product = Product("orange", 0.25)
  val banana: Product = Product("banana", 1)

  val exercise1CheckoutSystem = new CheckoutSystem(Set(apple, orange))
  val exercise2CheckoutSystem = new CheckoutSystem(
    Set(apple, orange),
    Set(Buy1Get1Free(apple), Buy3Pay2(orange))
  )
  val exercise3CheckoutSystem = new CheckoutSystem(
    Set(apple, orange, banana),
    Set(Buy2GetAnotherFree(apple, banana.id))
  )

  "CheckoutSystem.calculatePrice" should "return 0 price if the cart is empty" in {
    exercise1CheckoutSystem.calculatePrice(Nil) shouldBe 0
  }

  it should "return correct price for non empty cart" in {
    exercise1CheckoutSystem.calculatePrice(List("apple", "apple", "orange", "apple")) shouldBe 2.05
  }

  it should "ignore unknown product is used in cart" in {
    exercise1CheckoutSystem.calculatePrice(List("apple", "apple", "orange", "apple", "whatever")) shouldBe 2.05
  }

  it should "apply buy 1 get 1 free promotion for apples" in {
    exercise2CheckoutSystem.calculatePrice(List("apple", "apple", "orange", "apple")) shouldBe 1.45
  }

  it should "apply buy 3 pay 2 promotion for oranges" in {
    exercise2CheckoutSystem.calculatePrice(List("apple", "orange", "orange", "orange")) shouldBe 1.1
  }

  it should "apply buy 2 pay get another banana for free" in {
    exercise3CheckoutSystem.calculatePrice(List("apple", "apple", "orange", "banana", "banana")) shouldBe 2.45
  }
}
