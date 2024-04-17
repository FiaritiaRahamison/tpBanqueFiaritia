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
import java.util.logging.Logger;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;
import mg.itu.fiaritia.tpbanquefiaritia.util.Util;

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
    
    private static final Logger LOGGER = Logger.getLogger(CompteBancaireDetailsBean.class.getName());

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
        LOGGER.info("Chargement du compte bancaire avec l'ID : " + id);
        this.compteBancaire = gestionnaireCompte.findById(id);
        LOGGER.info("Compte bancaire chargé avec succès : " + compteBancaire.toString());
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
        boolean erreur = false;
        
        LOGGER.info("=============================");
        LOGGER.info(compteBancaire.toString());
        LOGGER.info("=============================");
        
        if (compteBancaire.getNom().length() != 0) {
            compteBancaire = gestionnaireCompte.updateCompteBancaire(compteBancaire);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification réussie.", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de la modification, remplir le champ `nom`.", null);
            Util.messageErreur("Nom vide!", "Nom vide!", "details:nom");
            erreur = true;
        }
        
        if (erreur) {
            return null;
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
