sealed trait PriceCalculationStrategy {
  def getPrice(quantity: Int): Double
}

case class StandardPriceStrategy(itemPrice: Double) extends PriceCalculationStrategy {
  override def getPrice(quantity: Int): Double = quantity * itemPrice
}

case class Buy1Get1Free (itemPrice: Double) extends PriceCalculationStrategy {
  def getPrice(quantity: Int): Double = {
    if (quantity > 1) {
      val countedQuantity = if (quantity % 2 == 0) quantity / 2 else (quantity / 2) + 1
      countedQuantity * itemPrice
    }
    else quantity * itemPrice
  }
}

case class Buy3Pay2 (itemPrice: Double) extends PriceCalculationStrategy {
  def getPrice(quantity: Int): Double = 0
}


