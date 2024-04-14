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

/**
 *  * Backing bean de la page compteBancaireDetails.xhtml.
 *
 * @author raham
 */
@Named(value = "compteBancaireDetailsBean")
@RequestScoped
public class CompteBancaireDetailsBean implements Serializable {

    private int id;
    private CompteBancaire compteBancaire;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne les détails du compte courant (contenu dans l'attribut compteBancaire
     * de cette classe).
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
     * @return String
     */
    public String afficher() {
        return "compteBancaireDetails?id="+id+"&faces-redirect=true";
    }
    
    /**
     * Creates a new instance of CustomerDetailsBean
     */
    public CompteBancaireDetailsBean() {
    }

}
