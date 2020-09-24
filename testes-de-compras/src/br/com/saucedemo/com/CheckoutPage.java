package br.com.saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	
	private final WebDriver driver;
	
	public CheckoutPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void preencheDadosParaEnvio() {
		WebElement txtPrimeiroNome = driver.findElement(By.id("first-name"));
		txtPrimeiroNome.sendKeys("Alex");
		
		WebElement txtUltimoNome = driver.findElement(By.id("last-name"));
		txtUltimoNome.sendKeys("Antonio Silva");
		
		WebElement txtCep = driver.findElement(By.id("postal-code"));
		txtCep.sendKeys("96745000");
		
		txtPrimeiroNome.submit();
		
	}

	public void validaDadosEnvio(String nome, String sobrenome, String cep) {
		WebElement txtPrimeiroNome = driver.findElement(By.id("first-name"));
		txtPrimeiroNome.sendKeys(nome);
		
		WebElement txtUltimoNome = driver.findElement(By.id("last-name"));
		txtUltimoNome.sendKeys(sobrenome);
		
		WebElement txtCep = driver.findElement(By.id("postal-code"));
		txtCep.sendKeys(cep);
		
		txtPrimeiroNome.submit();
	}
	
	public boolean validaCep(String cep) {
		return driver.getPageSource().contains(cep);
	}

	public boolean validaTotalDaCompra(String total) {
		return driver.getPageSource().contains(total);		
	}

	public void prosseguirParaFinalizacaoDaCompra() {
		WebElement btnFinalizar = driver.findElement(By.linkText("FINISH"));
		
		btnFinalizar.click();
	}

	public boolean confirmaFinalizacaoDoPedido(String confirmado) {
		return driver.getPageSource().contains(confirmado);
	}

}
