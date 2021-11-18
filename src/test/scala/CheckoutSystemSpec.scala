import org.scalatest._
import flatspec._
import matchers.should.Matchers
import PriceCalculationStrategy._

class CheckoutSystemSpec extends AnyFlatSpec with Matchers {
  val exercise1CheckoutSystem = new CheckoutSystem(Set(Product("apple", Default(0.6)), Product("orange", Default(0.25))))
  val exercise2CheckoutSystem = new CheckoutSystem(Set(Product("apple", Buy1Get1Free(0.6)), Product("orange", Buy3Pay2(0.25))))

  "Cart.calculatePrice" should "return 0 price if the cart is empty" in {
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
}
