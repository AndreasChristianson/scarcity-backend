CREATE TABLE tag (
    id           uuid PRIMARY KEY
    ,tag         text NOT NULL
);

CREATE TABLE template_tag (
    template_id  uuid NOT NULL
    ,tag_id     uuid NOT NULL
    ,PRIMARY KEY (template_id, tag_id)
);
