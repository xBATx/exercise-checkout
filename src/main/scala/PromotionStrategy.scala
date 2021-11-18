sealed trait PromotionStrategy {
  def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal
}

 object PromotionStrategy {
   val zero = BigDecimal(0)

   case class Buy1Get1Free(product: Product) extends PromotionStrategy {
     def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal = {
       val relatedProductWithQuantity = productsWithQuantities.find(_.product.id == product.id)
       relatedProductWithQuantity.map {
         case ProductQuantity(product, quantity) if quantity > 1 => quantity / 2 * product.price
         case _ => zero
       }.getOrElse(0)
     }

     override def toString = s"Buy1Get1Free for product: [${product.id}]"
   }

   case class Buy3Pay2(product: Product) extends PromotionStrategy {
     def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal = {
       val relatedProductWithQuantity = productsWithQuantities.find(_.product.id == product.id)
       relatedProductWithQuantity.map {
         case ProductQuantity(product, quantity) =>
           if (quantity > 2) {
              quantity / 3 * product.price
           }
           else zero
       }.getOrElse(0)
     }

     override def toString = s"Buy3Pay2 for product: [${product.id}]"
   }

   case class Buy2GetAnotherFree(product: Product, freeProductId: String) extends PromotionStrategy {
     def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal = {
       val relatedProductWithQuantity = productsWithQuantities.find(_.product.id == product.id)
       relatedProductWithQuantity.map {
         case pq if pq.quantity > 1 =>
           val promotedQuantity = pq.quantity / 2
           productsWithQuantities.collectFirst {
             case ProductQuantity(product, quantity) if product.id == freeProductId => Math.min(promotedQuantity, quantity) * product.price
           }.getOrElse(zero)
         case _ => zero
       }.getOrElse(0)
     }

     override def toString = s"Buy2GetAnotherFree for product: [${product.id}] and freeProductId: [$freeProductId]"
   }

 }


