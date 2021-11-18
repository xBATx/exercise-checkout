sealed trait PriceCalculationStrategy {
  def getPrice(quantity: Int): Double
}

case class StandardPriceStrategy(itemPrice: Double) extends PriceCalculationStrategy {
  override def getPrice(quantity: Int): Double = quantity * itemPrice
}

case class Buy1Get1Free (itemPrice: Double) extends PriceCalculationStrategy {
  def getPrice(quantity: Int): Double = -1
}

case class Buy3Pay2 (itemPrice: Double) extends PriceCalculationStrategy {
  def getPrice(quantity: Int): Double = -1
}


