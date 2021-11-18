class CheckoutSystem(supportedProducts: Set[Product]) {

  def calculatePrice(products: List[String]): Double = {
    val countedProducts = products.groupBy(s => s).view.mapValues(_.length).toMap
    countedProducts.flatMap {
      case (product, quantity) => supportedProducts.find(_.id == product).map(_.priceCalculationStrategy.getPrice(quantity))
    }.sum
  }

}
