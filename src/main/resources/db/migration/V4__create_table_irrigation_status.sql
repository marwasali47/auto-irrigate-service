CREATE SEQUENCE public.irrigation_status_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.irrigation_status_seq
    OWNER TO postgres;


CREATE TABLE public.irrigation_status
(
    id integer NOT NULL DEFAULT nextval('irrigation_status_seq'::regclass),
    plot_id bigint NOT NULL,
    plot_name text COLLATE pg_catalog."default",
    slot_id bigint NOT NULL,
    slot_name text COLLATE pg_catalog."default",
    water_amount bigint NOT NULL,
    irrigation_status boolean default false ,
    irrigation_time bigint NOT NULL,
    creation_date bigint NOT NULL,
    CONSTRAINT plot_irrigation_status_pk PRIMARY KEY (id)
)
WITH ( OIDS = FALSE )
TABLESPACE pg_default;


ALTER TABLE public.irrigation_status
    OWNER to postgres;