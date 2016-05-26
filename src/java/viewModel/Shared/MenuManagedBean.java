/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.Shared;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "menu")
@RequestScoped
public class MenuManagedBean {

    /**
     * Creates a new instance of MenuManagedBean
     */
    public MenuManagedBean() {
    }
    
    public String pageHome(){
        return "/index";
    }
    
}
