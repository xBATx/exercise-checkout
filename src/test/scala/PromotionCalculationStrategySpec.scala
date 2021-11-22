import PromotionStrategy._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PromotionCalculationStrategySpec extends AnyFlatSpec with Matchers {
  val anotherProduct: ProductQuantity = ProductQuantity(Product("apple", price = 30), quantity = 2)

  "Buy1Get1Free.getPromotionPrice" should "return correct price in several situations" in {
    val promotedProduct: Product = Product("orange", price = 20)
    Buy1Get1Free(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 0), anotherProduct)) shouldBe 0
    Buy1Get1Free(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 2), anotherProduct)) shouldBe 20
    Buy1Get1Free(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 3))) shouldBe 20
    Buy1Get1Free(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 4))) shouldBe 40
  }

  "Buy3Pay2.getPromotionPrice" should "return correct price in several situations" in {
    val promotedProduct: Product = Product("orange", price = 1)
    Buy3Pay2(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 2), anotherProduct)) shouldBe 0
    Buy3Pay2(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 3), anotherProduct)) shouldBe 1
    Buy3Pay2(promotedProduct).getPromotionPrice(Set(ProductQuantity(promotedProduct, quantity = 8))) shouldBe 2
  }

  "Buy2GetAnotherFree.getPromotionPrice" should "return correct price in several situations" in {
    val baseProduct: Product = Product("orange", price = 1)
    val promotedProduct: Product = Product("banana", price = 10)

    Buy2GetAnotherFree(baseProduct, promotedProduct.id)
      .getPromotionPrice(Set(ProductQuantity(baseProduct, quantity = 1), ProductQuantity(promotedProduct, 2))) shouldBe 0
    Buy2GetAnotherFree(baseProduct, promotedProduct.id)
      .getPromotionPrice(Set(ProductQuantity(baseProduct, quantity = 2), ProductQuantity(promotedProduct, 2))) shouldBe 10
    Buy2GetAnotherFree(baseProduct, promotedProduct.id)
      .getPromotionPrice(Set(ProductQuantity(baseProduct, quantity = 4), ProductQuantity(promotedProduct, 2))) shouldBe 20
    Buy2GetAnotherFree(baseProduct, promotedProduct.id)
      .getPromotionPrice(Set(ProductQuantity(baseProduct, quantity = 8), ProductQuantity(promotedProduct, 2))) shouldBe 20
  }

}
