import PromotionStrategy._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PromotionCalculationStrategySpec extends AnyFlatSpec with Matchers {
  val anotherProduct: ProductQuantity = ProductQuantity(Product("apple", 30), 2)

  "Buy1Get1Free.getPrice" should "return correct price in several situations" in {
    val testProduct: Product = Product("orange", 20)
    Buy1Get1Free(testProduct).getPromotionPrice(Set(ProductQuantity(testProduct, 0), anotherProduct)) shouldBe 0
    Buy1Get1Free(testProduct).getPromotionPrice(Set(ProductQuantity(testProduct, 2), anotherProduct)) shouldBe 20
    Buy1Get1Free(testProduct).getPromotionPrice(Set(ProductQuantity(testProduct, 3))) shouldBe 20
    Buy1Get1Free(testProduct).getPromotionPrice(Set(ProductQuantity(testProduct, 4))) shouldBe 40
  }

  "Buy3Pay2.getPrice" should "return correct price in several situations" in {
    val testProduct: Product = Product("orange", 1)
    Buy3Pay2(testProduct).getPromotionPrice(Set(ProductQuantity(testProduct, 3), anotherProduct)) shouldBe 1
    Buy3Pay2(testProduct).getPromotionPrice(Set(ProductQuantity(testProduct,8))) shouldBe 2
  }

}
