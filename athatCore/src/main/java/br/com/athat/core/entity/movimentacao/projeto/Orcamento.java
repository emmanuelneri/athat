package br.com.athat.core.entity.movimentacao.projeto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.athat.core.entity.movimentacao.enuns.OrcamentoType;
import br.com.athat.core.entity.movimentacao.enuns.SituacaoMovimentacaoType;
import br.com.athat.core.entity.pessoa.funcionario.Funcionario;

@Entity
@DiscriminatorValue("ORCAMENTO")
public class Orcamento extends Levantamento {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "funcionarioOrcamento")
	private Funcionario funcionario;
	
	@Column(length = 2000, name = "observacaoOrcamento")
	private String observacao;
	
	public Orcamento() {
		this.orcamentoType = OrcamentoType.ORCAMENTO;
	}
	
	public Orcamento(Levantamento levantamento) {
		dataFinalizacao = levantamento.getDataFinalizacao();
		valorTotal = levantamento.getValorTotal();
		projeto = levantamento.getProjeto();
		itensMovimentacao = levantamento.getItensMovimentacao();
		situacaoMovimentacaoType = SituacaoMovimentacaoType.ABERTA;
		orcamentoType = OrcamentoType.ORCAMENTO;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}