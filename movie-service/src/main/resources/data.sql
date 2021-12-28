insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt0232500', 'The Fast and the Furious',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt0322259', '2 Fast 2 Furious',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt0463985', 'The Fast and the Furious: Tokyo Drift',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt1013752', 'Fast & Furious',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt1596343', 'Fast Five',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt1905041', 'Fast & Furious 6',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt2820852', 'Furious 7',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt4630562', 'The Fate of the Furious',false,CURRENT_DATE);
insert into MOVIE (id,name,is_deleted,last_updated_date) values ('tt5433138', 'F9: The Fast Saga',false,CURRENT_DATE);

insert into ROOM (id,name,is_deleted,last_updated_date,capacity) values ('0713b902f9604269b00657fa2aa69a47', 'Main Room',false,CURRENT_DATE,100);

insert into movie_show (id,room,movie,initial_date_time,final_date_time,price,duration) values ('a2ccb93dc433418f982f9c879329fa69','0713b902f9604269b00657fa2aa69a47','tt5433138','2021-12-28 00:00:00' ,'2021-12-28 02:00:00',10000,90);