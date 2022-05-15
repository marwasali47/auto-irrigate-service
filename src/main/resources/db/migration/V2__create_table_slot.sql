CREATE SEQUENCE public.slot_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.slot_seq
    OWNER TO postgres;


CREATE TABLE public.slot
(
    id integer NOT NULL DEFAULT nextval('slot_seq'::regclass),
    name varchar(100) COLLATE pg_catalog."default" NOT NULL,
    plot_id integer NOT NULL,
    CONSTRAINT slot_pk PRIMARY KEY (id),
    CONSTRAINT plot_fk FOREIGN KEY (plot_id) REFERENCES plot(id) ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


ALTER TABLE public.slot
    OWNER to postgres;