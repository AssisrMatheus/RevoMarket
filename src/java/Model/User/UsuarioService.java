/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import Model.DAO.BaseDAO;
import Util.ValidationResult;
import javax.persistence.Query;

/**
 *
 * @author adowt
 */
public class UsuarioService {

    public ValidationResult cadastraUsuario(Usuario usuario) {
        ValidationResult result = this.validaUsuarioCadastro(usuario);
        if (result.isSucess()) {
            BaseDAO dao = new BaseDAO();

            dao.getEntityManager().getTransaction().begin();
//            dao.getEntityManager().persist(usuario.getPessoa().getConta().getAcoes());
            dao.getEntityManager().persist(usuario.getPessoa().getConta());
            dao.getEntityManager().persist(usuario.getPessoa());
            dao.getEntityManager().persist(usuario);
            dao.getEntityManager().getTransaction().commit();

            return result;
        }
        result.addError("Não foi possível inserir no banco");
        return result;
    }

    private ValidationResult validaUsuarioCadastro(Usuario usuario) {
        ValidationResult result = new ValidationResult("Cadastro");

        return result;
    }

    public ValidationResult loginValido(Usuario usuario) {
        ValidationResult result = this.validaUsuarioLogin(usuario);

        if (result.isSucess()) {
            BaseDAO dao = new BaseDAO();

            Query query = dao.getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.Login = :login AND u.Senha = :senha");
            query.setParameter("login", usuario.getLogin());
            query.setParameter("senha", usuario.getSenha());

            try {
                query.getSingleResult();
            } catch (Exception ex) {
                result.addError("Não foi possível conectar e verificar o login");
                return result;
            }
        }

        return result;
    }

    private ValidationResult validaUsuarioLogin(Usuario usuario) {
        ValidationResult result = new ValidationResult("Login");

        return result;
    }

    public long getTotalQuantUsuarios() {
        try {
            BaseDAO dao = new BaseDAO();

            Query query = dao.getEntityManager().createQuery("SELECT count(u) FROM Usuario u ");

            return (long) query.getSingleResult();
        } catch (Exception ex) {
            return -999;
        }
    }
}
