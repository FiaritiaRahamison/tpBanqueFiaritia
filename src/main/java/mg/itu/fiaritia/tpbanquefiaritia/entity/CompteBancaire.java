/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raham
 */
@Entity
@Table(name = "comptebancaire")
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT cb FROM CompteBancaire cb"),
    @NamedQuery(name = "CompteBancaire.findById", query = "SELECT cb FROM CompteBancaire cb where cb.id=:id")})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "SOLDE")
    private int solde;

    @Version
    private int version;
    
    public Integer getId() {
        return id;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
    private List<OperationBancaire> operations = new ArrayList<>();  
     
    /*public void setId(Long id) {
        this.id = id;
        Il n'est pas nécessaire de modifier la clé d'une entité.
    }*/
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

    public List<OperationBancaire> getOperations() {  
      return operations;  
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    public CompteBancaire() {
    }

    /* Obligatoire */

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        operations.add(new OperationBancaire(TypeOperation.CREATION_DU_COMPTE, solde));
    }

    public void deposer(int montant) {
        solde += montant;
        operations.add(new OperationBancaire(TypeOperation.DEPOT, montant));
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
        operations.add(new OperationBancaire(TypeOperation.RETRAIT, -montant));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire[ id=" + id + " , nom=" + nom + ", solde= " + solde + "]";
    }

}
