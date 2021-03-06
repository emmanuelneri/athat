package br.com.athat.core.manager.conta;

import java.util.List;

import br.com.athat.core.entity.conta.Conta;
import br.com.athat.core.entity.conta.ContaAPagar;
import br.com.athat.core.entity.conta.Parcela;
import br.com.athat.core.entity.conta.SituacaoContaType;
import br.com.athat.core.entity.movimentacao.compra.Compra;
import br.com.athat.core.manager.AbstractManager;
import br.com.athat.core.manager.AbstractManagerImpl;
import br.com.athat.core.manager.movimentacao.compra.CompraManager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ContaAPagarManagerImpl extends AbstractManagerImpl implements ContaAPagarManager {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ParcelaManager parcelaManager;
    
    @Autowired
    private CompraManager compraManager;

    @Transactional
    @Override
    public void salvar(ContaAPagar conta, Compra compra) {
        if (conta.getId() == null) {
            getEntityManager().persist(conta);
        } else {
            getEntityManager().merge(conta);
        }
        if(compra != null) {
            compraManager.salvar(compra); 
        }
       
    }

    @Override
       @Transactional(readOnly=true)
    public List<Conta> buscarContaAberta() {
        Criteria criteria = createSession().createCriteria(ContaAPagar.class, "c")
                .add(Restrictions.eq("c.situacao", SituacaoContaType.ABERTA));

        List<Conta> a = ( List<Conta> ) criteria.list();
        
        return a;
    }
}
