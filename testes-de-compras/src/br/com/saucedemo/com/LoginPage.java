package br.com.saucedemo.com;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void carrega() {
		driver.get("https://www.saucedemo.com/");
	}
	
	public ProdutosPage logar() {
		driver.findElement(By.id("login-button"));
		return new ProdutosPage(driver);
	}
	
	public boolean validaLogin(String produtos) {
		return driver.getPageSource().contains(produtos);
	}

	public void fazerLogout() {
		WebElement btnMenu = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div/button"));
		btnMenu.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).click();
		
	}
	
}
