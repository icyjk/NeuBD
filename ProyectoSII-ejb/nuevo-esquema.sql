CREATE TABLE ALUMNO (ID INTEGER NOT NULL, CP VARCHAR, DIRECCION VARCHAR, DNI VARCHAR, EMAIL_INSTITUCIONAL VARCHAR, EMAIL_PERSONAL VARCHAR, LOCALIDAD VARCHAR, MOVIL VARCHAR, NOMBRE VARCHAR, PRIMER_APELLIDO VARCHAR, PROVINCIA VARCHAR, SEGUNDO_APELLIDO VARCHAR, TELEFONO VARCHAR, PRIMARY KEY (ID))
CREATE TABLE ASIGNATURA (REFERENCIA INTEGER NOT NULL, CARACTER VARCHAR, CODIGO INTEGER, CREDITOS INTEGER, CREDITOS_PRACTICA INTEGER, CREDITOS_TEORIA INTEGER, CURSO INTEGER, DURACION INTEGER, IDIOMA_IMPARTICION VARCHAR, NOMBRE VARCHAR, OFERTADA BOOLEAN, UNIDAD_TEMPORAL VARCHAR, TITULACION_CODIGO INTEGER, PRIMARY KEY (REFERENCIA))
CREATE TABLE ASIGNATURA_MATRICULA (ASIGNACIONMANUAL BOOLEAN, IDIOMA BOOLEAN, ASIGNATURA_REFERENCIA INTEGER NOT NULL, GRUPO_ID INTEGER, EXPEDIENTES_NUM_EXPEDIENTE BIGINT NOT NULL, CURSO_ACADEMICO INTEGER NOT NULL, PRIMARY KEY (ASIGNATURA_REFERENCIA, EXPEDIENTES_NUM_EXPEDIENTE, CURSO_ACADEMICO))
CREATE TABLE CENTRO (ID INTEGER NOT NULL, DIRECCION VARCHAR, NOMBRE VARCHAR, TLF_CONSERJERIA VARCHAR, PRIMARY KEY (ID))
CREATE TABLE CLASE (HORA_INICIO DATE NOT NULL, DIA VARCHAR NOT NULL, HORA_FIN DATE, ASIGNATURAS_REFERENCIA INTEGER, GRUPO_ID INTEGER NOT NULL, PRIMARY KEY (HORA_INICIO, DIA, GRUPO_ID))
CREATE TABLE DATOS_ALUMNADO (DNI VARCHAR NOT NULL, CP VARCHAR, CREDITOS_CF VARCHAR, CREDITOS_FB VARCHAR, CREDITOS_OB VARCHAR, CREDITOS_OP VARCHAR, CREDITOS_PE VARCHAR, CREDITOS_TF VARCHAR, CREDITOS_SUPERADOS VARCHAR, DIRECCION_NOT VARCHAR, EMAIL_INSTITUCIONAL VARCHAR, EMAIL_PERSONAL VARCHAR, FECHA_MATRICULA VARCHAR, GRUPOS_ASIGNADOS VARCHAR, LOCALIDAD_NOT VARCHAR, MOVIL INTEGER, NOMBRE VARCHAR, NOTA_MEDIA VARCHAR, NUM_EXPEDIENTE INTEGER, P_APELLIDO VARCHAR, PROVINCIA_NOT VARCHAR, S_APELLIDO VARCHAR, TELEFONO INTEGER, TURNO_PREFERENTE VARCHAR, PRIMARY KEY (DNI))
CREATE TABLE ENCUESTA (FECHA_DE_ENVIO TIMESTAMP NOT NULL, EXPEDIENTES_NUM_EXPEDIENTE BIGINT, PRIMARY KEY (FECHA_DE_ENVIO))
CREATE TABLE EXPEDIENTES (NUM_EXPEDIENTE BIGINT NOT NULL, ACTIVO BOOLEAN, CREDITO_CF INTEGER, CREDITO_FB INTEGER, CREDITO_OB INTEGER, CREDITO_OP INTEGER, CREDITO_PE INTEGER, CREDITO_TF INTEGER, CREDITOS_SUPERADO INTEGER, NOTA_MEDIA_PROVISIONAL DOUBLE, ALUMNO_ID INTEGER, TITULACIONES_CODIGO INTEGER, PRIMARY KEY (NUM_EXPEDIENTE))
CREATE TABLE GRUPO (ID INTEGER NOT NULL, ASIGNAR VARCHAR, CURSO INTEGER, INGLES BOOLEAN, LETRA CHAR, PLAZAS INTEGER, TURNO_MAÑANA_TARDE VARCHAR, VISIBLE BOOLEAN, GRUPOS_ID INTEGER, TITULACION_CODIGO INTEGER, PRIMARY KEY (ID))
CREATE TABLE GRUPOS_POR_ASIGNATURA (CURSO_ACADEMICO INTEGER NOT NULL, OFERTA BOOLEAN, ASIGNATURA_REFERENCIA INTEGER NOT NULL, GRUPO_ID INTEGER NOT NULL, PRIMARY KEY (CURSO_ACADEMICO, ASIGNATURA_REFERENCIA, GRUPO_ID))
CREATE TABLE MATRICULA (CURSO_ACADEMICO INTEGER NOT NULL, ESTADO VARCHAR, FECHA_MATRICULA DATE, NUEVO_INGRESO VARCHAR, NUM_ARCHIVO INTEGER, TURNO_PREFERENTE VARCHAR, EXPEDIENTES_NUM_EXPEDIENTE BIGINT NOT NULL, PRIMARY KEY (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE))
CREATE TABLE OFERTA_ASIGNATURAS (ID VARCHAR NOT NULL, ASIGNATURA VARCHAR, CODIGO INTEGER, CREDITO_PRACTICA VARCHAR, CREDITO_TEORIA VARCHAR, CREDITO_TOTALES VARCHAR, CURSO INTEGER, DURACION INTEGER, OFERTADA VARCHAR, OTRO_IDIOMA VARCHAR, PLAZAS INTEGER, REFERENCIA VARCHAR, TITULACION VARCHAR, PRIMARY KEY (ID))
CREATE TABLE OPTATIVA (REFERENCIA INTEGER NOT NULL, CARACTER VARCHAR, CODIGO INTEGER, CREDITOS INTEGER, CREDITOS_PRACTICA INTEGER, CREDITOS_TEORIA INTEGER, CURSO INTEGER, DURACION INTEGER, IDIOMA_IMPARTICION VARCHAR, MENCION VARCHAR, NOMBRE VARCHAR, OFERTADA BOOLEAN, PLAZAS INTEGER, UNIDAD_TEMPORAL VARCHAR, TITULACION_CODIGO INTEGER, PRIMARY KEY (REFERENCIA))
CREATE TABLE PRUEBA (NUM INTEGER NOT NULL, PRIMARY KEY (NUM))
CREATE TABLE TITULACION (CODIGO INTEGER NOT NULL, CREDITOS INTEGER, NOMBRE VARCHAR, PRIMARY KEY (CODIGO))
CREATE TABLE ALUMNO_EXPEDIENTES (Alumno_ID INTEGER NOT NULL, expedientes_NUM_EXPEDIENTE BIGINT NOT NULL, PRIMARY KEY (Alumno_ID, expedientes_NUM_EXPEDIENTE))
CREATE TABLE jnd_centro_titulacion (centro_fk INTEGER NOT NULL, titulacion_fk INTEGER NOT NULL, PRIMARY KEY (centro_fk, titulacion_fk))
CREATE TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA (encuestas_FECHA_DE_ENVIO TIMESTAMP NOT NULL, CURSO_ACADEMICO INTEGER NOT NULL, ASIGNATURA_REFERENCIA INTEGER NOT NULL, GRUPO_ID INTEGER NOT NULL, PRIMARY KEY (encuestas_FECHA_DE_ENVIO, CURSO_ACADEMICO, ASIGNATURA_REFERENCIA, GRUPO_ID))
CREATE TABLE MATRICULA_ASIGNATURA_MATRICULA (CURSO_ACADEMICO INTEGER NOT NULL, EXPEDIENTES_NUM_EXPEDIENTE BIGINT NOT NULL, ASIGNATURA_REFERENCIA INTEGER NOT NULL, PRIMARY KEY (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE, ASIGNATURA_REFERENCIA))
CREATE TABLE MATRICULA_ASIGNATURA (CURSO_ACADEMICO INTEGER NOT NULL, EXPEDIENTES_NUM_EXPEDIENTE BIGINT NOT NULL, listado_asignaturas_REFERENCIA INTEGER NOT NULL, PRIMARY KEY (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE, listado_asignaturas_REFERENCIA))
ALTER TABLE ASIGNATURA ADD CONSTRAINT FK_ASIGNATURA_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE ASIGNATURA_MATRICULA ADD CONSTRAINT FK_ASIGNATURA_MATRICULA_CURSO_ACADEMICO FOREIGN KEY (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE) REFERENCES MATRICULA (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE)
ALTER TABLE ASIGNATURA_MATRICULA ADD CONSTRAINT FK_ASIGNATURA_MATRICULA_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE CLASE ADD CONSTRAINT FK_CLASE_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE ENCUESTA ADD CONSTRAINT FK_ENCUESTA_EXPEDIENTES_NUM_EXPEDIENTE FOREIGN KEY (EXPEDIENTES_NUM_EXPEDIENTE) REFERENCES EXPEDIENTES (NUM_EXPEDIENTE)
ALTER TABLE EXPEDIENTES ADD CONSTRAINT FK_EXPEDIENTES_ALUMNO_ID FOREIGN KEY (ALUMNO_ID) REFERENCES ALUMNO (ID)
ALTER TABLE EXPEDIENTES ADD CONSTRAINT FK_EXPEDIENTES_TITULACIONES_CODIGO FOREIGN KEY (TITULACIONES_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_GRUPOS_ID FOREIGN KEY (GRUPOS_ID) REFERENCES GRUPO (ID)
ALTER TABLE GRUPOS_POR_ASIGNATURA ADD CONSTRAINT FK_GRUPOS_POR_ASIGNATURA_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE MATRICULA ADD CONSTRAINT FK_MATRICULA_EXPEDIENTES_NUM_EXPEDIENTE FOREIGN KEY (EXPEDIENTES_NUM_EXPEDIENTE) REFERENCES EXPEDIENTES (NUM_EXPEDIENTE)
ALTER TABLE OPTATIVA ADD CONSTRAINT FK_OPTATIVA_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE ALUMNO_EXPEDIENTES ADD CONSTRAINT FK_ALUMNO_EXPEDIENTES_Alumno_ID FOREIGN KEY (Alumno_ID) REFERENCES ALUMNO (ID)
ALTER TABLE ALUMNO_EXPEDIENTES ADD CONSTRAINT FK_ALUMNO_EXPEDIENTES_expedientes_NUM_EXPEDIENTE FOREIGN KEY (expedientes_NUM_EXPEDIENTE) REFERENCES EXPEDIENTES (NUM_EXPEDIENTE)
ALTER TABLE jnd_centro_titulacion ADD CONSTRAINT FK_jnd_centro_titulacion_centro_fk FOREIGN KEY (centro_fk) REFERENCES CENTRO (ID)
ALTER TABLE jnd_centro_titulacion ADD CONSTRAINT FK_jnd_centro_titulacion_titulacion_fk FOREIGN KEY (titulacion_fk) REFERENCES TITULACION (CODIGO)
ALTER TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA ADD CONSTRAINT FK_ENCUESTA_GRUPOS_POR_ASIGNATURA_CURSO_ACADEMICO FOREIGN KEY (CURSO_ACADEMICO, ASIGNATURA_REFERENCIA, GRUPO_ID) REFERENCES GRUPOS_POR_ASIGNATURA (CURSO_ACADEMICO, ASIGNATURA_REFERENCIA, GRUPO_ID)
ALTER TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA ADD CONSTRAINT ENCUESTAGRUPOSPORASIGNATURAencuestasFECHA_DE_ENVIO FOREIGN KEY (encuestas_FECHA_DE_ENVIO) REFERENCES ENCUESTA (FECHA_DE_ENVIO)
ALTER TABLE MATRICULA_ASIGNATURA_MATRICULA ADD CONSTRAINT FK_MATRICULA_ASIGNATURA_MATRICULA_CURSO_ACADEMICO FOREIGN KEY (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE) REFERENCES MATRICULA (CURSO_ACADEMICO, EXPEDIENTES_NUM_EXPEDIENTE)
ALTER TABLE MATRICULA_ASIGNATURA_MATRICULA ADD CONSTRAINT MATRICULAASIGNATURA_MATRICULAASIGNATURA_REFERENCIA FOREIGN KEY (ASIGNATURA_REFERENCIA, EXPEDIENTES_NUM_EXPEDIENTE, CURSO_ACADEMICO) REFERENCES ASIGNATURA_MATRICULA (ASIGNATURA_REFERENCIA, EXPEDIENTES_NUM_EXPEDIENTE, CURSO_ACADEMICO)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
