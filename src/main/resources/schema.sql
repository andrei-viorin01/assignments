-- Table: public.offer

-- DROP TABLE public.offer;

CREATE TABLE public.offer
(
    offer_id integer NOT NULL DEFAULT nextval('offer_offer_id_seq'::regclass),
    offer_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    offer_description character varying(250) COLLATE pg_catalog."default" NOT NULL,
    status character varying(15) COLLATE pg_catalog."default" NOT NULL,
    created_dt timestamp without time zone NOT NULL,
    active_days integer NOT NULL,
    expiry_dt timestamp without time zone NOT NULL,
    CONSTRAINT offer_pkey PRIMARY KEY (offer_id)
)

TABLESPACE pg_default;

ALTER TABLE public.offer
    OWNER to postgres;