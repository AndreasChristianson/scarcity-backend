CREATE TABLE tag (
    id           uuid PRIMARY KEY
    ,tag         text NOT NULL
);

CREATE TABLE template_tags (
    template_id  uuid NOT NULL
    ,tags_id     uuid NOT NULL
    ,PRIMARY KEY (template_id, tags_id)
);
