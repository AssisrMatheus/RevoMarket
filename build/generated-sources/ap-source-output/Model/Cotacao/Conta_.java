package Model.Cotacao;

import Model.Cotacao.Acao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T22:29:38")
@StaticMetamodel(Conta.class)
public class Conta_ { 

    public static volatile ListAttribute<Conta, Acao> Acoes;
    public static volatile SingularAttribute<Conta, Integer> Id;
    public static volatile SingularAttribute<Conta, Double> Credito;
    public static volatile SingularAttribute<Conta, Double> CorretagemTotalPaga;

}