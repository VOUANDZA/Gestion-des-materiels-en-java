drop database if exists gestion_r;
create database gestion_r;
	use gestion_r;

	
create table user(
	iduser int(3) not null auto_increment,
	nom varchar(50),
	prenom varchar(50),
	email varchar(50),
	mdp varchar(255),
	role enum("admin","user"),
	primary key(iduser)
);


create table client (
    id_client int(3) not null auto_increment,
    nom varchar(200),
    prenom varchar(200),
    adresse varchar(200),
    cp varchar(6),
    tel varchar(12),
    primary key (id_client)
		);
		
		
		create table technicien(
    id_tech int (3) not null auto_increment,
    nom varchar (50),
    prenom varchar(50),
    adresse varchar(60),
    cp varchar(5),
    diplome varchar(50),
    primary key (id_tech)
		);
		
		
		 create table materiels(
    id_materiels int(3) not null auto_increment,
    nom varchar (50) ,
    marque varchar(50),
    poids varchar(10),
    capacite varchar(10),
    taille varchar(10),
    primary key (id_materiels)
    );
    
    create table location (
	idloc int(3) not null auto_increment, 
	designation varchar(50) not null, 
	dateloc date,
	heureloc time, 
	id_client1 int(3) not null, 
	id_materiels int(3) not null, 
	primary key (idloc), 
	foreign key(id_client1) references client(id_client), 
	foreign key(id_materiels) references materiels(id_materiels)
);


Create or replace view CML as (
    select p.nom, p.prenom, a.marque as materiels, v.designation as location, v.dateloc, v.heureloc
    from client p, materiels a, location v
    where p.id_client = v.id_client1
    and a.id_materiels = v.idloc
);
    

insert into user values (null, "VOUANDZA","Cedric","a@gmail.com","Ced@123","admin"),(null,"maamar","lassana","b@gmail.com","Lass@456","user"),(null,"Dupond","cedric","cedric@gmail.com","Cedric@123","user");
