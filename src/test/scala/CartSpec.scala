import org.scalatest._
import flatspec._
import matchers.should.Matchers

class CartSpec extends AnyFlatSpec with Matchers {
  val cart = new Cart(Set(Product("apple", 0.6), Product("orange", 0.25)))

  "Cart.calculatePrice" should "return 0 price if the cart is empty" in {
    cart.calculatePrice(Nil) shouldBe 0
  }

  it should "return correct price for non empty cart" in {
    cart.calculatePrice(List("apple", "apple", "orange", "apple")) shouldBe 2.05
  }

  it should "ignore unknown product is used in cart" in {
    cart.calculatePrice(List("apple", "apple", "orange", "apple", "whatever")) shouldBe 2.05
  }
}
