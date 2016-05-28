package Model.User;

import Model.User.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T19:31:19")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Boolean> IsAdmin;
    public static volatile SingularAttribute<Usuario, String> Senha;
    public static volatile SingularAttribute<Usuario, Integer> Id;
    public static volatile SingularAttribute<Usuario, String> Login;
    public static volatile SingularAttribute<Usuario, Pessoa> Pessoa;

}