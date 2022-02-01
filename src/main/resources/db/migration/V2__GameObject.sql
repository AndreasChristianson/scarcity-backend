CREATE TABLE template (
    id             uuid PRIMARY KEY
    ,icon          text NOT NULL
    ,description   text NOT NULL
    ,label         text NOT NULL
    ,dtype         text NOT NULL
);

CREATE TABLE game_object (
    id             uuid PRIMARY KEY
    ,template      uuid NOT NULL REFERENCES template (id)
    ,dtype         text NOT NULL

    ,parent        uuid REFERENCES game_object (id)
    ,x             float
    ,y             float
    ,durability    float
    ,trigger_time  bigint
);

CREATE INDEX ON game_object(parent);

CREATE TABLE modifier (
    id             uuid PRIMARY KEY
    ,template      uuid NOT NULL REFERENCES template (id)
    ,target        uuid REFERENCES game_object (id)
    ,value         float NOT NULL
    ,dtype         text NOT NULL
);
CREATE INDEX ON modifier(target);

CREATE TABLE change (
    id             uuid PRIMARY KEY
    ,target        uuid NOT NULL REFERENCES game_object (id)
    ,game_time     bigint NOT NULL
    ,stamp         timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
    ,dtype         text NOT NULL

    ,parent        uuid
    ,modifier      uuid
);

CREATE INDEX ON change(target);