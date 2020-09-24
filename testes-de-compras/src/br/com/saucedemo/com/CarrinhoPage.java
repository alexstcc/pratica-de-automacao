package br.com.saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarrinhoPage {
	
	private final WebDriver driver;
	
	public CarrinhoPage(WebDriver driver){
		this.driver = driver;
	}

	public void prosseguirParaCheckout() {
		WebElement btnCheckout = driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/a[2]"));
		btnCheckout.click();
	}

	public void acessarCarrinho() {
		WebElement btnCarrinho = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/a"));
															 //"/html/body/div/div[2]/div[1]/div[2]/a/svg/path"
		btnCarrinho.click();
	}

}
