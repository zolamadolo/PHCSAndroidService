package phcs.android.service.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ServiceContent {

	/**
	 * An array of sample (ui) items.
	 */
	public static List<UIItem> ITEMS = new ArrayList<UIItem>();

	/**
	 * A map of sample (ui) items, by ID.
	 */
	public static Map<String, UIItem> ITEM_MAP = new HashMap<String, UIItem>();

	static {
		// Add 3 sample items.
		addItem(new UIItem("1", "PHCS Setting", "Start and Stop Service"));
		addItem(new UIItem("2", "PHCS Notifications","Notifications ON/OFF"));
	}

	private static void addItem(UIItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A ui item representing a piece of content.
	 */
	public static class UIItem {
		public String id;
		public String _listTitle;
		public String _detailTitle;

		public UIItem(String id, String listTitle,String detailTitle) {
			this.id = id;
			this._listTitle = listTitle;
			this._detailTitle = detailTitle;
		}

		@Override
		public String toString() {
			return _listTitle;
		}
	}
}
