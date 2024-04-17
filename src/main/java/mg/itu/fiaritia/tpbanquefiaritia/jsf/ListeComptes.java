/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;

/**
 *
 * @author raham
 */
@Named(value = "listeComptes")
@RequestScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> allComptes;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
        
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    /**
     * Retourne la liste des comptes bancaires pour l'affichage dans une DataTable.
     *
     * @return List
     */
    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllComptes();       
        }
        return allComptes;
    }
    
    /**
     * Supprime un compte après avoir cliqué sur l'icone supprimer de la liste.
     * 
     * @param compteBancaire
     */
    public String supprimerCompte(CompteBancaire compteBancaire) {
        FacesMessage message = null;
        
        compteBancaire = gestionnaireCompte.findById(compteBancaire.getId());
        if (compteBancaire != null) {
            gestionnaireCompte.supprimerCompte(compteBancaire);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supprimé avec succès.", null);
            allComptes = this.getAllComptes();
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Actualiser la page et réessayer.", null);
        }
       
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "listeComptes?faces-redirect=true";
    }
    
    public boolean filterBySolde(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null || filterText.isEmpty()) {
           return true;
       }

        int filterAmount = Integer.parseInt(filterText);
        int balance = ((Integer)value);

        return balance >= filterAmount;
   }
    
}
