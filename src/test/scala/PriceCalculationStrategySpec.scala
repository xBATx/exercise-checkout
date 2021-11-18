import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PriceCalculationStrategySpec extends AnyFlatSpec with Matchers {
  "StandardPriceStrategy.getPrice" should "return correct price in several situations" in {
    StandardPriceStrategy(20).getPrice(1) shouldBe 20
    StandardPriceStrategy(0).getPrice(10) shouldBe 0
    StandardPriceStrategy(5).getPrice(4) shouldBe 20
  }

  "Buy1Get1Free.getPrice" should "return correct price in several situations" in {
    Buy1Get1Free(20).getPrice(0) shouldBe 0
    Buy1Get1Free(20).getPrice(1) shouldBe 20
    Buy1Get1Free(20).getPrice(2) shouldBe 20
    Buy1Get1Free(20).getPrice(3) shouldBe 40
    Buy1Get1Free(1).getPrice(11) shouldBe 6
  }

  "Buy3Pay2.getPrice" should "return correct price in several situations" in {
    Buy3Pay2(1).getPrice(3) shouldBe 2
    Buy3Pay2(1).getPrice(8) shouldBe 6
    Buy3Pay2(2).getPrice(2) shouldBe 4
  }

}
