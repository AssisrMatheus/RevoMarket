package Model.Cotacao;

import Model.Cotacao.Conta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T19:31:19")
@StaticMetamodel(Acao.class)
public class Acao_ { 

    public static volatile SingularAttribute<Acao, Double> ult_cotacao;
    public static volatile SingularAttribute<Acao, Double> aber_cotacao;
    public static volatile SingularAttribute<Acao, Double> variacao;
    public static volatile SingularAttribute<Acao, Double> max_cotacao_dia;
    public static volatile SingularAttribute<Acao, Double> min_cotacao_dia;
    public static volatile SingularAttribute<Acao, Integer> Id;
    public static volatile SingularAttribute<Acao, Conta> Conta;
    public static volatile SingularAttribute<Acao, String> acao;
    public static volatile SingularAttribute<Acao, Double> med_cotacao_dia;

}