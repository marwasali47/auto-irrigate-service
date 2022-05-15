
CREATE SEQUENCE public.plot_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.plot_seq
    OWNER TO postgres;

CREATE TABLE public.plot
(
    id bigint NOT NULL DEFAULT nextval('plot_seq'::regclass),
    name text COLLATE pg_catalog."default",
    CONSTRAINT plot_pkey PRIMARY KEY (id)
)WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE public.plot
    OWNER to postgres;