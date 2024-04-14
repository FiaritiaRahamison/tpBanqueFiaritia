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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;
import mg.itu.fiaritia.tpbanquefiaritia.entity.TypeOperation;
import mg.itu.fiaritia.tpbanquefiaritia.service.GestionnaireCompte;

/**
 *
 * @author raham
 */
@Named(value = "mouvementArgent")
@RequestScoped
public class MouvementArgent {

    private int idCompteSource;
    private int idCompteDestinataire;
    private int montant;
    private TypeOperation typeOperation;
    private List<TypeOperation> typeOperationValues;
    private static final Logger LOGGER = Logger.getLogger(CompteBancaireDetailsBean.class.getName());
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public int getIdCompteSource() {
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> COMPTE ID: " + String.valueOf(idCompteSource) + " >>>>>>>>>>>>>>>>>>>>>");
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

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        LOGGER.info("==================ENUM: " + typeOperation.toString() + "==============");
        this.typeOperation = typeOperation;
    }
   
    public List<TypeOperation> getTypeOperationValues() {
        typeOperationValues = Arrays.asList(TypeOperation.values());
        return typeOperationValues;
    }
    
    /**
     * Creates a new instance of ListeComptes
     */
    public MouvementArgent() {
    }

    /**
     * Transférer de l'argent d'un compte à un autre. Le compte source est
     * débité. Le compte destinataire est crédité.
     *
     */
    public void transfert() {
        FacesMessage message = null;

        CompteBancaire compteSource = gestionnaireCompte.findById(idCompteSource);
        CompteBancaire compteDestinataire = gestionnaireCompte.findById(idCompteDestinataire);
        if (compteSource != null && compteDestinataire != null && montant <= compteSource.getSolde()) {
            LOGGER.info("==================AVANT SENDER: " + compteSource.toString() + "==============");
            LOGGER.info("================AVANT RECEIVER: " + compteDestinataire.toString() + "==============");

            gestionnaireCompte.transfererArgent(compteSource, compteDestinataire, montant);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transfert de "+montant+" effectué avec succès de "+compteSource.getNom()+" ID du compte: "+ compteSource.getId()+" vers "+ compteDestinataire.getNom()+" ID du compte: "+ compteDestinataire.getId()+".", null);

            LOGGER.info("==================APRES SENDER: " + compteSource.toString() + "==============");
            LOGGER.info("================APRES RECEIVER: " + compteDestinataire.toString() + "==============");
        } else if (compteSource != null && montant > compteSource.getSolde()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transfert échoué, solde insuffisant.", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transfert échoué, vérifier les identifiants de comptes.", null);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * Transférer de l'argent d'un compte à un autre. Le compte source est
     * débité. Le compte destinataire est crédité.
     *
     */
    public String operation() {
        FacesMessage message = null;
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> COMPTE ID: " + String.valueOf(idCompteSource) + " >>>>>>>>>>>>>>>>>>>>>");
        CompteBancaire compteSource = gestionnaireCompte.findById(idCompteSource);
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> AVANT OP: " + compteSource.toString() + " >>>>>>>>>>>>>>>>>>>>>");
        if (typeOperation.equals(TypeOperation.DEPOT)) {
            gestionnaireCompte.depot(compteSource, montant);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dépot de "+ montant +" réussi pour "+ compteSource.getNom()+"// ID du compte: "+ compteSource.getId()+".", null);
        } else if (typeOperation.equals(TypeOperation.RETRAIT) && montant <= compteSource.getSolde()) {
            gestionnaireCompte.retrait(compteSource, montant);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Retrait de "+ montant +" réussi pour "+ compteSource.getNom()+"// ID du compte: "+ compteSource.getId()+".", null);
        } else if (typeOperation.equals(TypeOperation.RETRAIT) && montant > compteSource.getSolde()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Retrait échoué, solde insuffisant.", null);
        }
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> POST OP: " + compteSource.toString() + " >>>>>>>>>>>>>>>>>>>>>");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        return "listeComptes";
    }
}
