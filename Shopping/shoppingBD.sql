DROP DATABASE IF EXISTS shoppingBD;   
CREATE DATABASE shoppingBD;
USE shoppingBD;

-- Désactiver les vérifications des clés étrangères
SET FOREIGN_KEY_CHECKS = 0;

-- Suppression des tables
DROP TABLE IF EXISTS Admin, Client, Commande, Produit, User, HistoriqueCommande, HistoriquePanier, Journalisation, Panier;

-- Réactivation des clés étrangères
SET FOREIGN_KEY_CHECKS = 1;

-- Création des tables
CREATE TABLE User (
   idUser               INT NOT NULL,
   loginUser            VARCHAR(254) NULL,
   mdpUser              VARCHAR(254) NULL,
   mailUser             VARCHAR(254) NULL,
   statutUser           SMALLINT NULL,
   PRIMARY KEY (idUser)
);

CREATE TABLE Admin (
   idUser               INT NOT NULL,
   PRIMARY KEY (idUser)
);

CREATE TABLE Client (
   idUser               INT NOT NULL,
   dateNaissanceClient  TIMESTAMP NULL,
   dateAjoutClient      DATE NULL,
   PRIMARY KEY (idUser)
);

CREATE TABLE Commande (
   idCommande           INT NOT NULL,
   idUser               INT NULL,
   montantCommande      FLOAT NULL,
   quantiteProdCom      INT NULL,
   dateCommande         DATE NULL,
   PRIMARY KEY (idCommande)
);

CREATE TABLE Produit (
   idProduit            INT NOT NULL,
   nomProduit           VARCHAR(254) NULL,
   quantiteProduit      INT NULL,
   prixProduit          FLOAT NULL,
   marqueProduit        VARCHAR(254) NULL,
   typeProduit          SMALLINT NULL,
   categorieProduit     VARCHAR(254) NULL,
   reductionProduit     FLOAT NULL,
   dateAjoutProduit     DATE NULL,
   datePeremptionProduit TIMESTAMP NULL,
   PRIMARY KEY (idProduit)
);

CREATE TABLE Panier (
   idPanier             INT NOT NULL,
   idUser               INT NULL,
   PRIMARY KEY (idPanier)
);

CREATE TABLE HistoriqueCommande (
   idCommande           INT NOT NULL,
   idProduit            INT NOT NULL,
   PRIMARY KEY (idCommande, idProduit)
);

CREATE TABLE HistoriquePanier (
   idProduit            INT NOT NULL,
   idPanier             INT NOT NULL,
   PRIMARY KEY (idProduit, idPanier)
);

CREATE TABLE Journalisation (
   idJournalisation     INT NOT NULL,
   idUser               INT NOT NULL,
   heureDebutConnexion  DATETIME NULL,
   heureFinConnexion    DATETIME NULL,
   dureeConnexion       INT NULL,
   dateConnexion        DATE NULL,
   PRIMARY KEY (idJournalisation)
);

-- Suppression des clés étrangères avant suppression des tables
ALTER TABLE  Admin DROP FOREIGN KEY FK_ADMIN_GENERALIS_USER;
ALTER TABLE Client DROP FOREIGN KEY FK_CLIENT_GENERALIS_USER;
ALTER TABLE Commande DROP FOREIGN KEY FK_COMMANDE_ASSOCIATI_CLIENT;
ALTER TABLE HistoriqueCommande DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_COMMANDE;
ALTER TABLE HistoriqueCommande DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_PRODUIT;
ALTER TABLE HistoriquePanier DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_PANIER;
ALTER TABLE HistoriquePanier DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_PRODUIT;
ALTER TABLE Journalisation DROP FOREIGN KEY FK_JOURNALI_ASSOCIATI_USER;
ALTER TABLE Panier DROP FOREIGN KEY FK_PANIER_ASSOCIATI_CLIENT;

-- Ajout des contraintes de clé étrangère après la création des tables
ALTER TABLE Admin ADD CONSTRAINT FK_ADMIN_GENERALIS_USER FOREIGN KEY (idUser) REFERENCES User (idUser);
ALTER TABLE Client ADD CONSTRAINT FK_CLIENT_GENERALIS_USER FOREIGN KEY (idUser) REFERENCES User (idUser);
ALTER TABLE Commande ADD CONSTRAINT FK_COMMANDE_ASSOCIATI_CLIENT FOREIGN KEY (idUser) REFERENCES Client (idUser);
ALTER TABLE HistoriqueCommande ADD CONSTRAINT FK_HISTORIQ_ASSOCIATI_COMMANDE FOREIGN KEY (idCommande) REFERENCES Commande (idCommande);
ALTER TABLE HistoriqueCommande ADD CONSTRAINT FK_HISTORIQ_ASSOCIATI_PRODUIT FOREIGN KEY (idProduit) REFERENCES Produit (idProduit);
ALTER TABLE HistoriquePanier ADD CONSTRAINT FK_HISTORIQ_ASSOCIATI_PANIER FOREIGN KEY (idPanier) REFERENCES Panier (idPanier);
ALTER TABLE HistoriquePanier ADD CONSTRAINT FK_HISTORIQ_ASSOCIATI_PRODUIT FOREIGN KEY (idProduit) REFERENCES Produit (idProduit);
ALTER TABLE Journalisation ADD CONSTRAINT FK_JOURNALI_ASSOCIATI_USER FOREIGN KEY (idUser) REFERENCES User (idUser);
ALTER TABLE Panier ADD CONSTRAINT FK_PANIER_ASSOCIATI_CLIENT FOREIGN KEY (idUser) REFERENCES Client (idUser);

SET FOREIGN_KEY_CHECKS = 1;