/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;
import java.util.logging.Logger;
/**
 *
 * @author raham
 */
@Named(value = "ajouterCompte")
@RequestScoped
public class AjouterCompte {

    private String nom;
    private int solde;
    private static final Logger LOGGER = Logger.getLogger(CompteBancaireDetailsBean.class.getName());

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
        CompteBancaire compteBancaire = new CompteBancaire(nom, solde);
        LOGGER.info("Compte bancaire chargé avec succès : " + compteBancaire.toString());
        gestionnaireCompte.creerCompte(compteBancaire);
        return "listeComptes?faces-redirect=true";
    }
}
