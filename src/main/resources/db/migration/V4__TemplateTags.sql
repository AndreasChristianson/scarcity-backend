CREATE TABLE template_tag (
    template_id  uuid NOT NULL REFERENCES template (id)
    ,tag         text NOT NULL
);

CREATE INDEX ON template_tag(template_id);
