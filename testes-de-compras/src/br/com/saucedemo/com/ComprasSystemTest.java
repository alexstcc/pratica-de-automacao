package br.com.saucedemo.com;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

public class ComprasSystemTest {
	
	private ChromeDriver driver;
	
	@Before
	public void inicializa() {
		this.driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void deveNegarAcessoNoSistema() {
		
		LoginPage login = new LoginPage(driver);
				
		login.logar().acessa("standard_userxxx", "secret_sauce");		
		assertTrue(login.validaLogin("Username and password do not match any user in this service"));
		
		driver.navigate().refresh();
		
		login.logar().acessa("", "secret_sauce");		
		assertTrue(login.validaLogin("Username is required"));
		driver.navigate().refresh();
		
		login.logar().acessa("standard_userxxx", "");		
		assertTrue(login.validaLogin("Password is required"));
		
		
	}
	
	@Test
	public void deveLogarNoSistema() {
		
		LoginPage login = new LoginPage(driver);
				
		login.logar().acessa("standard_user", "secret_sauce");		
		assertTrue(login.validaLogin("Product"));	
	}
	
	@Test
	public void deveEscolherProduto() {	
		
		ProdutosPage produto = new ProdutosPage(driver);
		
		deveLogarNoSistema();
		produto.escolherProduto();
		assertTrue(produto.validaProdutoEscolhido("Sauce Labs Bolt T-Shirt")); 
	}
	
	@Test
	public void deveAdicionarProdutoAoCarrinho() {
		
		ProdutosPage addProduto = new ProdutosPage(driver);
		
		deveEscolherProduto();
		addProduto.adicionaAoCarrinho();
		assertTrue(addProduto.validaAdicaoAoCarrinho("REMOVE"));
	}
	
	@Test
	public void deveProsseguirParaCheckout() {
		CarrinhoPage carrinho = new CarrinhoPage(driver);
		
		deveAdicionarProdutoAoCarrinho();
		carrinho.acessarCarrinho();
		carrinho.prosseguirParaCheckout();
	}
	
	@Test
	public void deveEntrarComDadosParaEnvio() {
		CheckoutPage dados = new CheckoutPage(driver);
		
		deveProsseguirParaCheckout();
		dados.preencheDadosParaEnvio();
	}
	
	@Test 
	public void deveValidarDadosDeCheckout() {
		CheckoutPage dadosDeEnvio = new CheckoutPage(driver);
		
		deveProsseguirParaCheckout();
		dadosDeEnvio.validaDadosEnvio("Alex","Antonio Silva","");
		assertTrue(dadosDeEnvio.validaCep("Error: Postal Code is required"));
	}
	
	@Test
	public void deveValidarValorTotalDaCompra() {
		CheckoutPage validaTotal = new CheckoutPage(driver);
		
		deveEntrarComDadosParaEnvio();
		assertTrue(validaTotal.validaTotalDaCompra("17.27"));
	}
	
	@Test
	public void deveFinalizarCompra() {
		CheckoutPage finaliza = new CheckoutPage(driver);
		
		deveValidarValorTotalDaCompra();
		finaliza.prosseguirParaFinalizacaoDaCompra();
		assertTrue(finaliza.confirmaFinalizacaoDoPedido("THANK YOU FOR YOUR ORDER"));
	}
	
	@Test
	public void deveFazerLogout() {
		LoginPage logout = new LoginPage(driver);
		
		deveFinalizarCompra();
		logout.fazerLogout();
	}
	
	@After
	public void finaliza() {
		driver.close();
	}

}
