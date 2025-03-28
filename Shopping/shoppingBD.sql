/*==============================================================*/
/* Nom de SGBD :  Sybase SQL Anywhere 11                        */
/* Date de création :  28/03/2025 09:55:07                      */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_ADMIN_GENERALIS_USER') then
    alter table Admin
       delete foreign key FK_ADMIN_GENERALIS_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_CLIENT_GENERALIS_USER') then
    alter table Client
       delete foreign key FK_CLIENT_GENERALIS_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMANDE_ASSOCIATI_CLIENT') then
    alter table Commande
       delete foreign key FK_COMMANDE_ASSOCIATI_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIQ_ASSOCIATI_COMMANDE') then
    alter table HistoriqueCommande
       delete foreign key FK_HISTORIQ_ASSOCIATI_COMMANDE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIQ_ASSOCIATI_PRODUIT') then
    alter table HistoriqueCommande
       delete foreign key FK_HISTORIQ_ASSOCIATI_PRODUIT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIQ_ASSOCIATI_PANIER') then
    alter table HistoriquePanier
       delete foreign key FK_HISTORIQ_ASSOCIATI_PANIER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIQ_ASSOCIATI_PRODUIT') then
    alter table HistoriquePanier
       delete foreign key FK_HISTORIQ_ASSOCIATI_PRODUIT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_JOURNALI_ASSOCIATI_USER') then
    alter table Journalisation
       delete foreign key FK_JOURNALI_ASSOCIATI_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PANIER_ASSOCIATI_CLIENT') then
    alter table Panier
       delete foreign key FK_PANIER_ASSOCIATI_CLIENT
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERALISATION_1_FK'
     and t.table_name='Admin'
) then
   drop index Admin.GENERALISATION_1_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ADMIN_PK'
     and t.table_name='Admin'
) then
   drop index Admin.ADMIN_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Admin'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Admin
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERALISATION_2_FK'
     and t.table_name='Client'
) then
   drop index Client.GENERALISATION_2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='CLIENT_PK'
     and t.table_name='Client'
) then
   drop index Client.CLIENT_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Client'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Client
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION2_FK'
     and t.table_name='Commande'
) then
   drop index Commande.ASSOCIATION2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='COMMANDE_PK'
     and t.table_name='Commande'
) then
   drop index Commande.COMMANDE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Commande'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Commande
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION3_FK2'
     and t.table_name='HistoriqueCommande'
) then
   drop index HistoriqueCommande.ASSOCIATION3_FK2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION3_FK'
     and t.table_name='HistoriqueCommande'
) then
   drop index HistoriqueCommande.ASSOCIATION3_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION3_PK'
     and t.table_name='HistoriqueCommande'
) then
   drop index HistoriqueCommande.ASSOCIATION3_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='HistoriqueCommande'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table HistoriqueCommande
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION5_FK2'
     and t.table_name='HistoriquePanier'
) then
   drop index HistoriquePanier.ASSOCIATION5_FK2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION5_FK'
     and t.table_name='HistoriquePanier'
) then
   drop index HistoriquePanier.ASSOCIATION5_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION5_PK'
     and t.table_name='HistoriquePanier'
) then
   drop index HistoriquePanier.ASSOCIATION5_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='HistoriquePanier'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table HistoriquePanier
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION1_FK'
     and t.table_name='Journalisation'
) then
   drop index Journalisation.ASSOCIATION1_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='JOURNALISATION_PK'
     and t.table_name='Journalisation'
) then
   drop index Journalisation.JOURNALISATION_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Journalisation'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Journalisation
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION4_FK'
     and t.table_name='Panier'
) then
   drop index Panier.ASSOCIATION4_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='PANIER_PK'
     and t.table_name='Panier'
) then
   drop index Panier.PANIER_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Panier'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Panier
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='PRODUIT_PK'
     and t.table_name='Produit'
) then
   drop index Produit.PRODUIT_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Produit'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Produit
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='USER_PK'
     and t.table_name='User'
) then
   drop index "User".USER_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='User'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table "User"
