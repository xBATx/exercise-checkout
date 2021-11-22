sealed trait PromotionStrategy {
  def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal
}

 object PromotionStrategy {

   case class Buy1Get1Free(product: Product) extends PromotionStrategy {
     def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal = {
       productsWithQuantities.collectFirst {
         case ProductQuantity(p, q) if p.id == product.id && q > 1 =>
           q / 2 * p.price
       }.getOrElse(0)
     }

     override def toString = s"Buy1Get1Free for product: [${product.id}]"
   }

   case class Buy3Pay2(product: Product) extends PromotionStrategy {
     def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal = {
       productsWithQuantities.collectFirst {
         case ProductQuantity(p, q) if p.id == product.id && q > 2 =>
              q / 3 * product.price
       }.getOrElse(0)
     }

     override def toString = s"Buy3Pay2 for product: [${product.id}]"
   }

   case class Buy2GetAnotherFree(baseProduct: Product, freeProductId: String) extends PromotionStrategy {
     def getPromotionPrice(productsWithQuantities: Set[ProductQuantity]): BigDecimal = {
       productsWithQuantities.collectFirst {
         case baseProductQuantity if baseProductQuantity.product.id == baseProduct.id && baseProductQuantity.quantity > 1 =>
           val promotedQuantity = baseProductQuantity.quantity / 2
           productsWithQuantities.collectFirst {
             case ProductQuantity(p, q) if p.id == freeProductId => Math.min(promotedQuantity, q) * p.price
           }
       }.flatten.getOrElse(0)
     }

     override def toString = s"Buy2GetAnotherFree for product: [${baseProduct.id}] and freeProductId: [$freeProductId]"
   }

 }


