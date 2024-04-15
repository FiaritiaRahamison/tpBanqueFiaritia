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
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;
import java.util.logging.Logger;
/**
 *
 * @author raham
 */
@Named(value = "ajouterCompte")
@RequestScoped
public class AjouterCompte implements Serializable {

    //@NotNull(message = "Le nom est obligatoire.")
    private String nom;
    
    private static final Logger LOGGER = Logger.getLogger(CompteBancaireDetailsBean.class.getName());

    //@PositiveOrZero(message = "Le solde doit être un nombre positif.")
    private int solde;
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    /**
     * Creates a new instance of AjouterCompte
     */
    public AjouterCompte() {
    }
    
    public void nouveauCompte() {
        LOGGER.info("==================NOM: "+ nom +"==============");
        LOGGER.info("================SOLDE: "+ solde +"==============");
        boolean erreur = false;
        
        FacesMessage message = null;
        if (nom.length() == 0) {
            LOGGER.info("==================NOM VIDE ICI==============");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun nom n'a été saisi !", null);
            erreur = true;
        } else if (solde < 0) {
            LOGGER.info("==================SOLDE NEGATIF ICI==============");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le solde saisi est négatif !", null);
            erreur = true;
        } else {
            CompteBancaire compteBancaire = new CompteBancaire(nom, solde);
            gestionnaireCompte.creerCompte(compteBancaire);
            LOGGER.info("Compte bancaire ajouté avec succès : " + compteBancaire.toString());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compte créé avec succès !", null);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        //return "listeComptes?faces-redirect=true";
    }
}
