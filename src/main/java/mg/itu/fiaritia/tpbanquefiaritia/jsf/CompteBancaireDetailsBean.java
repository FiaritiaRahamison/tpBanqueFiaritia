/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;

/**
 *  * Backing bean de la page compteBancaireDetails.xhtml.
 *
 * @author raham
 */
@Named(value = "compteBancaireDetailsBean")
@ViewScoped
public class CompteBancaireDetailsBean implements Serializable {

    private int id;
    private CompteBancaire compteBancaire;
    private String successMessage; // Message de succès

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @PostConstruct
    public void init() {
        if (id != 0) {
            loadCompteBancaire(); // Charge le compte bancaire lors de l'initialisation si l'ID est présent
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * Retourne les détails du compte courant (contenu dans l'attribut
     * compteBancaire de cette classe).
     *
     * @return CompteBancaire
     */
    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    /**
     * Charger les détails d'un compte donné
     */
    public void loadCompteBancaire() {
        this.compteBancaire = gestionnaireCompte.findById(id);
    }

    /**
     * Afficher les détails d'un compte bancaire choisi.
     *
     * @return String
     */
    public String afficher() {
        return "compteBancaireDetails?id=" + id + "&faces-redirect=true";
    }

    /**
     * Modifier un compte.
     *
     * @return String
     */
    public String updateCompteBancaire() {
        FacesMessage message = null;
        
        if (compteBancaire.getNom().length() != 0) {
            compteBancaire = gestionnaireCompte.updateCompteBancaire(compteBancaire);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modification réussie.", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de la modification, remplir le champ `nom`.", null);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "listeComptes";
    }

    /**
     * Creates a new instance of CustomerDetailsBean
     */
    public CompteBancaireDetailsBean() {
    }

}
