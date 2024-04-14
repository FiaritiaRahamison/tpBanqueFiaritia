/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;

/**
 *
 * @author raham
 */
@Named(value = "listeComptes")
@ViewScoped
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
     * @return 
     */
    public void supprimerCompte(CompteBancaire compteBancaire) {
        FacesMessage message = null;
        
        compteBancaire = gestionnaireCompte.findById(compteBancaire.getId());
        if (compteBancaire != null) {
            gestionnaireCompte.supprimerCompte(compteBancaire);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supprimé avec succès.", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Actualiser la page et réessayer.", null);
        }
       
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
