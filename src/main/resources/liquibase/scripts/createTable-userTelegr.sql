CREATE TABLE IF NOT EXISTS public.user_telegr
(
    id bigint NOT NULL,
    first_name character varying(100),
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.user_telegr
    OWNER to postgres;
