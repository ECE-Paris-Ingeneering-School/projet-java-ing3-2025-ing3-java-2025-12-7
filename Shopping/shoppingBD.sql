-- DROP DATABASE IF EXISTS shoppingBD;
-- CREATE DATABASE shoppingBD;
-- USE shoppingBD;
--
-- -- Désactiver les vérifications des clés étrangères
-- -- SET FOREIGN_KEY_CHECKS = 0;

-- Suppression des tables
DROP TABLE IF EXISTS Admin, Client, Commande, Produit, User, HistoriqueCommande, HistoriquePanier, Journalisation, Panier;

-- Réactivation des clés étrangères
-- SET FOREIGN_KEY_CHECKS = 1;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Création des tables
CREATE TABLE User (
    idUser          INT NOT NULL,
    mdpUser         VARCHAR(254) NULL,
    mailUser        VARCHAR(254) NULL,
    statutUser      SMALLINT NULL,
    nomUser         VARCHAR(100) NULL,
    prenomUser      VARCHAR(100) NULL,
    PRIMARY KEY (idUser)
)ENGINE=MyISAM;

CREATE TABLE Admin (
   idUser               INT NOT NULL,
   PRIMARY KEY (idUser)
)ENGINE=MyISAM;

CREATE TABLE Client (
   idUser               INT NOT NULL,
   dateNaissanceClient  TIMESTAMP NULL,
   dateAjoutClient      DATE NULL,
   adresseClient     TEXT NULL,
   PRIMARY KEY (idUser)
)ENGINE=MyISAM;

CREATE TABLE Commande (
   idCommande           INT NOT NULL AUTO_INCREMENT,
   idUser               INT NULL,
   montantCommande      FLOAT NULL,
   produitsCommandes    VARCHAR (254) NULL,
   quantiteProdCom      INT NULL,
   dateCommande         DATE NULL,
   PRIMARY KEY (idCommande)
)ENGINE=MyISAM;

CREATE TABLE Produit (
   idProduit            INT NOT NULL AUTO_INCREMENT,
   nomProduit           VARCHAR(254) NULL,
   quantiteProduit      INT NULL,
   prixProduit          FLOAT NULL,
   marqueProduit        VARCHAR(254) NULL,
   typeProduit          VARCHAR(254) NULL,
   categorieProduit     VARCHAR(254) NULL,
   reductionProduit     FLOAT NULL,
   dateAjoutProduit     DATE NULL,
   photoProduit         VARCHAR(254) NULL,
   datePeremptionProduit TIMESTAMP NULL,
   PRIMARY KEY (idProduit)
)ENGINE=MyISAM;

CREATE TABLE Panier (
   idPanier             INT NOT NULL AUTO_INCREMENT,
   idUser               INT NULL,
   listeIDProduits      VARCHAR(254)NULL,
   listeQuantite        VARCHAR(254)NULL,
   montantPanier        FLOAT NULL,
   -- datePanier           TIMESTAMP NULL,
   PRIMARY KEY (idPanier)
)ENGINE=MyISAM;

CREATE TABLE HistoriqueCommande (
   idCommande           INT NOT NULL,
   idProduit            INT NOT NULL,
   PRIMARY KEY (idCommande, idProduit)
)ENGINE=MyISAM;

CREATE TABLE Journalisation (
   idJournalisation     INT NOT NULL,
   idUser               INT NOT NULL,
   heureDebutConnexion  DATETIME NULL,
   heureFinConnexion    DATETIME NULL,
   dureeConnexion       INT NULL,
   dateConnexion        DATE NULL,
   PRIMARY KEY (idJournalisation)
)ENGINE=MyISAM;


-- Chargement des données de la table `panier`

INSERT INTO `panier` (idPanier, idUser, listeIDProduits, listeQuantite, montantPanier)  VALUES
    (1,2,'2,5,35', '2,1,1',10.27),
    (2,4,'12,44', '8,1',30.55);