end if;

/*==============================================================*/
/* Table : Admin                                                */
/*==============================================================*/
create table Admin 
(
   idUser               integer                        not null,
   constraint PK_ADMIN primary key (idUser)
);

/*==============================================================*/
/* Index : ADMIN_PK                                             */
/*==============================================================*/
create unique index ADMIN_PK on Admin (
idUser ASC
);

/*==============================================================*/
/* Index : GENERALISATION_1_FK                                  */
/*==============================================================*/
create index GENERALISATION_1_FK on Admin (
idUser ASC
);

/*==============================================================*/
/* Table : Client                                               */
/*==============================================================*/
create table Client 
(
   idUser               integer                        not null,
   dateNaissanceClient  timestamp                      null,
   dateAjoutClient      java.time.LocalDate            null,
   constraint PK_CLIENT primary key (idUser)
);

/*==============================================================*/
/* Index : CLIENT_PK                                            */
/*==============================================================*/
create unique index CLIENT_PK on Client (
idUser ASC
);

/*==============================================================*/
/* Index : GENERALISATION_2_FK                                  */
/*==============================================================*/
create index GENERALISATION_2_FK on Client (
idUser ASC
);

/*==============================================================*/
/* Table : Commande                                             */
/*==============================================================*/
create table Commande 
(
   idCommande           integer                        not null,
   idUser               integer                        null,
   montantCommande      float                          null,
   quantiteProdCom      integer                        null,
   dateCommande         java.time.LocalDate            null,
   constraint PK_COMMANDE primary key (idCommande)
);

/*==============================================================*/
/* Index : COMMANDE_PK                                          */
/*==============================================================*/
create unique index COMMANDE_PK on Commande (
idCommande ASC
);

/*==============================================================*/
/* Index : ASSOCIATION2_FK                                      */
/*==============================================================*/
create index ASSOCIATION2_FK on Commande (
idUser ASC
);

/*==============================================================*/
/* Table : HistoriqueCommande                                   */
/*==============================================================*/
create table HistoriqueCommande 
(
   idCommande           integer                        not null,
   idProduit            integer                        not null,
   constraint PK_HISTORIQUECOMMANDE primary key clustered (idCommande, idProduit)
);

/*==============================================================*/
/* Index : ASSOCIATION3_PK                                      */
/*==============================================================*/
create unique clustered index ASSOCIATION3_PK on HistoriqueCommande (
idCommande ASC,
idProduit ASC
);

/*==============================================================*/
/* Index : ASSOCIATION3_FK                                      */
/*==============================================================*/
create index ASSOCIATION3_FK on HistoriqueCommande (
idCommande ASC
);

/*==============================================================*/
/* Index : ASSOCIATION3_FK2                                     */
/*==============================================================*/
create index ASSOCIATION3_FK2 on HistoriqueCommande (
idProduit ASC
);

/*==============================================================*/
/* Table : HistoriquePanier                                     */
/*==============================================================*/
create table HistoriquePanier 
(
   idProduit            integer                        not null,
   idPanier             integer                        not null,
   constraint PK_HISTORIQUEPANIER primary key clustered (idProduit, idPanier)
);

/*==============================================================*/
/* Index : ASSOCIATION5_PK                                      */
/*==============================================================*/
create unique clustered index ASSOCIATION5_PK on HistoriquePanier (
idProduit ASC,
idPanier ASC
);

/*==============================================================*/
/* Index : ASSOCIATION5_FK                                      */
/*==============================================================*/
create index ASSOCIATION5_FK on HistoriquePanier (
idProduit ASC
);

/*==============================================================*/
/* Index : ASSOCIATION5_FK2                                     */
/*==============================================================*/
create index ASSOCIATION5_FK2 on HistoriquePanier (
idPanier ASC
);

