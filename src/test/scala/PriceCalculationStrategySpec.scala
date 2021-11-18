import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PriceCalculationStrategySpec extends AnyFlatSpec with Matchers {
  "StandardPriceStrategy.getPrice" should "return correct price in several situations" in {
    StandardPriceStrategy(20).getPrice(1) shouldBe 20
    StandardPriceStrategy(0).getPrice(10) shouldBe 0
    StandardPriceStrategy(5).getPrice(4) shouldBe 20
  }

}