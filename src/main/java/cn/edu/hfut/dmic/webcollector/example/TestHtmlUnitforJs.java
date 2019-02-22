package cn.edu.hfut.dmic.webcollector.example;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestHtmlUnitforJs {

	public static void main(String[] args) {
        
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        String url="http://fund.jrj.com.cn/";
        driver.get(url);

	}

}
