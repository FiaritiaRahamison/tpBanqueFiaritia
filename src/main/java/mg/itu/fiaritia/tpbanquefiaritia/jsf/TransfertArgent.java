/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;

/**
 *
 * @author raham
 */
@Named(value = "transfertArgent")
@RequestScoped
public class TransfertArgent {
    private int idCompteSource;
    private int idCompteDestinataire;
    private int montant;

    private static final Logger LOGGER = Logger.getLogger(CompteBancaireDetailsBean.class.getName());
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public int getIdCompteSource() {
        return idCompteSource;
    }

    public void setIdCompteSource(int idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public int getIdCompteDestinataire() {
        return idCompteDestinataire;
    }

    public void setIdCompteDestinataire(int idCompteDestinataire) {
        this.idCompteDestinataire = idCompteDestinataire;
    }
        
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    /**
     * Creates a new instance of ListeComptes
     */
    public TransfertArgent() {
    }
    
    /**
     * Transférer de l'argent d'un compte à un autre.
     * Le compte source est débité.
     * Le compte destinataire est crédité.
     * 
     */
    public void transfert() {
        // Logique de transfert entre les comptes source et destination
        CompteBancaire compteSource = gestionnaireCompte.findById(idCompteSource);
        CompteBancaire compteDestinataire = gestionnaireCompte.findById(idCompteDestinataire);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transfert effectué avec succès", null);
        LOGGER.info("==================AVANT SENDER: "+ compteSource.toString() +"==============");
        LOGGER.info("================AVANT RECEIVER: "+ compteDestinataire.toString() +"==============");
        gestionnaireCompte.transfererArgent(compteSource, compteDestinataire, montant);
        LOGGER.info("==================APRES SENDER: "+ compteSource.toString() +"==============");
        LOGGER.info("================APRES RECEIVER: "+ compteDestinataire.toString() +"==============");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
