CREATE TABLE game_time (
    id             uuid PRIMARY KEY,
    game_time      bigint NOT NULL
);

INSERT INTO game_time(id, game_time)
  VALUES ('00000000-0000-0000-0000-000000000000', 1);

