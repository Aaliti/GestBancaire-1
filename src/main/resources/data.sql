-- INSERT COMPTES
insert into compte (compte_id, nom) values (nextval('compte_seq'), 'John');
insert into compte (compte_id, nom) values (nextval('compte_seq'), 'Mike');
insert into compte (compte_id, nom) values (nextval('compte_seq'), 'Steve');
insert into compte (compte_id, nom) values (nextval('compte_seq'), 'Yassir');
insert into compte (compte_id, nom) values (nextval('compte_seq'), 'Salma');


-- INSERT OPERATIONS
insert into operation (id, montant, date, compte_id) values (nextval('operation_seq'), +1500, NOW(), 1);
insert into operation (id, montant, date, compte_id) values (nextval('operation_seq'), -500, NOW(), 1);
insert into operation (id, montant, date, compte_id) values (nextval('operation_seq'), +4000, NOW(), 2);

