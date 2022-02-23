ALTER TABLE template ADD COLUMN bind_type TEXT;
ALTER TABLE template ADD COLUMN volume FLOAT;
ALTER TABLE template ADD COLUMN weight FLOAT;
ALTER TABLE template ADD COLUMN damage_per_turn FLOAT;
ALTER TABLE template ADD COLUMN damage_shape TEXT;
ALTER TABLE template ADD COLUMN damage_type TEXT;
ALTER TABLE template ADD COLUMN slot TEXT;
ALTER TABLE template ADD COLUMN swing_duration BIGINT;
ALTER TABLE template ADD COLUMN ready_time BIGINT;
ALTER TABLE template ADD COLUMN max_range FLOAT;
ALTER TABLE template ADD COLUMN min_range FLOAT;
ALTER TABLE template ADD COLUMN base_level FLOAT;
ALTER TABLE template ADD COLUMN flavor TEXT;
ALTER TABLE template ADD COLUMN rarity SMALLINT;
ALTER TABLE template ADD COLUMN type TEXT;