-- Insertion des utilisateurs (10 utilisateurs)
INSERT INTO User (idUser, mdpUser, mailUser, statutUser, nomUser, prenomUser) VALUES
    (1,  'mdp1', 'client1@example.com', 0, 'Dupont', 'Jean'),
    (2,  'mdp2', 'client2@example.com', 0, 'Martin', 'Sophie'),
    (3,  'mdp3', 'admin1@example.com', 1, 'Bernard', 'Pierre'),
    (4,  'mdp4', 'client4@example.com', 0, 'Thomas', 'Julie'),
    (5,  'mdp5', 'admin2@example.com', 1, 'Petit', 'Luc'),
    (6,  'mdp6', 'client6@example.com', 0, 'Robert', 'Anne'),
    (7,  'mdp7', 'client7@example.com', 0, 'Richard', 'Paul'),
    (8,  'mdp8', 'admin3@example.com', 1, 'Durand', 'Camille'),
    (9,  'mdp9', 'client9@example.com', 0, 'Dubois', 'Thomas'),
    (10,  'mdp10', 'admin4@example.com', 1, 'Moreau', 'Élise');

-- Insertion des clients correspondants (10 clients)
INSERT INTO Client (idUser, dateNaissanceClient, dateAjoutClient, adresseClient) VALUES
    (1, '1990-01-15 00:00:00', '2023-01-10', '123 Rue des Biscuits, 75000 Paris'),
    (2, '1985-05-22 00:00:00', '2023-02-15', '456 Avenue des Boissons, 69000 Lyon'),
    (4, '1988-07-04 00:00:00', '2023-04-05', '321 Rue des Confiseries, 31000 Toulouse'),
    (6, '1983-09-25 00:00:00', '2023-06-18', '987 Chemin des Saveurs, 06000 Nice'),
    (7, '1998-12-08 00:00:00', '2023-07-22', '159 Route des Délices, 59000 Lille'),
    (9, '1993-06-27 00:00:00', '2023-09-05', '951 Avenue des Gourmets, 44000 Nantes');

-- Chargement des données de la table `produit`
--

