CREATE SEQUENCE public.time_slot_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.time_slot_seq
    OWNER TO postgres;


CREATE TABLE public.time_slot
(
    id integer NOT NULL DEFAULT nextval('time_slot_seq'::regclass),
    irrigation_time bigint NOT NULL,
    water_amount bigint NOT NULL,
    slot_id integer NOT NULL,
    CONSTRAINT time_slot_pk PRIMARY KEY (id),
    CONSTRAINT slot_fk FOREIGN KEY (slot_id) REFERENCES slot(id) ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


ALTER TABLE public.time_slot
    OWNER to postgres;