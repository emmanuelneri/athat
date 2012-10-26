package br.com.athat.web.movimentacao.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.athat.core.entity.movimentacao.ItemProduto;
import br.com.athat.core.entity.movimentacao.compra.Compra;
import br.com.athat.core.entity.produto.Produto;
import br.com.athat.core.manager.movimentacao.compra.CompraManager;
import br.com.athat.core.manager.produto.ProdutoManager;
import br.com.athat.web.utils.AbstractController;

public class CompraController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	private Compra compra;
	private Produto produto;
	private ItemProduto itemProduto;
	private List<Produto> produtos;
	private BigDecimal valorTotal;
	
	@Autowired
	private CompraManager compraManager;
	
	@Autowired
	private ProdutoManager produtoManager;
	
	public CompraController() {
		compra = new Compra();
		compra.setItensMovimentacao(new ArrayList<ItemProduto>());
		inicializeProduto();
		produtos = new ArrayList<Produto>();
		valorTotal = BigDecimal.ZERO;
	}
	
	public void buscarProdutos(){
		produtos = produtoManager.buscar(produto);
	}
	
	public void prepararParaAdicionar(){
		inicializeProduto();
	}
	
	public void adicionarProduto(){
		itemProduto.setProduto(produto);
		compra.getItensMovimentacao().add(itemProduto);
		valorTotal.add(itemProduto.getValorTotal());
		inicializeProduto();
	}
	
	public void removerProduto(){
		compra.getItensMovimentacao().remove(itemProduto);
		valorTotal.subtract(itemProduto.getValorTotal());
		inicializeProduto();
	}
	
	public String salvar(){
		compraManager.salvar(compra);
		return "/pages/movimentacao/compra";
	}
	
	private void inicializeProduto(){
		itemProduto = new ItemProduto();
		produto = new Produto();
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ItemProduto getItemProduto() {
		return itemProduto;
	}

	public void setItemProduto(ItemProduto itemProduto) {
		this.itemProduto = itemProduto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public CompraManager getCompraManager() {
		return compraManager;
	}

	public void setCompraManager(CompraManager compraManager) {
		this.compraManager = compraManager;
	}

	public ProdutoManager getProdutoManager() {
		return produtoManager;
	}

	public void setProdutoManager(ProdutoManager produtoManager) {
		this.produtoManager = produtoManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
