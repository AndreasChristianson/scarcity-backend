--CREATE TABLE template (
--    id             uuid PRIMARY KEY
--    ,icon          text
--    ,description   text NOT NULL
--    ,label         text NOT NULL
--    ,dtype         text NOT NULL
--);

CREATE TABLE game_object (
    id             uuid PRIMARY KEY
    ,parent_id     uuid NOT NULL REFERENCES game_object (id)
    ,dtype         text NOT NULL
    ,x             float
    ,y             float
);

CREATE INDEX ON game_object(parent_id);

CREATE TABLE modifier (
    id             uuid PRIMARY KEY
    ,parent_id     uuid REFERENCES game_object (id)
    ,dtype         text NOT NULL

);
CREATE INDEX ON modifier(parent_id);

CREATE TABLE change (
    id             uuid PRIMARY KEY
    ,game_object_id     uuid NOT NULL REFERENCES game_object (id)
    ,game_time     bigint NOT NULL
    ,stamp         timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
    ,message         text NOT NULL
);

CREATE INDEX ON change(game_object_id);
CREATE INDEX ON change(game_time);
