
class CheckoutSystem(supportedProducts: Set[Product], promotionStrategies: Set[PromotionStrategy] = Set.empty) {

  def calculatePrice(products: List[String]): BigDecimal = {
    val countedProducts = products.groupBy(s => s).view.mapValues(_.length).toMap
    val totalPrice = countedProducts.flatMap {
      case (productId, quantity) =>
        supportedProducts
          .collectFirst {
            case sp if sp.id == productId => sp.price * quantity
          }
    }.sum

    val productQuantities = countedProducts.flatMap {
      case (productId, quantity) =>
        supportedProducts.collectFirst {
          case sp if sp.id == productId => ProductQuantity(sp, quantity)
        }
    }.toSet

    val promotionPrice: BigDecimal = promotionStrategies.map(_.getPromotionPrice(productQuantities)).sum

    totalPrice - promotionPrice
  }

}
