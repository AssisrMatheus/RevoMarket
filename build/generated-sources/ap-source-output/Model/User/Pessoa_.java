package Model.User;

import Model.Cotacao.Conta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T19:31:19")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ { 

    public static volatile SingularAttribute<Pessoa, Integer> Rg;
    public static volatile SingularAttribute<Pessoa, String> Profissao;
    public static volatile SingularAttribute<Pessoa, Integer> Cpf;
    public static volatile SingularAttribute<Pessoa, Integer> Id;
    public static volatile SingularAttribute<Pessoa, Conta> Conta;
    public static volatile SingularAttribute<Pessoa, String> Nome;
    public static volatile SingularAttribute<Pessoa, String> Endereco;
    public static volatile SingularAttribute<Pessoa, String> Cidade;
    public static volatile SingularAttribute<Pessoa, String> Estado;

}