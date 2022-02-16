ALTER TABLE template
  ADD COLUMN max_range FLOAT;
ALTER TABLE template
  ADD COLUMN min_range FLOAT;
ALTER TABLE template
  ADD COLUMN base_level FLOAT;
ALTER TABLE template
  ADD COLUMN flavor TEXT;

ALTER TABLE template
  ADD COLUMN rarity TEXT;
ALTER TABLE template
  ADD COLUMN type TEXT;