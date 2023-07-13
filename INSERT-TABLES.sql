INSERT INTO adress (cep, place) VALUES(40000000,"Rua das Flores");
INSERT INTO category(id,name) VALUES (NULL,"Eletronico");
INSERT INTO consumer(id,cpf,dateCreate,email,name,password,phone,adress_cep) VALUES(NULL,"123.456.789-10",now(),"mark.zuckenberg@facebook.com","Mark","123456","(11)3495-4753",40000000);
INSERT INTO product (id,name,price,stock,url,category_id) VALUES (NULL,"Notebook",1550.0,5,"https://a-static.mlcdn.com.br/800x560/notebook-samsung-book-intel-celeron-4gb-256gb-ssd-156-full-hd-windows-11-np550xda-kp3br/magazineluiza/233394300/a8dcd2d4e938a2b9886dd586531c92a8.jpg",1);
INSERT INTO orders (id,situation,total,consumer_id) VALUES (NULL,0,1550.0,1);
INSERT INTO product_orders (orders_id,product_id) VALUES(1,1);