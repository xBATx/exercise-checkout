import org.scalatest._
import flatspec._
import matchers.should.Matchers

class CartSpec extends AnyFlatSpec with Matchers {
  val exercise1Cart = new Cart(Set(Product("apple", StandardPriceStrategy(0.6)), Product("orange", StandardPriceStrategy(0.25))))
  val exercise2Cart = new Cart(Set(Product("apple", Buy1Get1Free(0.6)), Product("orange", Buy3Pay2(0.25))))

  "Cart.calculatePrice" should "return 0 price if the cart is empty" in {
    exercise1Cart.calculatePrice(Nil) shouldBe 0
  }

  it should "return correct price for non empty cart" in {
    exercise1Cart.calculatePrice(List("apple", "apple", "orange", "apple")) shouldBe 2.05
  }

  it should "ignore unknown product is used in cart" in {
    exercise1Cart.calculatePrice(List("apple", "apple", "orange", "apple", "whatever")) shouldBe 2.05
  }

  it should "apply buy 1 get 1 free promotion for apples" in {
    exercise2Cart.calculatePrice(List("apple", "apple", "orange", "apple")) shouldBe 2.05
  }

  it should "apply buy 3 pay 2 promotion for oranges" in {
    exercise2Cart.calculatePrice(List("apple", "orange", "orange", "orange")) shouldBe 2.05
  }
}
