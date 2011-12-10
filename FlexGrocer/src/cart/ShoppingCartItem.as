package cart {
	import valueObjects.Product;

	public class ShoppingCartItem {
		public var product:Product;
		public var quantity:uint;
		public var subtotal:Number;

		public function ShoppingCartItem( product:Product, quantity:uint=1 ){
			this.product = product;
			this.quantity = quantity;
			calculateSubtotal();
		}
		
		public function calculateSubtotal():void{
			this.subtotal = product.listPrice * quantity;
		}
		
		public function toString():String {
			return "[ShoppingCartItem] " + product.prodName + ":" + quantity;
		}
	}
}