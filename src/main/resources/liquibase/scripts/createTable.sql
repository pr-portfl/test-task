
CREATE TABLE IF NOT EXISTS public.notification
(
    id integer NOT NULL,
    date_mes timestamp without time zone,
    mes character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT notification_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.notification
    OWNER to postgres;