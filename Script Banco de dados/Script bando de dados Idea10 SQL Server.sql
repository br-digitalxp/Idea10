CREATE TABLE tb_usuario(
 
	id_usuario INT IDENTITY(1,1) PRIMARY KEY NOT NULL
	ds_login   VARCHAR(30) NOT NULL 
	ds_senha   VARCHAR(30) NOT NULL 
 
);


CREATE TABLE tb_cliente(

    id_cliente          INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    nm_cliente          VARCHAR(70)  NOT NULL,
    ds_cpf	        	VARCHAR(15)	 NOT NULL,
    dt_cadastro         DATETIME     NOT NULL,
    ds_email	        VARCHAR(80)  NOT NULL, 
    ds_endereco         VARCHAR(200)     NULL, 
    ds_telefone         VARCHAR(15)	 NOT NULL,
    id_usuario_cadastro	INT	         NOT NULL 
 
);

ALTER TABLE tb_cliente ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);

CREATE TABLE tb_substrato(

    id_substrato        INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    nm_material         VARCHAR(70)       NOT NULL, 
    vl_material        	numeric(10,2)	  NOT NULL, 
    dt_cadastro         DATETIME     	  NOT NULL, 
    fl_primer	        BIT  	          NULL, 
    fl_coating          BIT               NULL ,
	id_usuario_cadastro	INT	              NOT NULL 
);

ALTER TABLE tb_substrato ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);

CREATE TABLE tb_tamanho_substrato(

    id_tamanho_substrato INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    vl_x                INT               NOT NULL, 
    vl_y        	    INT         	  NOT NULL, 
    dt_cadastro         DATETIME     	  NOT NULL, 
    id_substrato      	INT	              NOT NULL,
	id_usuario_cadastro	INT	              NOT NULL 
 
);

ALTER TABLE tb_tamanho_substrato ADD FOREIGN KEY (id_substrato) REFERENCES tb_substrato(id_substrato);
ALTER TABLE tb_tamanho_substrato ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);

CREATE TABLE tb_categoria_imagem(

    id_categoria_imagem    INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    nm_categoria_imagem    VARCHAR(200)      NOT NULL,
	vl_ordem_menu          INT               NOT NULL, 
    fl_categoria_principal BIT       	     NOT NULL,
	dt_cadastro            DATETIME     	 NOT NULL,
	id_usuario_cadastro	   INT	             NOT NULL 
);

ALTER TABLE tb_categoria_imagem ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);

CREATE TABLE tb_imagem(

    id_imagem              INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    id_autor	           INT               NOT NULL,
	ds_caminho_imagem      VARCHAR(200)      NOT NULL, 
	fl_exclusivo           BIT               NULL,
    id_categoria_imagem    INT           	 NOT NULL,
	dt_cadastro            DATETIME     	 NOT NULL,
	id_usuario_cadastro	   INT	             NOT NULL 
);

ALTER TABLE tb_imagem ADD FOREIGN KEY (id_categoria_imagem) REFERENCES tb_categoria_imagem(id_categoria_imagem);
ALTER TABLE tb_imagem ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);

CREATE TABLE tb_ordem_servico(

    id_ordem_servico       INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    id_substato	           INT               NOT NULL,
	id_tamanho_substrato   INT               NOT NULL, 
	id_cliente			   INT               NOT NULL,
	nm_tamanho             INT               NOT NULL,
    id_imagem              INT           	 NOT NULL,
	dt_cadastro            DATETIME     	 NOT NULL,
	id_usuario_cadastro	   INT	             NOT NULL 
);

ALTER TABLE tb_ordem_servico ADD FOREIGN KEY (id_substato) REFERENCES tb_substrato(id_substrato);
ALTER TABLE tb_ordem_servico ADD FOREIGN KEY (id_tamanho_substrato) REFERENCES tb_tamanho_substrato(id_tamanho_substrato);
ALTER TABLE tb_ordem_servico ADD FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id_cliente);
ALTER TABLE tb_ordem_servico ADD FOREIGN KEY (id_imagem) REFERENCES tb_imagem(id_imagem);
ALTER TABLE tb_ordem_servico ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);
