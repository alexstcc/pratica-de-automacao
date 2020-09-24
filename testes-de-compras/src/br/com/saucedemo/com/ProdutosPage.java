package br.com.saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProdutosPage {
	
	private final WebDriver driver;
	
	public ProdutosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void acessa(String nome, String senha){
		
		WebElement txtNome = driver.findElement(By.id("user-name"));
		txtNome.sendKeys(nome);
				
		WebElement txtSenha = driver.findElement(By.id("password"));
		txtSenha.sendKeys(senha);
		
		txtNome.submit();
	}
	
	public void escolherProduto(){
		
		WebElement produto = driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt"));
		produto.click();
	}

	public void adicionaAoCarrinho() {
		
		WebElement adiciona = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/button"));
		((WebElement) adiciona).click();
		
	}

	public boolean validaProdutoEscolhido(String item) {		
		return driver.getPageSource().contains(item);
	}

	public boolean validaAdicaoAoCarrinho(String adicionado) {
		return driver.getPageSource().contains(adicionado);
	}
	
}