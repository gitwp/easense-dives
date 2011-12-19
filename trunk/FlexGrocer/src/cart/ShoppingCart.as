package cart
{
	import mx.collections.ArrayCollection;
	import mx.collections.IViewCursor;
	import mx.collections.Sort;
	import mx.collections.SortField;

	public class ShoppingCart
	{
		[Bindable]
		public var items:ArrayCollection = new ArrayCollection();

		[Bindable]
		public var total:Number = 0;

		public function ShoppingCart()
		{
			sortItems();
		}

		private function sortItems():void
		{
			var itemSort:Sort = new Sort();
			itemSort.fields = [new SortField("product")];
			items.sort = itemSort;
			items.refresh();
		}

		public function addItem(item:ShoppingCartItem):void
		{
			if (isItemInCart(item))
			{
				updateItem(item);
			}
			else
			{
				items.addItem(item);
			}
			calculateTotal();
		}

		private function getItemInCart(item:ShoppingCartItem):ShoppingCartItem
		{
			var cursor:IViewCursor = items.createCursor();

			if (cursor.findFirst(item))
			{
				return cursor.current as ShoppingCartItem;
			}

			return null;
		}

		private function isItemInCart(item:ShoppingCartItem):Boolean
		{
			var sci:ShoppingCartItem = getItemInCart(item);
			return (sci != null);
		}

		private function updateItem(item:ShoppingCartItem):void
		{
			var existingItem:ShoppingCartItem = getItemInCart(item);
			existingItem.quantity += item.quantity;
			existingItem.calculateSubtotal();
		}

		private function calculateTotal():void
		{
			var newTotal:Number = 0;
			var existingItem:ShoppingCartItem;
			for (var i:uint = 0; i < items.length; i++)
			{
				existingItem = items[i] as ShoppingCartItem;
				newTotal += existingItem.subtotal;
			}

			this.total = newTotal;
		}

		public function toString():String
		{
			return "[ShoppingCart $" + total + "] " + items;
		}
	}
}
