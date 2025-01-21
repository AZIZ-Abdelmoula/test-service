--Table Salle pour gerer les salles.
INSERT INTO Salle (id,nom, capacite_Max) VALUES (1,'E1001', 23);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (2,'E1002', 10);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (3,'E1003', 8);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (4,'E2004', 4);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (5,'E2001', 4);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (6,'E2002', 15);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (7,'E2003', 7);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (8,'E2004', 9);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (9,'E3001', 13);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (10,'E3002', 8);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (11,'E3003', 9);
INSERT INTO Salle (id,nom, capacite_Max) VALUES (12,'E3004', 4);
--Table EQUIPEMENT pour gerer les equipements.
insert  into EQUIPEMENT  (id,nom, nombre_Disponible) VALUES (1,'Pieuvre',4);
insert  into EQUIPEMENT  (id,nom, nombre_Disponible) VALUES (2,'Tableau',2);
insert  into EQUIPEMENT  (id,nom, nombre_Disponible) VALUES (3,'WebCam',4);
insert  into EQUIPEMENT  (id,nom, nombre_Disponible) VALUES (4,'Ecran',5);
--Table TYPE_REUNION pour gerer les types de reunions.
insert into TYPE_REUNION (id,code,description,nbr_Collab) VALUES (1,'VC','VisioConférence',null);
insert into TYPE_REUNION (id,code,description,nbr_Collab) VALUES (2,'SPEC','Séance de partage/étude de cas',null);
insert into TYPE_REUNION (id,code,description,nbr_Collab) VALUES (3,'RS','Réunion simple',3);
insert into TYPE_REUNION (id,code,description,nbr_Collab) VALUES (4,'RC','Réunion couplée',null);

--Table de jointure entre un type de réunion et les equipements nécessaires
insert into EQUIPEMENT_TYPE_REUNION  (equipement_id,type_reunion_id) values (1,1);
insert into EQUIPEMENT_TYPE_REUNION (equipement_id,type_reunion_id) values (3,1);
insert into EQUIPEMENT_TYPE_REUNION (equipement_id,type_reunion_id) values (4,1);

insert into EQUIPEMENT_TYPE_REUNION (equipement_id,type_reunion_id) values (2,2);

insert into EQUIPEMENT_TYPE_REUNION (equipement_id,type_reunion_id) values (2,4);
insert into EQUIPEMENT_TYPE_REUNION (equipement_id,type_reunion_id) values (4,4);
insert into EQUIPEMENT_TYPE_REUNION (equipement_id,type_reunion_id) values (1,4);

--Table de jointure entre les salles et les équipements
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (2,  4);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (3,  1);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (4,  2);

INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (6,  3);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (6,  4);

INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (8,  2);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (9,  4);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (9,  3);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (9,  1);

INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (11, 4);
INSERT INTO EQUIPEMENT_SALLE (SALLE_ID ,EQUIPEMENT_ID) VALUES (11, 1);