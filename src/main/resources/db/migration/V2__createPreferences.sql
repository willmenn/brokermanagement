create table brokerPreferences ( id integer Primary KEY , id_broker INTEGER NOT NULL, id_preferences INTEGER NOT NULL);

create sequence brokerPreferences_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;

create table preferences ( id integer Primary KEY , weekDay varchar(30) NOT NULL, shift VARCHAR(20) NOT NULL);

create sequence preferences_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;