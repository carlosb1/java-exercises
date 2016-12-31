package scrap_example;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

import com.ui4j.api.browser.Page;

public class HackerNews {

	public static void main(String[] args) {

		/*
		 * try (Page page = getWebKit().navigate("https://news.ycombinator.com")) { page.getDocument().queryAll(".title a").forEach(e -> {
		 * System.out.println(e.getText().get()); }); }
		 */

		try (Page page = getWebKit().navigate("http://shop.mango.com/ES/m/hombre/prendas/todas?m=coleccion")) {
			page.getDocument().queryAll(".productList__name").forEach(e -> {
				System.out.println(e.getText().get());
			});
		}

		try (Page page = getWebKit().navigate("http://shop.mango.com/ES/m/hombre/prendas/todas?m=coleccion")) {
			page.getDocument().queryAll(".productList__price").forEach(e -> {
				System.out.println(e.getText().get());
			});
		}
	}
}
