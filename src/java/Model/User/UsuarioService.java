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
    
    private Usuario ultimoUsuario;
    
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
        
        if(usuario.getPessoa().getConta().getCredito() < 500){
            result.addError("O investimento mínimo é de R$500");
        }

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
                setUltimoUsuario((Usuario)query.getSingleResult());
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
    
    public Usuario getUpdateUsuario(Usuario usuario) {
         try {
            BaseDAO dao = new BaseDAO();

            Query query = dao.getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.Id = :usuId");
            query.setParameter("usuId", usuario.getId());

            return (Usuario) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public Usuario getUltimoUsuario(){
        return this.ultimoUsuario;
    }

    private void setUltimoUsuario(Usuario ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }
}
