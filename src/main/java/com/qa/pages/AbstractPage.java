package com.qa.pages;


import com.qa.base.Browser;
import com.qa.utils.PropertyLoader;
import java.util.Properties;

public class AbstractPage {

	public Properties prop;
	protected Browser browser;

	public AbstractPage() {
		prop = PropertyLoader.loadProps("src/main/java/com/qa/config/confog.properties");
		this.browser = Browser.getInstance();
	}
}
