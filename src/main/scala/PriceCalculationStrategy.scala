sealed trait PriceCalculationStrategy {
  def getPrice(quantity: Int): Double
}

case class StandardPriceStrategy(itemPrice: Double) extends PriceCalculationStrategy {
  override def getPrice(quantity: Int): Double = quantity * itemPrice
}


