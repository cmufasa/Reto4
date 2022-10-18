INSERT INTO usuario (id_usuario,password,username) VALUES(1,'$2a$10$riXLoaQw9mg.MRHP588Vu.ORr17JMINPr8UWkCv453lz8v57l/ICy','admin');
INSERT INTO usuario (id_usuario,password,username) VALUES(2,'$2a$10$NVxPSgLDQNAesg30AcpoRewkz7Xu4ub2TnUSk7sB6wpm49cCBIq8m','user');
INSERT INTO rol (id_rol,nombre,id_usuario) VALUES (1,'ROLE_ADMIN',1);
INSERT INTO rol (id_rol,nombre,id_usuario) VALUES (2,'ROLE_USER',1);
INSERT INTO rol (id_rol,nombre,id_usuario) VALUES (3,'ROLE_USER',2);
