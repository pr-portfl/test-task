CREATE TABLE IF NOT EXISTS public.user_mes
(
    id integer GENERATED ALWAYS AS IDENTITY,
    id_user bigint not null,
    date_mes DATE DEFAULT CURRENT_DATE,
    mes character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT user_mes_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_mes
    OWNER to postgres;