/*==============================================================*/
/* Table : Journalisation                                       */
/*==============================================================*/
create table Journalisation 
(
   idJournalisation     integer                        not null,
   idUser               integer                        not null,
   heureDebutConnexion  java.time.LocalDateTime        null,
   heureFinConnexion    java.time.LocalDateTime        null,
   dureeConnexion       integer                        null,
   dateConnexion        java.time.LocalDate            null,
   constraint PK_JOURNALISATION primary key (idJournalisation)
);

/*==============================================================*/
/* Index : JOURNALISATION_PK                                    */
/*==============================================================*/
create unique index JOURNALISATION_PK on Journalisation (
idJournalisation ASC
);

/*==============================================================*/
/* Index : ASSOCIATION1_FK                                      */
/*==============================================================*/
create index ASSOCIATION1_FK on Journalisation (
idUser ASC
);

/*==============================================================*/
/* Table : Panier                                               */
/*==============================================================*/
create table Panier 
(
   idPanier             integer                        not null,
   idUser               integer                        null,
   quantiteProdPanier   integer                        null,
   montantPanier        float                          null,
   datePanier           java.time.LocalDate            null,
   constraint PK_PANIER primary key (idPanier)
);

/*==============================================================*/
/* Index : PANIER_PK                                            */
/*==============================================================*/
create unique index PANIER_PK on Panier (
idPanier ASC
);

/*==============================================================*/
/* Index : ASSOCIATION4_FK                                      */
/*==============================================================*/
create index ASSOCIATION4_FK on Panier (
idUser ASC
);

/*==============================================================*/
/* Table : Produit                                              */
/*==============================================================*/
create table Produit 
(
   idProduit            integer                        not null,
   nomProduit           varchar(254)                   null,
   quantiteProduit      integer                        null,
   prixProduit          float                          null,
   marqueProduit        varchar(254)                   null,
   typeProduit          smallint                       null,
   categorieProduit     varchar(254)                   null,
   reductionProduit     float                          null,
   dateAjoutProduit     java.time.LocalDate            null,
   datePeremptionProduit timestamp                      null,
   constraint PK_PRODUIT primary key (idProduit)
);

/*==============================================================*/
/* Index : PRODUIT_PK                                           */
/*==============================================================*/
create unique index PRODUIT_PK on Produit (
idProduit ASC
);

/*==============================================================*/
/* Table : "User"                                               */
/*==============================================================*/
create table "User" 
(
   idUser               integer                        not null,
   loginUser            varchar(254)                   null,
   mdpUser              varchar(254)                   null,
   mailUser             varchar(254)                   null,
   statutUser           smallint                       null,
   constraint PK_USER primary key (idUser)
);

/*==============================================================*/
/* Index : USER_PK                                              */
/*==============================================================*/
create unique index USER_PK on "User" (
idUser ASC
);

alter table Admin
   add constraint FK_ADMIN_GENERALIS_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table Client
   add constraint FK_CLIENT_GENERALIS_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table Commande
   add constraint FK_COMMANDE_ASSOCIATI_CLIENT foreign key (idUser)
      references Client (idUser)
      on update restrict
      on delete restrict;

alter table HistoriqueCommande
   add constraint FK_HISTORIQ_ASSOCIATI_COMMANDE foreign key (idCommande)
      references Commande (idCommande)
      on update restrict
      on delete restrict;

alter table HistoriqueCommande
   add constraint FK_HISTORIQ_ASSOCIATI_PRODUIT foreign key (idProduit)
      references Produit (idProduit)
      on update restrict
      on delete restrict;

alter table HistoriquePanier
   add constraint FK_HISTORIQ_ASSOCIATI_PANIER foreign key (idPanier)
      references Panier (idPanier)
      on update restrict
      on delete restrict;

alter table HistoriquePanier
   add constraint FK_HISTORIQ_ASSOCIATI_PRODUIT foreign key (idProduit)
      references Produit (idProduit)
      on update restrict
      on delete restrict;

alter table Journalisation
   add constraint FK_JOURNALI_ASSOCIATI_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table Panier
   add constraint FK_PANIER_ASSOCIATI_CLIENT foreign key (idUser)
      references Client (idUser)
      on update restrict
      on delete restrict;

