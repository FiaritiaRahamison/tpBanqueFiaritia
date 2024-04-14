/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;
import java.util.logging.Logger;
import mg.itu.fiaritia.tpbanquefiaritia.util.Util;
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
    
    public String nouveauCompte() {
        LOGGER.info("==================NOM: "+ nom +"==============");
        LOGGER.info("================SOLDE: "+ solde +"==============");
        boolean erreur = false;
        if (nom.length() == 0) {
            LOGGER.info("==================NOM VIDE ICI==============");
            Util.messageErreur("Aucun nom n'a été saisi !", "Aucun nom n'a été saisi!", "nouveauCompte:nom");
            erreur = true;
        }
        
        LOGGER.info("================ERREUR: "+ erreur +"==============");
        /*if (erreur) {
            return "listeComptes?faces-redirect=true";
        }*/
        
       
        CompteBancaire compteBancaire = new CompteBancaire(nom, solde);
        LOGGER.info("Compte bancaire chargé avec succès : " + compteBancaire.toString());
        gestionnaireCompte.creerCompte(compteBancaire);
        Util.addFlashInfoMessage("OK");
        return "listeComptes?faces-redirect=true";
    }
}
