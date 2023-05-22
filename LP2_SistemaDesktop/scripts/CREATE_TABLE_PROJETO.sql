
CREATE TABLE IF NOT EXISTS PROJETO ( 
						 	ID INTEGER NOT NULL PRIMARY KEY	AUTOINCREMENT, 
						 	NOME CHAR(100),  
						 	STATUS INTEGER, 
						 	DATA_CRIACAO	DATE NOT NULL, 
						 	DATA_CONCLUSAO DATE,
						 	ID_RESPONSAVEL INTEGER,
							CONSTRAINT FK_ID_RESPONSVEL FOREIGN KEY (ID_RESPONSAVEL)
							REFERENCES USUARIO(ID)
					 	)  