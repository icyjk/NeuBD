-- Generado por Oracle SQL Developer Data Modeler 20.4.1.406.0906
--   en:        2021-04-20 10:34:00 CEST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE alumno (
    id                   VARCHAR2(180 CHAR) NOT NULL,
    dni                  VARCHAR2(180 CHAR) NOT NULL,
    nombre               VARCHAR2(180 CHAR) NOT NULL,
    primer_apellido      VARCHAR2(180 CHAR) NOT NULL,
    segundo_apellido     VARCHAR2(180 CHAR),
    email_personal       VARCHAR2(180 CHAR) NOT NULL,
    email_institucional  VARCHAR2(180 CHAR) NOT NULL,
    m�vil                VARCHAR2(180 CHAR) NOT NULL,
    tel�fono             VARCHAR2(180 CHAR),
    direcci�n            VARCHAR2(180 CHAR),
    localidad            VARCHAR2(180 CHAR),
    provincia            VARCHAR2(180 CHAR),
    cp                   VARCHAR2(180 CHAR)
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( id ) USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE alumno ADD CONSTRAINT alumno_dni_un UNIQUE ( dni );

ALTER TABLE alumno ADD CONSTRAINT alumno_email_personal_un UNIQUE ( email_personal );

ALTER TABLE alumno ADD CONSTRAINT alumno_id_un UNIQUE ( id );

ALTER TABLE alumno ADD CONSTRAINT alumno_m�vil_un UNIQUE ( m�vil );

ALTER TABLE alumno ADD CONSTRAINT alumno_tel�fono_un UNIQUE ( tel�fono );

CREATE TABLE asig_matri (
    asignatura_referencia      VARCHAR2(180 CHAR) NOT NULL,
    matr_exp_num_exp           VARCHAR2(180 CHAR) NOT NULL,
    matr�cula_curso_acad�mico  VARCHAR2(180 CHAR) NOT NULL,
    grupo_id                   VARCHAR2(180 CHAR),
    ingles                     CHAR(1),
    asignacionmanual           CHAR(1)
);

ALTER TABLE asig_matri
    ADD CONSTRAINT asig_matri_pk PRIMARY KEY ( matr_exp_num_exp,
                                               matr�cula_curso_acad�mico,
                                               asignatura_referencia ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE asignatura (
    referencia                       VARCHAR2(180 CHAR) NOT NULL,
    c�digo                           VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_pr�ctica                VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_teor�a                  VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos                         VARCHAR2(180 CHAR) NOT NULL,
    ofertada                         VARCHAR2(180 CHAR) NOT NULL,
    nombre                           VARCHAR2(180 CHAR) NOT NULL,
    curso                            VARCHAR2(180 CHAR),
    car�cter                         VARCHAR2(180 CHAR),
    duraci�n                         VARCHAR2(180 CHAR),
    "Unidad_Temporal(Cuatrimestre)"  VARCHAR2(180 CHAR),
    idiomas_de_impartici�n           VARCHAR2(180 CHAR),
    titulaci�n_c�digo                VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( referencia ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE centro (
    id               VARCHAR2(180 CHAR) NOT NULL,
    nombre           VARCHAR2(180 CHAR) NOT NULL,
    direcci�n        VARCHAR2(180 CHAR) NOT NULL,
    tlf_conserjer�a  VARCHAR2(180 CHAR)
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( id ) USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE centro ADD CONSTRAINT centro_nombre_un UNIQUE ( nombre );

CREATE TABLE clase (
    d�a                    VARCHAR2(180 CHAR) NOT NULL,
    hora_inicio            VARCHAR2(180 CHAR) NOT NULL,
    hora_fin               VARCHAR2(180 CHAR),
    asignatura_referencia  VARCHAR2(180 CHAR) NOT NULL,
    grupo_id               VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE clase
    ADD CONSTRAINT clase_pk PRIMARY KEY ( grupo_id,
                                          d�a,
                                          hora_inicio ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE datos_alum (
    dni                     VARCHAR2(180 CHAR) NOT NULL,
    nombre                  VARCHAR2(180 CHAR) NOT NULL,
    primer_apellido         VARCHAR2(180 CHAR) NOT NULL,
    segundo_apellido        VARCHAR2(180 CHAR) NOT NULL,
    n�mero_expediente       VARCHAR2(180 CHAR) NOT NULL,
    email_institucional     VARCHAR2(180 CHAR) NOT NULL,
    email_personal          VARCHAR2(180 CHAR) NOT NULL,
    numero_archivo          VARCHAR2(180 BYTE) NOT NULL,
    tel�fono                VARCHAR2(180 CHAR) NOT NULL,
    m�vil                   VARCHAR2(180 CHAR) NOT NULL,
    direcci�n_notificaci�n  VARCHAR2(180 CHAR) NOT NULL,
    localidad_notificaci�n  VARCHAR2(180 CHAR) NOT NULL,
    provincia_notificaci�n  VARCHAR2(180 CHAR) NOT NULL,
    cp_notificaci�n         VARCHAR2(180 CHAR) NOT NULL,
    fecha_matr�cula         VARCHAR2(180 CHAR) NOT NULL,
    turno_preferente        VARCHAR2(180 CHAR) NOT NULL,
    grupos_asignados        VARCHAR2(180 CHAR) NOT NULL,
    nota_media              VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_superados      VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_fb             VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_ob             VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_op             VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_cf             VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_pe             VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_tf             VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE datos_alum ADD CONSTRAINT datos_alum_pk PRIMARY KEY ( dni ) USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE datos_alum ADD CONSTRAINT datos_alum_email_insti_un UNIQUE ( email_institucional );

ALTER TABLE datos_alum ADD CONSTRAINT datos_alum_email_personal_un UNIQUE ( email_personal );

ALTER TABLE datos_alum ADD CONSTRAINT datos_alum_m�vil_un UNIQUE ( m�vil );

ALTER TABLE datos_alum ADD CONSTRAINT datos_alum_n�m_exp_un UNIQUE ( n�mero_expediente );

ALTER TABLE datos_alum ADD CONSTRAINT datos_alum_tel�fono_un UNIQUE ( tel�fono );

CREATE TABLE encuesta (
    fecha_de_env�o              VARCHAR2(180 CHAR) NOT NULL,
    expedientes_num_expediente  VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE encuesta ADD CONSTRAINT encuesta_pk PRIMARY KEY ( fecha_de_env�o ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE expedientes (
    num_expediente          VARCHAR2(180 CHAR) NOT NULL,
    activo                  VARCHAR2(180 CHAR),
    nota_media_provisional  VARCHAR2(180 CHAR),
    cr�ditos_superado       VARCHAR2(180 CHAR),
    cr�ditos_fb             VARCHAR2(180 CHAR),
    cr�ditos_ob             VARCHAR2(180 CHAR),
    cr�ditos_op             VARCHAR2(180 CHAR),
    cr�ditos_cf             VARCHAR2(180 CHAR),
    cr�ditos_pe             VARCHAR2(180 CHAR),
    cr�ditos_tf             VARCHAR2(180 CHAR),
    titulaci�n_c�digo       VARCHAR2(180 CHAR) NOT NULL,
    alumno_id               VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE expedientes ADD CONSTRAINT expedientes_pk PRIMARY KEY ( num_expediente ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE grupo (
    id                  VARCHAR2(180 CHAR) NOT NULL,
    curso               VARCHAR2(180 CHAR) NOT NULL,
    letra               VARCHAR2(180 CHAR) NOT NULL,
    turno_ma�ana_tarde  VARCHAR2(180 CHAR) NOT NULL,
    ingl�s              VARCHAR2(180 CHAR) NOT NULL,
    visible             VARCHAR2(180 CHAR),
    asignar             VARCHAR2(180 CHAR),
    plazas              VARCHAR2(180 CHAR),
    titulaci�n_c�digo   VARCHAR2(180 CHAR) NOT NULL,
    grupo_id            VARCHAR2(180 CHAR)
);

ALTER TABLE grupo ADD CONSTRAINT grupo_pk PRIMARY KEY ( id ) USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE grupo ADD CONSTRAINT grupo_curso_un UNIQUE ( curso );

ALTER TABLE grupo ADD CONSTRAINT grupo_letra_un UNIQUE ( letra );

CREATE TABLE grupo_por_asig (
    curso_academico        VARCHAR2(180 CHAR) NOT NULL,
    oferta                 VARCHAR2(180 CHAR),
    asignatura_referencia  VARCHAR2(180 CHAR) NOT NULL,
    grupo_id               VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE grupo_por_asig
    ADD CONSTRAINT grupo_por_asig_pk PRIMARY KEY ( asignatura_referencia,
                                                   grupo_id,
                                                   curso_academico ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE matr�cula (
    curso_acad�mico             VARCHAR2(180 CHAR) NOT NULL,
    estado                      VARCHAR2(180 CHAR) NOT NULL,
    num_archivo                 VARCHAR2(180 CHAR),
    turno_preferente            VARCHAR2(180 CHAR),
    fecha_de_matr�cula          VARCHAR2(180 CHAR) NOT NULL,
    nuevo_ingreso               VARCHAR2(180 CHAR),
    listado_asignaturas         VARCHAR2(180 CHAR),
    expedientes_num_expediente  VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE matr�cula ADD CONSTRAINT matr�cula_pk PRIMARY KEY ( expedientes_num_expediente,
                                                                curso_acad�mico ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE oferta_asig (
    titulaci�n        VARCHAR2(180 CHAR) NOT NULL,
    ofertada          VARCHAR2(180 CHAR) NOT NULL,
    c�digo            VARCHAR2(180 CHAR) NOT NULL,
    referencia        VARCHAR2(180 CHAR) NOT NULL,
    asignatura        VARCHAR2(180 CHAR) NOT NULL,
    curso             VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_teor�a   VARCHAR2(180 CHAR) NOT NULL,
    cr�dtos_pr�ctica  VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos_totales  VARCHAR2(180 CHAR) NOT NULL,
    duraci�n          VARCHAR2(180 CHAR),
    plazas            VARCHAR2(180 CHAR),
    otro_idioma       VARCHAR2(180 CHAR)
);

ALTER TABLE oferta_asig ADD CONSTRAINT oferta_asig_pk PRIMARY KEY ( c�digo ) USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE oferta_asig ADD CONSTRAINT oferta_asig_referencia_un UNIQUE ( referencia );

CREATE TABLE optativa (
    referencia  VARCHAR2(180 CHAR) NOT NULL,
    plazas      VARCHAR2(180 CHAR) NOT NULL,
    menci�n     VARCHAR2(180 CHAR)
);

ALTER TABLE optativa ADD CONSTRAINT optativa_pk PRIMARY KEY ( referencia ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE r_asig_tit (
    optativa_referencia  VARCHAR2(180 CHAR) NOT NULL,
    titulaci�n_c�digo    VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE r_asig_tit ADD CONSTRAINT relation_20_pk PRIMARY KEY ( optativa_referencia,
                                                                   titulaci�n_c�digo ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE r_enc_grupasig (
    grupo_por_asig_referencia       VARCHAR2(180 CHAR) NOT NULL,
    grupo_por_asig_id               VARCHAR2(180 CHAR) NOT NULL,
    grupo_por_asig_curso_academico  VARCHAR2(180 CHAR) NOT NULL,
    encuesta_fecha_de_env�o         VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE r_enc_grupasig
    ADD CONSTRAINT relation_31_pk PRIMARY KEY ( grupo_por_asig_referencia,
                                                grupo_por_asig_id,
                                                grupo_por_asig_curso_academico,
                                                encuesta_fecha_de_env�o ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE r_tit_cent (
    centro_id          VARCHAR2(180 CHAR) NOT NULL,
    titulaci�n_c�digo  VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE r_tit_cent ADD CONSTRAINT relation_27_pk PRIMARY KEY ( centro_id,
                                                                   titulaci�n_c�digo ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE titulaci�n (
    c�digo    VARCHAR2(180 CHAR) NOT NULL,
    nombre    VARCHAR2(180 CHAR) NOT NULL,
    cr�ditos  VARCHAR2(180 CHAR) NOT NULL
);

ALTER TABLE titulaci�n ADD CONSTRAINT titulaci�n_pk PRIMARY KEY ( c�digo ) USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE asig_matri
    ADD CONSTRAINT asig_matri_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE asig_matri
    ADD CONSTRAINT asig_matri_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE asig_matri
    ADD CONSTRAINT asig_matri_matr�cula_fk FOREIGN KEY ( matr_exp_num_exp,
                                                         matr�cula_curso_acad�mico )
        REFERENCES matr�cula ( expedientes_num_expediente,
                               curso_acad�mico );

ALTER TABLE asignatura
    ADD CONSTRAINT asignatura_titulaci�n_fk FOREIGN KEY ( titulaci�n_c�digo )
        REFERENCES titulaci�n ( c�digo );

ALTER TABLE clase
    ADD CONSTRAINT clase_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE clase
    ADD CONSTRAINT clase_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE encuesta
    ADD CONSTRAINT encuesta_expedientes_fk FOREIGN KEY ( expedientes_num_expediente )
        REFERENCES expedientes ( num_expediente );

ALTER TABLE expedientes
    ADD CONSTRAINT expedientes_alumno_fk FOREIGN KEY ( alumno_id )
        REFERENCES alumno ( id );

ALTER TABLE expedientes
    ADD CONSTRAINT expedientes_titulaci�n_fk FOREIGN KEY ( titulaci�n_c�digo )
        REFERENCES titulaci�n ( c�digo );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo_por_asig
    ADD CONSTRAINT grupo_por_asig_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE grupo_por_asig
    ADD CONSTRAINT grupo_por_asig_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_titulaci�n_fk FOREIGN KEY ( titulaci�n_c�digo )
        REFERENCES titulaci�n ( c�digo );

ALTER TABLE matr�cula
    ADD CONSTRAINT matr�cula_expedientes_fk FOREIGN KEY ( expedientes_num_expediente )
        REFERENCES expedientes ( num_expediente );

ALTER TABLE optativa
    ADD CONSTRAINT optativa_asignatura_fk FOREIGN KEY ( referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE r_asig_tit
    ADD CONSTRAINT relation_20_optativa_fk FOREIGN KEY ( optativa_referencia )
        REFERENCES optativa ( referencia );

ALTER TABLE r_asig_tit
    ADD CONSTRAINT relation_20_titulaci�n_fk FOREIGN KEY ( titulaci�n_c�digo )
        REFERENCES titulaci�n ( c�digo );

ALTER TABLE r_tit_cent
    ADD CONSTRAINT relation_27_centro_fk FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

ALTER TABLE r_tit_cent
    ADD CONSTRAINT relation_27_titulaci�n_fk FOREIGN KEY ( titulaci�n_c�digo )
        REFERENCES titulaci�n ( c�digo );

ALTER TABLE r_enc_grupasig
    ADD CONSTRAINT relation_31_encuesta_fk FOREIGN KEY ( encuesta_fecha_de_env�o )
        REFERENCES encuesta ( fecha_de_env�o );

ALTER TABLE r_enc_grupasig
    ADD CONSTRAINT relation_31_grupo_por_asig_fk FOREIGN KEY ( grupo_por_asig_referencia,
                                                               grupo_por_asig_id,
                                                               grupo_por_asig_curso_academico )
        REFERENCES grupo_por_asig ( asignatura_referencia,
                                    grupo_id,
                                    curso_academico );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            17
-- CREATE INDEX                             0
-- ALTER TABLE                             52
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
