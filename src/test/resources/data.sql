
insert into service(id,version, title) values(1, 1, 'title1');
insert into under_service(id,version, base_Price, details,service_id) values (1, 1, 100, 'details1',1);
insert into application_user (id,deleted,image,version,firstname, lastname, email, password, created_At, credit, status, role, score) values (1,false,null ,0,'John', 'Doe', 'mahdi@gmail.com','123', '2019-01-01', 100, 'NEW', 'CUSTOMER', 100);

insert into offer(id,version, period_of_time, proposed_price, created_at, start_time, order_id, user_id)
values (112,1, '2020-01-01', 100, '2020-01-01', '2020-01-01', 1, 1);

insert into orders(id,version,proposed_Price,created_At,address,word_Time,under_service_id,status,user_id,offer_id)
values (1,1,100,'2020-01-01','address','2020-01-01',1,'WAITING_FOR_THE_OFFER',1 ,null );