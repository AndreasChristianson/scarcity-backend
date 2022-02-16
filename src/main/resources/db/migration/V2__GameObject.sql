CREATE TABLE template (
    id             uuid PRIMARY KEY
    ,icon          text
    ,description   text NOT NULL
    ,label         text NOT NULL
    ,dtype         text NOT NULL
);

CREATE TABLE game_object (
    id             uuid PRIMARY KEY
    ,template_id   uuid NOT NULL REFERENCES template (id)
    ,parent_id     uuid NOT NULL REFERENCES game_object (id)
    ,x             float
    ,y             float
);

CREATE INDEX ON game_object(parent_id);

CREATE TABLE modifier (
    id             uuid PRIMARY KEY
    ,template_id   uuid NOT NULL REFERENCES template (id)
    ,parent_id     uuid REFERENCES game_object (id)
    ,dtype         text NOT NULL
);
CREATE INDEX ON modifier(parent_id);

CREATE TABLE change (
    id             uuid PRIMARY KEY
    ,parent_id     uuid NOT NULL REFERENCES game_object (id)
    ,game_time     bigint NOT NULL
    ,stamp         timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
    ,dtype         text NOT NULL

    ,source_id      uuid REFERENCES game_object (id)
    ,modifier_id    uuid REFERENCES modifier (id)
);

CREATE INDEX ON change(parent_id);