INSERT INTO `produit` (`idProduit`, `nomProduit`, `quantiteProduit`, `prixProduit`, `marqueProduit`, `typeProduit`, `categorieProduit`, `reductionProduit`, `dateAjoutProduit`, `datePeremptionProduit`, `photoProduit`) VALUES
     (1, 'FIZZ cassis basilic menthe', 10, 2.99, 'FIZZ', 'Boisson', 'Limonade', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_CASSIS_BASILIC_MENTHE.png'),
     (2, 'FIZZ citron', 15, 1.99, 'FIZZ', 'Boisson', 'Limonade', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_CITRON.png'),
     (3, 'FIZZ cola', 20, 2.5, 'FIZZ', 'Boisson', 'Limonade', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_COLA.png'),
     (4, 'FIZZ Mojito', 12, 3.5, 'FIZZ', 'Boisson', 'Limonade', 0.15, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_MOJITO.png'),
     (5, 'FIZZ Orange', 18, 2.5, 'FIZZ', 'Boisson', 'Limonade', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_ORANGE.png'),
     (6, 'FIZZ Pomme lavande', 11, 2.99, 'FIZZ', 'Boisson', 'Limonade', 0.10, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_POMME_LAVANDE.png'),
     (7, 'FIZZ Tropical', 14, 2.99, 'FIZZ', 'Boisson', 'Limonade', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_TROPICAL.png'),
     (8, 'FIZZ verveine citron vert', 16, 2.99, 'FIZZ', 'Boisson', 'Limonade', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/FIZZ_VERVEINE_CITRON_VERT.png'),
     (9, 'Nectar de Fruit de la Passion', 19, 3.99, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.15, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/NECTAR_FRUIT_PASSION.png'),
     (10, 'Nectar de Mangue', 10, 3.99, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/NECTAR_MANGUE.png'),
     (11, 'Nectar de Pêche de Vigne', 18, 3.5, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/NECTAR_PECHE_VIGNE.png'),
     (12, 'Nectar de Poire', 15, 3, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/NECTAR_POIRE.png'),
     (13, 'Nectar de Fraise', 20, 3.5, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/NECTAR_FRAISE.png'),
     (14, 'Nectar d Abricot', 13, 3.5, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/NECTAR_ABRICOT.png'),
     (15, 'Jus de Tomate', 16, 2.99, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/JUS_TOMATE.png'),
     (16, 'Jus de Raisin Rouge', 14, 3.5, 'Meneau', 'Boisson', 'Jus de fruit', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/JUS_RAISIN_ROUGE.png'),
     (17, 'Jus de Raisin Blanc', 17, 3.5, 'Meneau', 'Boisson', 'Jus de fruit', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/JUS_RAISIN_BLANC.png'),
     (18, 'Jus de Pomme', 12, 2.5, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/JUS_POMME.png'),
     (19, 'Jus d Orange', 18, 2.5, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/JUS_ORANGE.png'),
     (20, 'Jus d Ananas', 15, 3, 'Alain Milliat', 'Boisson', 'Jus de fruit', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/JUS_ANANAS.png'),
     (21, 'Sirop de Citron', 15, 4, 'Meneau', 'Boisson', 'Sirop', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/SIROP_CITRON.png'),
     (22, 'Sirop de Fraise', 12, 4.5, 'Meneau', 'Boisson', 'Sirop', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/SIROP_FRAISE.png'),
     (23, 'Sirop de Myrtille', 18, 4.99, 'Meneau', 'Boisson', 'Sirop', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/SIROP_MYRTILLE.png'),
     (24, 'Thé Glacé Citron Thym', 16, 3.99, 'Meneau', 'Boisson', 'Thé glacé', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/THE_GLACE_CITRON_THYM.png'),
     (25, 'Thé Glacé Grenade', 14, 4, 'Meneau', 'Boisson', 'Thé glacé', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/THE_GLACE_GRENADE.png'),
     (26, 'Thé Glacé Pêche', 17, 3.99, 'Meneau', 'Boisson', 'Thé glacé', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/THE_GLACE_PECHE.png'),
     (27, 'Thé Glacé Citron vert Gingembre', 18, 4.5, 'Alain Milliat', 'Boisson', 'Thé glacé', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/THE_GLACE_CITRON_VERT_GINGEMBRE.png'),
     (28, 'Thé Glacé Framboise Menthe', 12, 4.99, 'Alain Milliat', 'Boisson', 'Thé glacé', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/THE_GLACE_FRAMBOISE_MENTHE.png'),
     (29, 'Thé Glacé Mangue Passion', 15, 4.99, 'Alain Milliat', 'Boisson', 'Thé glacé', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/THE_GLACE_MANGUE_PASSION.png'),
     (30, 'Citron Gingembre', 16, 3.99, 'Buddy', 'Boisson', 'Boisson énergisante', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/BUDDY_GINGEMBRE_CITRON.png'),
     (31, 'Grenade Hibiscus', 14, 4, 'Buddy', 'Boisson', 'Boisson énergisante', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/BUDDY_GRENADE_HIBISCUS.png'),
     (32, 'Mangue Passion', 18, 4.5, 'Buddy', 'Boisson', 'Boisson énergisante', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/BUDDY_MANGUE_PASSION.png'),
     (33, 'Myrtille Cannelle', 10, 4, 'Ossa', 'Boisson', 'Boisson énergisante', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/OSSA_MYRTILLE_CANNELLE.png'),
     (34, 'Fleur de Sureau Pomme', 13, 4.5, 'Ossa', 'Boisson', 'Boisson énergisante', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/OSSA_FLEUR_DE_SUREAU_POMME.png'),
     (35, 'Sirop de Grenadine', 17, 3.99, 'Monin', 'Boisson', 'Sirop', 0.05, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/SIROP_GRENADINE.png'),
     (36, 'Sirop de Menthe', 16, 3.99, 'Monin', 'Boisson', 'Sirop', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/SIROP_MENTHE.png'),
     (37, 'Sirop de Pêche', 19, 4, 'Monin', 'Boisson', 'Sirop', 0.1, '2025-04-03', '2026-04-02 22:00:00', 'PHOTOS/SIROP_PECHE.png'),
     (38, 'Cookies Caramel', 28, 8.99, 'Fauchon', 'Biscuit', 'Cookies', 0.05, '2025-04-12', '2025-09-30 22:00:00', 'PHOTOS/FAUCHON_COOKIES_CARAMEL.png'),
     (39, 'Cookies Chocolat Noisette', 22, 8.99, 'Fauchon', 'Biscuit', 'Cookies', 0.01, '2025-04-12', '2025-09-29 22:00:00', 'PHOTOS/FAUCHON_COOKIES_CHOCOLAT_NOISETTE.png'),
     (40, 'Cookies Fruits rouges', 27, 8.99, 'Fauchon', 'Biscuit', 'Cookies', 0.05, '2025-04-12', '2025-09-04 22:00:00', 'PHOTOS/FAUCHON_COOKIES_FRUITS_ROUGES.png'),
     (41, 'Biscuits Framboise', 26, 6.99, 'Fauchon', 'Biscuit', 'Biscuits', 0.10, '2025-04-12', '2025-08-14 22:00:00', 'PHOTOS/FAUCHON_FRAMBOISE.png'),
     (42, 'Biscuits Vanille', 24, 6.99, 'Fauchon', 'Biscuit', 'Biscuits', 0.15, '2025-04-12', '2025-09-11 22:00:00', 'PHOTOS/FAUCHON_VANILLE.png'),
     (43, 'Madeleines Chocolat', 28, 7.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.10, '2025-04-12', '2025-07-31 22:00:00', 'PHOTOS/MADELEINE_CHOCOLAT_CLASSIQUE.png'),
     (44, 'Madeleines Chocolat au lait', 22, 7.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.15, '2025-04-12', '2025-08-09 22:00:00', 'PHOTOS/MADELEINE_CHOCOLAT_LAIT.png'),
     (45, 'Madeleines Chocolat Noisette', 23, 7.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.10, '2025-04-12', '2025-08-31 22:00:00', 'PHOTOS/MADELEINE_CHOCOLAT_NOISETTE.png'),
     (46, 'Madeleines Chocolat Noix de coco', 27, 8.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.05, '2025-04-12', '2025-08-11 22:00:00', 'PHOTOS/MADELEINE_CHOCOLAT_NOIX_DE_COCO.png'),
     (47, 'Madeleines Nature', 20, 7.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.10, '2025-04-12', '2025-07-27 22:00:00', 'PHOTOS/MADELEINE_NATURE.png'),
     (48, 'Madeleines Pistache', 26, 8.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.10, '2025-04-12', '2025-08-31 22:00:00', 'PHOTOS/MADELEINE_PISTACHE.png'),
     (49, 'Madeleines Tout Chocolat', 24, 8.99, 'Maison Colibri', 'Biscuit', 'Madeleines', 0.15, '2025-04-12', '2025-09-30 22:00:00', 'PHOTOS/MADELEINE_TOUT_CHOCOLAT.png'),
     (50, 'Sablés Nature', 30, 5.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.10, '2025-04-12', '2025-08-04 22:00:00', 'PHOTOS/SABLE_NATURE.png'),
     (51, 'Sablés Pépites d Abricots', 28, 6.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.15, '2025-04-12', '2025-08-31 22:00:00', 'PHOTOS/SABLES_PEPITES_ABRICOTS.png'),
     (52, 'Sablés Pépites de Caramel', 22, 5.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.10, '2025-04-12', '2025-08-14 22:00:00', 'PHOTOS/SABLES_PEPITES_CARAMEL.png'),
     (53, 'Sablés Pépites de Chocolat', 25, 5.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.05, '2025-04-12', '2025-09-09 22:00:00', 'PHOTOS/SABLES_PEPITES_CHOCOLAT.png'),
     (54, 'Sablés Pépites de Citron', 24, 6.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.10, '2025-04-12', '2025-08-24 22:00:00', 'PHOTOS/SABLES_PEPITES_CITRON.png'),
     (55, 'Sablés Pépites de Framboise', 27, 6.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.10, '2025-04-12', '2025-09-04 22:00:00', 'PHOTOS/SABLES_PEPITES_FRAMBOISE.png'),
     (56, 'Sablés Tout Chocolat', 30, 6.99, 'La Sablésienne', 'Biscuit', 'Sablés', 0.15, '2025-04-12', '2025-09-29 22:00:00', 'PHOTOS/SABLES_TOUT_CHOCOLAT.png'),
     (57, 'Cookies Agrumes', 20, 8.99, 'Fauchon', 'Biscuit', 'Cookies', 0.1, '2025-04-12', '2025-09-09 22:00:00', 'PHOTOS/FAUCHON_COOKIES_AGRUMES.png'),
     (58, 'Biscuits Celine Citron', 25, 8.99, 'Generous', 'Biscuit', 'Biscuits', 0.10, '2025-04-12', '2025-09-11 22:00:00', 'PHOTOS/CELINE_CITRON.png'),
     (59, 'Biscuits Charlotte Chocolat', 28, 8.99, 'Generous', 'Biscuit', 'Biscuits', 0.15, '2025-04-12', '2025-09-30 22:00:00', 'PHOTOS/CHARLOTTE_CHOCOLAT.png'),
     (60, 'Biscuits Colette Coco', 27, 9.99, 'Generous', 'Biscuit', 'Biscuits', 0.05, '2025-04-12', '2025-08-19 22:00:00', 'PHOTOS/COLETTE_COCO.png'),
     (61, 'Biscuits Nicole Noisette', 22, 8.99, 'Generous', 'Biscuit', 'Biscuits', 0.10, '2025-04-12', '2025-09-14 22:00:00', 'PHOTOS/NICOLE_NOISETTE.png'),
     (62, 'Biscuits Sylvain Speculoos', 30, 9.99, 'Generous', 'Biscuit', 'Biscuits', 0.05, '2025-04-12', '2025-10-04 22:00:00', 'PHOTOS/SYLVAIN_SPECULOOS.png'),
     (63, 'Biscuits Victor Vanille', 24, 9.99, 'Generous', 'Biscuit', 'Biscuits', 0.10, '2025-04-12', '2025-09-24 22:00:00', 'PHOTOS/VICTOR_VANILLE.png'),
     (64, 'Cookies Beurre de cacahuètes', 26, 5.99, 'KoRo', 'Biscuit', 'Cookies', 0.20, '2025-04-12', '2025-11-11 23:00:00', 'PHOTOS/COOKIES_BEURRE_CACAHUETES.png'),
     (65, 'Gaufrettes Cannelle', 23, 5.99, 'KoRo', 'Biscuit', 'Gaufrettes', 0.15, '2025-04-12', '2025-09-17 22:00:00', 'PHOTOS/GAUFRETTES_CANELLE.png'),
     (66, 'Biscuits Caramel', 25, 6.99, 'Fauchon', 'Biscuit', 'Biscuits', 0.10, '2025-04-12', '2025-08-24 22:00:00', 'PHOTOS/FAUCHON_CARAMEL.png');


-- -- Suppression des clés étrangères avant suppression des tables
-- ALTER TABLE  Admin DROP FOREIGN KEY FK_ADMIN_GENERALIS_USER;
-- ALTER TABLE Client DROP FOREIGN KEY FK_CLIENT_GENERALIS_USER;
-- ALTER TABLE Commande DROP FOREIGN KEY FK_COMMANDE_ASSOCIATI_CLIENT;
-- ALTER TABLE HistoriqueCommande DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_COMMANDE;
-- ALTER TABLE HistoriqueCommande DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_PRODUIT;
-- ALTER TABLE HistoriquePanier DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_PANIER;
-- ALTER TABLE HistoriquePanier DROP FOREIGN KEY FK_HISTORIQ_ASSOCIATI_PRODUIT;
-- ALTER TABLE Journalisation DROP FOREIGN KEY FK_JOURNALI_ASSOCIATI_USER;
-- ALTER TABLE Panier DROP FOREIGN KEY FK_PANIER_ASSOCIATI_CLIENT;

-- Ajout des contraintes de clé étrangère après la création des tables
ALTER TABLE Admin ADD CONSTRAINT FK_ADMIN_GENERALIS_USER FOREIGN KEY (idUser) REFERENCES User (idUser);
ALTER TABLE Client ADD CONSTRAINT FK_CLIENT_GENERALIS_USER FOREIGN KEY (idUser) REFERENCES User (idUser);
ALTER TABLE Commande ADD CONSTRAINT FK_COMMANDE_ASSOCIATI_CLIENT FOREIGN KEY (idUser) REFERENCES Client (idUser);
ALTER TABLE HistoriqueCommande ADD CONSTRAINT FK_HISTORIQ_ASSOCIATI_COMMANDE FOREIGN KEY (idCommande) REFERENCES Commande (idCommande);
ALTER TABLE HistoriqueCommande ADD CONSTRAINT FK_HISTORIQ_ASSOCIATI_PRODUIT FOREIGN KEY (idProduit) REFERENCES Produit (idProduit);
ALTER TABLE Journalisation ADD CONSTRAINT FK_JOURNALI_ASSOCIATI_USER FOREIGN KEY (idUser) REFERENCES User (idUser);
ALTER TABLE Panier ADD CONSTRAINT FK_PANIER_ASSOCIATI_CLIENT FOREIGN KEY (idUser) REFERENCES Client (idUser);
ALTER TABLE User MODIFY idUser INT NOT NULL AUTO_INCREMENT;

-- SET FOREIGN_KEY_CHECKS = 1;